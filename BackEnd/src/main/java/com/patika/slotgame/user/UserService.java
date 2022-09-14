package com.patika.slotgame.user;

import com.patika.slotgame.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User getByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(NotFoundException::new);
    }

    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void update(String username){
        User inDB = getByUsername(username);
        userRepository.save(inDB);
    }

}
