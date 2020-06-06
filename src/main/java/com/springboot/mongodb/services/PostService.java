package com.springboot.mongodb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mongodb.domain.Post;
import com.springboot.mongodb.repository.PostRepository;
import com.springboot.mongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    public Post findById(String id) {
         Optional<Post> post = postRepository.findById(id);
         return post.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }
}