package com.crazyideas.services;

import com.crazyideas.models.Role;
import com.crazyideas.models.Thinker;
import com.crazyideas.repositories.RoleRepository;
import com.crazyideas.repositories.ThinkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

public class ThinkerServices implements UserDetailsService {

    @Autowired
    ThinkerRepository thinkerRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveThinker(Thinker thinker) {
        thinker.setPassword(bCryptPasswordEncoder.encode(thinker.getPassword()));
        thinker.setEnabled(true);
        Role role = roleRepository.findByRole("USER");
        thinker.setRoles(new HashSet<>(Arrays.asList(role)));
        thinkerRepository.save(thinker);
    }

    @Override
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
        return new org.springframework.security.core.userdetails.User(thinker.getEmail(), thinker.getPassword(), authorities);
    }
}
