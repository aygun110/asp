package com.example.impl;
import com.example.repository.UserRepository;
import com.example.model.UserDtls;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDtls user = userRepository.findByEmail(email);
        // Возвращаем UserDetails с одной ролью
        return User.withUsername(user.getName())
                .username(user.getName())
                .password(user.getPassword())// Убедитесь, что роль имеет префикс "ROLE_"
                .build();

    }

    public UserDtls saveUser(UserDtls user) {
        user.setRole("ROLE_USER");
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        UserDtls saveUser = userRepository.save(user);
        return saveUser;
    }


}
