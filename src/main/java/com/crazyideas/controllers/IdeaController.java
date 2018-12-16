package com.crazyideas.controllers;

import com.crazyideas.models.Idea;
import com.crazyideas.repositories.IdeaRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class IdeaController {

    private static final Logger logger = LoggerFactory.getLogger(IdeaController.class);

    @Autowired
    IdeaRepository ideaRepository;
    @GetMapping("/ideas")
    public List<Idea> getIdeas(){
        logger.info("Get all ideas");
        return ideaRepository.findAll();
    }
    @GetMapping("/ideas/{ideaId}")
    public Idea getIdea(@PathVariable ObjectId ideaId) throws ConversionFailedException {
        logger.info("Get idea: "+ideaId);
        Idea idea = new Idea();
        try{
            idea = ideaRepository.findById(ideaId).get();
        } catch(NoSuchElementException ex){
            // Idea not exists
            logger.warn("Idea: "+ideaId+", not found");
            idea = null;
        }
        catch(ConversionFailedException ex){
            // invalid hexadecimal representation of an ObjectId
            logger.warn("invalid hexadecimal representation of an ObjectId");
            idea = null;
        }
        return idea;
    }
    @PostMapping("/ideas")
    public Idea saveIdea(@RequestBody Idea idea){
        logger.info("Save "+idea.toString());
        ideaRepository.save(idea);
        return idea;
    }
    @PutMapping("/ideas/{ideaId}")
    public Idea updateIdea(@PathVariable ObjectId ideaId, @RequestBody Idea idea){
        logger.info("Update "+ideaId);
        try{
            Idea ideaToUpdate = ideaRepository.findById(ideaId).get();
            idea.setId(ideaToUpdate.getId());
            idea = ideaRepository.save(idea);
        } catch(NoSuchElementException ex){
            // Idea not exists
            logger.warn("Idea: "+ideaId+", not found");
            idea = null;
        }
        return idea;
    }
    @DeleteMapping("/ideas/{ideaId}")
    public boolean deleteIdea(@PathVariable ObjectId ideaId){
        logger.info("Delete "+ideaId);
        boolean deletingStatus = false;
        try{
            ideaRepository.findById(ideaId).get();
            ideaRepository.deleteById(ideaId);
            deletingStatus = true;
        } catch(NoSuchElementException ex){
            // Idea not exists
            logger.warn("Idea: "+ideaId+", not found");
        }

        return deletingStatus;
    }
}