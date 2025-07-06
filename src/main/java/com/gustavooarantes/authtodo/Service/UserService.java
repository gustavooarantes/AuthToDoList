package com.gustavooarantes.authtodo.Service;

import com.gustavooarantes.authtodo.Config.Exception.InvalidPasswordException;
import com.gustavooarantes.authtodo.Config.Exception.PasswordAlreadyInUseException;
import com.gustavooarantes.authtodo.Config.Exception.UserAlreadyExistsException;
import com.gustavooarantes.authtodo.Config.Exception.UserNotFoundException;
import com.gustavooarantes.authtodo.DTO.UpdatePasswordDTO;
import com.gustavooarantes.authtodo.DTO.UserRequestDTO;
import com.gustavooarantes.authtodo.Model.UserModel;
import com.gustavooarantes.authtodo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Sign-up process
    public UserModel signUp(UserRequestDTO userRequestDTO) {

        // Checks if user already exists
        if(userRepository.findByUsername(userRequestDTO.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username already exists");
        } else if (userRepository.findByEmail(userRequestDTO.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        // Hashes the password before saving
        String hashedPassword = passwordEncoder.encode(userRequestDTO.getPassword());

        // Creates the new user
        UserModel newUser = UserModel.builder()
                .username(userRequestDTO.getUsername())
                .email(userRequestDTO.getEmail())
                .password(hashedPassword)
                .build();

        return userRepository.save(newUser);
    }

    // Change password process
    public void updatePassword(String username, UpdatePasswordDTO updatePasswordDTO) {

        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Checks if the password provided is incorrect
        if(!passwordEncoder.matches(updatePasswordDTO.getCurrentPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }

        // Checks if the password provided is the same as the current one
        if(passwordEncoder.matches(updatePasswordDTO.getNewPassword(), user.getPassword())) {
            throw new PasswordAlreadyInUseException("Password must be different");
        }

        // Updates the password
        String newPassword = passwordEncoder.encode(updatePasswordDTO.getNewPassword());
        user.setPassword(newPassword);
        userRepository.save(user);
    }
}
