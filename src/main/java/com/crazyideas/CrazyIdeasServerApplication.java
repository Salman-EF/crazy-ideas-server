package com.crazyideas;

import com.crazyideas.models.Idea;
import com.crazyideas.models.Thinker;
import com.crazyideas.repositories.IdeaRepository;
import com.crazyideas.repositories.ThinkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootApplication
public class CrazyIdeasServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrazyIdeasServerApplication.class, args);
	}

}

