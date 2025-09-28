package com.payment.upi.security;

import com.payment.upi.model.User;
import com.payment.upi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsernamePasswordAuthenticator implements AuthenticationProvider {

    @Autowired
    UserRepository registerRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println(username + " " + password);
        User user = registerRepository.findByUsername(username);
        System.out.println("user: "  + user);
        if (user != null && user.getId() > 0
                && username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            System.out.println("loggedin");
                return new UsernamePasswordAuthenticationToken(user.getUsername(), password, getGrantedAuthorities(user.getRoles()));
        } else
            throw new BadCredentialsException("Invalid Credentials");
    }

    private List<GrantedAuthority> getGrantedAuthorities(String roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+roles));
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
