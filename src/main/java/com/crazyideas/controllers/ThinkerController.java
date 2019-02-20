package com.crazyideas.controllers;

import com.crazyideas.models.Thinker;
import com.crazyideas.repositories.ThinkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ThinkerController {

    private static final Logger logger = LoggerFactory.getLogger(ThinkerController.class);

    @Autowired
    ThinkerRepository thinkerRepository;

    @GetMapping("/thinkers")
    public List<Thinker> getAllThinkers(){
        logger.info("Get all Thinkers");
        return thinkerRepository.findAll();
    }
    /*@GetMapping("/thinkers/me")
    public ResponseEntity<Thinker> getCurrentUser() {
        String authUsername = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Thinker appThinker = thinkerRepository.findByEmail(authUsername);
        logger.info("Current user: "+appThinker);
        return ResponseEntity.ok(appThinker);
    }*/
    @GetMapping("/thinkers/{thinker}")
    public Thinker getThinker(@PathVariable String thinker) {
        logger.info("Get thinker: "+thinker);
        Thinker thinkerToFind = null;
        try{
            thinkerToFind = thinkerRepository.findById(thinker).get();
        } catch(NoSuchElementException ex){
            // ThinkerId not exists let's try with email
            thinkerToFind = thinkerRepository.findByEmail(thinker);
        }
        return thinkerToFind;
    }
    @PostMapping("/thinkers")
    public Thinker saveThinker(@RequestBody Thinker thinker){
        Thinker thinkerSaved = thinkerRepository.save(thinker);
        logger.info("Save "+thinkerSaved.toString());
        return thinkerSaved;
    }
    @PutMapping("/thinkers/{thinkerId}")
    public Thinker updateThinker(@PathVariable String thinkerId, @RequestBody Thinker thinker){
        logger.info("Update "+thinkerId);
        try{
            Thinker thinkerToUpdate = thinkerRepository.findById(thinkerId).get();
            thinker.setId(thinkerToUpdate.getId());
            thinker = thinkerRepository.save(thinker);
        } catch(NoSuchElementException ex){
            // Thinker not exists
            logger.warn("Thinker: "+thinkerId+", not found");
            thinker = null;
        }
        return thinker;
    }
    @DeleteMapping("/thinkers/{thinkerId}")
    public boolean deleteThinker(@PathVariable String thinkerId){
        logger.info("Delete "+thinkerId);
        boolean deletingStatus = false;
        try{
            thinkerRepository.findById(thinkerId).get();
            thinkerRepository.deleteById(thinkerId);
            deletingStatus = true;
        } catch(NoSuchElementException ex){
            // Thinker not exists
            logger.warn("Thinker: "+thinkerId+", not found");
        }

        return deletingStatus;
    }
}
