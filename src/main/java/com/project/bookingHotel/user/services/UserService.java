package com.project.bookingHotel.user.services;

import com.project.bookingHotel.user.model.User;
import com.project.bookingHotel.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    /*public ResponseEntity<?> createUser(User user) {
    String email = user.getEmail();

    if (userRepository.existsByEmail(email)) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("O e-mail já está em uso. Por favor, escolha outro.");
    }

    // Se o e-mail não existe, você pode continuar com a criação do usuário
    User createdUser = userRepository.save(user);

    return ResponseEntity.status(HttpStatus.CREATED)
            .body(createdUser);
}*/

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public ResponseEntity<User> findUserById(Long id){
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<User> updateUserById(Long id, User user){
        return userRepository.findById(id)
                .map(userUpdate -> {
                    userUpdate.setEmail(user.getEmail());
                    userUpdate.setName((userUpdate.getName()));
                    userUpdate.setPassword((userUpdate.getPassword()));
                    User updateUser = userRepository.save(user);
                    return ResponseEntity.ok().body(userUpdate);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> deleteUserById(Long id){
        return userRepository.findById(id)
                .map(userDelete -> {
                    userRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
