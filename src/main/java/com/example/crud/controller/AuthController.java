package com.example.crud.controller;

import com.example.crud.entity.Usuario;
import com.example.crud.repository.UsuarioRepository;
import com.example.crud.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @PostMapping("/register")
    public String register(@RequestBody Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRole("ROLE_USER");
        usuarioRepository.save(usuario);
        return "Usuario registrado exitosamente";
    }

        @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        try {
            // Intentamos autenticar
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.get("username"), request.get("password"))
            );
        } catch (Exception e) {
            // Si falla (contraseña incorrecta, etc.), devolvemos un error 401 controlado
            return ResponseEntity.status(401).body(Map.of("error", "Credenciales incorrectas o usuario no encontrado", "detalle", e.getMessage()));
        }

        // Si la autenticación fue exitosa, generamos el token
        UserDetails user = userDetailsService.loadUserByUsername(request.get("username"));
        String token = jwtService.generateToken(user);
        
        return ResponseEntity.ok(Map.of("token", token));
    }
}