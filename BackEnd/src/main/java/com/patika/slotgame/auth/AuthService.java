package com.patika.slotgame.auth;

import com.patika.slotgame.user.User;
import com.patika.slotgame.user.UserRepository;
import com.patika.slotgame.user.dto.UserDto;
import com.patika.slotgame.user.mapper.UserMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Date;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secret;

    private JwtParser jwtParser;

    @PostConstruct
    protected void init() {
        jwtParser = Jwts.parser().setSigningKey(secret);
    }

    public AuthResponse authenticate(Credentials credentials) {
        User inDB = userRepository.findByUsername(credentials.getUsername()).orElseThrow(AuthException::new);
        boolean matches = passwordEncoder.matches(credentials.getPassword(), inDB.getPassword());
        if (!matches) {
            throw new AuthException();
        }
        UserDto userDto = UserMapper.INSTANCE.userToUserDto(inDB);
        Date expiresAt = new Date(System.currentTimeMillis() + 1000 * 3000);
        String token = Jwts.builder().setSubject("" + inDB.getUsername())
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(expiresAt)
                .compact();
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUser(userDto);
        authResponse.setToken(token);
        return authResponse;
    }

    @Transactional
    public UserDetails getUserDetails(String token) {
        try {
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            String username = claims.getSubject();
            return userRepository.findByUsername(username).orElseThrow(AuthException::new);
        } catch (Exception e) {
            throw new AuthException();
        }
    }
}
