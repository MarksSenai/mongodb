package com.springboot.mongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.springboot.mongodb.domain.Post;
import com.springboot.mongodb.domain.User;
import com.springboot.mongodb.dto.AuthorDTO;
import com.springboot.mongodb.dto.CommentDTO;
import com.springboot.mongodb.repository.PostRepository;
import com.springboot.mongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        userRepository.deleteAll();
        postRepository.deleteAll();
        User maria = new User(null, "Maria Suzana", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        User roberto = new User(null, "Roberto Souzaa", "roberto@gmail.com");
        User daiana = new User(null, "Daiane Gonzales", "daiane@gmail.com");
        User diego = new User(null, "Diego Pietro", "diego@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob, roberto, daiana, diego));

        Post post1 = new Post(null, sdf.parse("21/03/2020"), "Partiu Viagem",
                "Vou viajar para Sampa, abraços !", new AuthorDTO(maria));

        Post post2 = new Post(null, sdf.parse("22/03/2020"), "Bom dia!",
                "Hoje acordei feliz : )", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem",
                sdf.parse("21/03/2020"), new AuthorDTO(diego));

        CommentDTO c2 = new CommentDTO("Aproveite",
                sdf.parse("21/03/2020"), new AuthorDTO(diego));

        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!",
                sdf.parse("21/03/2020"), new AuthorDTO(diego));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));
        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
}