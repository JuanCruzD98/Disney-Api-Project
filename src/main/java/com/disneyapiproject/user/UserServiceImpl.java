
package com.disneyapiproject.user;

import com.disneyapiproject.emailsender.IEmailSenderService;
import com.disneyapiproject.mapstruct.dto.UserDto;
import com.disneyapiproject.security.CustomUserDetailsService;
import com.disneyapiproject.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    @Autowired
    private final IEmailSenderService emailService;

    private Authentication authenticate(UserDto user) {

        Authentication authentication;

        try {

            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        } catch (AuthenticationException e) {

            throw new BadCredentialsException("Wrong credentials");

        }

        return authentication;

    }



    public boolean checkIfEmailIsAvailable(String email) {
        return userRepository.existsByEmail(email);
    }

    public String logInUser(UserDto user) {

        Authentication authentication = authenticate(user);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());

        return jwtUtil.generateToken(userDetails);

    }

    public void registerUser(UserDto userToSave) {

        checkIfEmailIsAvailable(userToSave.getEmail());

        User user = new User();
        user.setEmail(userToSave.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userToSave.getPassword()));

        if(user !=null){{emailService.sendWelcomeEmailTo(user.getEmail());}

        }
        userRepository.save(user);

    }



}

