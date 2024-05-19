package com.openclassroom.configuration;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.openclassroom.model.DBUser;
import com.openclassroom.repository.DBUserRepository;

@Service
public class CustumUserDetailsService implements UserDetailsService {
    @Autowired
    private DBUserRepository dbUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DBUser user = dbUserRepository.findByUsername(username);

        if(user!= null){
            return new User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user.getRole()));
        }
       return null;
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
}