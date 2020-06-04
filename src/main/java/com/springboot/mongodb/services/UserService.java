package com.springboot.mongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mongodb.domain.User;
import com.springboot.mongodb.repository.UserRepository;
import com.springboot.mongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
         Optional<User> user = userRepository.findById(id);
         return user.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    public User create(User user) {
        return userRepository.insert(user);
    }

    public void delete(String id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public User update(User user) {
        User newUser = findById(user.getId());
        updateData(newUser, user);
        return  userRepository.save(newUser);

    }

    private void updateData(User newUser, User user) {
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
    }
}
