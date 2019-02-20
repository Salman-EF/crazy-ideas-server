package com.crazyideas.services;

import com.crazyideas.controllers.IdeaController;
import com.crazyideas.models.Role;
import com.crazyideas.models.Thinker;
import com.crazyideas.repositories.RoleRepository;
import com.crazyideas.repositories.ThinkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;

@Service
public class ThinkerServices {
/*
    private static final Logger logger = LoggerFactory.getLogger(IdeaController.class);
    @Autowired
    ThinkerRepository thinkerRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public Thinker saveThinker(Thinker thinker) {
        thinker.setPassword(bCryptPasswordEncoder.encode(thinker.getPassword()));
        thinker.setEnabled(true);
        Role role = roleRepository.findByRole("USER");
        thinker.setRoles(new HashSet<>(Arrays.asList(role)));
        thinkerRepository.save(thinker);
        logger.info("Save "+thinker.toString());

        return thinker;
    }

    public Thinker updateThinker(Thinker thinker) {
        try {
            thinkerRepository.findById(thinker.getId());
            thinker.setPassword(bCryptPasswordEncoder.encode(thinker.getPassword()));
            thinker.setEnabled(true);
            Role role = roleRepository.findByRole("USER");
            thinker.setRoles(new HashSet<>(Arrays.asList(role)));
            thinkerRepository.save(thinker);
            logger.info("Save "+thinker.toString());
        } catch(NoSuchElementException ex) {
            // Thinker not exists
            logger.warn("Idea: "+thinker.getId()+", not found");
            thinker = null;
        }

        return thinker;
    }

    public boolean deleteThinker(String thinkerId) {
        boolean deletingStatus = false;
        logger.info("Delete "+thinkerId);
        try {
            thinkerRepository.findById(thinkerId);
            thinkerRepository.deleteById(thinkerId);
            deletingStatus = true;
        } catch(NoSuchElementException ex) {
            // Thinker not exists
            logger.warn("Idea: "+thinkerId+", not found");
        }
        return deletingStatus;
    }
    */
}
