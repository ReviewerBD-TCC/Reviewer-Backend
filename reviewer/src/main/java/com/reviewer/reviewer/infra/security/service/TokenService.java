package com.reviewer.reviewer.infra.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.reviewer.reviewer.dto.users.LoginDto;
import com.reviewer.reviewer.dto.users.RegisterDto;
import com.reviewer.reviewer.models.User;
import com.reviewer.reviewer.repositories.UserRepository;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String generateToken(User user){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Reviewer")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId())
                    .withExpiresAt(dateExpire())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error generating jwt token ", exception);
        }catch (TokenExpiredException exception){
            throw new TokenExpiredException("scorro", exception.getExpiredOn());
        }
    }

    public String getSubject(String tokenJWT, HttpServletResponse response) throws IOException {
        try{
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API Reviewer")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTCreationException exception){
            throw new RuntimeException("Token JWT inválido!", exception);
        } catch (TokenExpiredException exception){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token has expired, sign in again! ");
        }
        return tokenJWT;
    }


    private Instant dateExpire() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


    public String login(LoginDto data) throws BadCredentialsException{

        User user = (User) repository.findByEmail(data.email());
        if (user == null) {
            throw new BadCredentialsException("Falha na autenticação");
        }

        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = authenticationManager.authenticate(usernamePassword);
            return this.generateToken((User) auth.getPrincipal());
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Falha na autenticação: ");
        }
    }


    public User register(RegisterDto data){
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        var user = new User(data);
        user.setPassword(encryptedPassword);
        repository.save(user);

        return user;
    }

}
