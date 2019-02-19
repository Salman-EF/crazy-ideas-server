package com.crazyideas.controllers;

import com.crazyideas.repositories.RoleRepository;
import com.crazyideas.repositories.ThinkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    ThinkerRepository thinkerRepository;
    @Autowired
    RoleRepository roleRepository;

//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateThinker(@RequestBody Thinker thinker) {
//
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(thinker.getEmail(),thinker.getPassword())
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        return ResponseEntity.ok(thinker);
//    }
}
