package com.backend.ecommerce.User.business.manager;

import com.backend.ecommerce.User.business.service.UserServices;
import com.backend.ecommerce.User.dataAccess.mongo.UserRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserServices {

    @Autowired
    private PasswordEncoder passwordEncoder;
    /*@Autowired
    private UserRepository userRepository;*/
    @Autowired
    private UserRepositoryMongo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
    }
}
