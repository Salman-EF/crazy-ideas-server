package com.crazyideas.controllers;

import com.crazyideas.models.Thinker;
import com.crazyideas.repositories.RoleRepository;
import com.crazyideas.repositories.ThinkerRepository;
import com.crazyideas.services.ThinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    ThinkerRepository thinkerRepository;
    @Autowired
    ThinkerServices thinkerServices;
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

 /*   @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Thinker Thinker) {
        // Checking first if the email already exists
        if (thinkerRepository.findByEmail(Thinker.getEmail()) != null) {
            return ResponseEntity.ok("error-email");
        } else {
            // Register the user and userServices saveUser will take care of encrypting the password
            Thinker ThinkerSaved = thinkerServices.saveThinker(Thinker);
            // Generate and return access_token automatically after successfully register
            if (ThinkerSaved!=null) {
                String jwt = JWTAuthenticationFilter.generateToken(Thinker.getEmail());
                return ResponseEntity.ok().body(jwt);
            }
            return ResponseEntity.ok("error-other");
        }
    }*/
}
