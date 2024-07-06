package com.civa.futbolista_posicion.service.impl;

import com.civa.futbolista_posicion.dto.auth.AuthLoginRequestDto;
import com.civa.futbolista_posicion.dto.auth.AuthResponseDto;
import com.civa.futbolista_posicion.entity.UserEntity;
import com.civa.futbolista_posicion.repository.UserRepository;
import com.civa.futbolista_posicion.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(JwtUtils jwtUtils, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);

        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
            user.getRoles().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
            return new User(user.getEmail(), user.getPassword(), authorityList);
        } else {
            throw new UsernameNotFoundException("No se ha encontrado un email");
        }
    }


    public AuthResponseDto login(AuthLoginRequestDto authLoginRequestDto) {
        String email = authLoginRequestDto.getEmail();
        String password = authLoginRequestDto.getPassword();

        Authentication authentication = this.authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.createToken(authentication);
        AuthResponseDto responseDto = new AuthResponseDto();
        responseDto.setEmail(email);
        responseDto.setMessage("Authentication Successful");
        responseDto.setJwt(accessToken);
        responseDto.setStatus(true);
        return responseDto;
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = this.loadUserByUsername(email);

        if (userDetails == null) throw new BadCredentialsException("Incorrect username or password");
        if (!passwordEncoder.matches(password, userDetails.getPassword()))
            throw new BadCredentialsException("Incorrect password");
        return new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());
    }
}
