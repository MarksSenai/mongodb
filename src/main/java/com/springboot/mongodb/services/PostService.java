package com.springboot.mongodb.services;

import java.util.Date;
import java.util.List;
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

    public List<Post> findByTitle (String text) {
        return postRepository.findByTitleContainingIgnoreCase(text);
    }

    public List<Post> searchTitle (String text) {
        return postRepository.searchTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return  postRepository.fullSearch(text, minDate, maxDate);
    }
}