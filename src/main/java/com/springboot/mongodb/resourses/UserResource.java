package com.springboot.mongodb.resourses;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.mongodb.domain.User;
import com.springboot.mongodb.dto.UserDTO;
import com.springboot.mongodb.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> userDTOList = userService.findAll().stream()
                .map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(userDTOList);
    }

    @RequestMapping(value = "/{id}" ,method= RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(new UserDTO(userService.findById(id)));
    }

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody UserDTO userDTO) {
        UserDTO userFrom = new UserDTO();
        User user = userFrom.fromDTO(userDTO);
        userService.create(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}" ,method= RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}" ,method= RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody UserDTO userDTO, @PathVariable String id) {
        UserDTO userFrom = new UserDTO();
        User user = userFrom.fromDTO(userDTO);
        user.setId(id);
        userService.update(user);
        return ResponseEntity.noContent().build();
    }


}