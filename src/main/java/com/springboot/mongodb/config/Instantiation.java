package com.springboot.mongodb.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.springboot.mongodb.domain.User;
import com.springboot.mongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        User maria = new User(null, "Maria Suzana", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        User roberto = new User(null, "Roberto Souzaa", "roberto@gmail.com");
        User daiana = new User(null, "Daiane Gonzales", "daiane@gmail.com");
        User diego = new User(null, "Diego Pietro", "diego@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob, roberto, daiana, diego));
    }
}
