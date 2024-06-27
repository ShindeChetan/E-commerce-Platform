package dev.chetan.userservicetestfinal.services;

import dev.chetan.userservicetestfinal.dtos.UserDto;
import dev.chetan.userservicetestfinal.models.Session;
import dev.chetan.userservicetestfinal.models.SessionStatus;
import dev.chetan.userservicetestfinal.models.User;
import dev.chetan.userservicetestfinal.repositories.SessionRepository;
import dev.chetan.userservicetestfinal.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;
import javax.crypto.SecretKey;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository
//                       BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ResponseEntity<UserDto>     login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();
        // password validation
//        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
//            return null;
//        }

//        String token = RandomStringUtils.randomAlphanumeric(30);

//        String message = "{\n" +
//        "   \"email\": \"naman@scaler.com\",\n" +
//        "   \"roles\": [\n" +
//        "      \"mentor\",\n" +
//        "      \"ta\"\n" +
//        "   ],\n" +
//        "   \"expirationDate\": \"23rdOctober2023\"\n" +
//        "}";
//        // user_id
//        // user_email
//        // roles
//        byte[] content = message.getBytes(StandardCharsets.UTF_8);

        Map<String, Object> jsonForJwt = new HashMap<>();
        jsonForJwt.put("email", user.getEmail());
        jsonForJwt.put("roles", user.getRoles());
        jsonForJwt.put("expirationDate", new Date());
        //if(xx =!null)
        jsonForJwt.put("createdAt" , new Date());

        MacAlgorithm alg = Jwts.SIG.HS256;
        SecretKey key = alg.key().build();

        String token = Jwts.builder().claims(jsonForJwt).signWith(key, alg).compact();

        Session session = new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setToken(token);
        session.setUser(user);
        sessionRepository.save(session);

        UserDto userDto = new UserDto();

//        Map<String, String> headers = new HashMap<>();
//        headers.put(HttpHeaders.SET_COOKIE, token);

        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token:" + token);



        ResponseEntity<UserDto> response = new ResponseEntity<>(userDto, headers, HttpStatus.OK);
//        response.getHeaders().add(HttpHeaders.SET_COOKIE, token);

        return response;
    }

    public ResponseEntity<Void> logout(String token, Long userId) {
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);

        if (sessionOptional.isEmpty()) {
            return null;
        }

        Session session = sessionOptional.get();

        session.setSessionStatus(SessionStatus.ENDED);

        sessionRepository.save(session);

        return ResponseEntity.ok().build();
    }

    public UserDto signUp(String email, String password) {
        User user = new User();
        user.setEmail(email);
//        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setPassword(password);
        User savedUser = userRepository.save(user);

        return UserDto.from(savedUser);
    }

    public SessionStatus validate(String token, Long userId) {
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);

        if (sessionOptional.isEmpty()) {
            return null;
        }

        MacAlgorithm alg = Jwts.SIG.HS256;
        SecretKey key = alg.key().build();

        Claims claims =
                Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();

        return SessionStatus.ACTIVE;
    }

}