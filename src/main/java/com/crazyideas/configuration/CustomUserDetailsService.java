package com.crazyideas.configuration;

import com.crazyideas.models.Role;
import com.crazyideas.models.Thinker;
import com.crazyideas.repositories.ThinkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    ThinkerRepository thinkerRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Thinker thinker = thinkerRepository.findByEmail(email);
        if (thinker != null) {
            List<GrantedAuthority> authorities = getUserAuthority(thinker.getRoles());
            return buildUserForAuthentication(thinker, authorities);
        } else {
            return null;
        }
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(Thinker thinker, List<GrantedAuthority> authorities) {
        return new User(thinker.getEmail(), thinker.getPassword(), authorities);
    }
}
