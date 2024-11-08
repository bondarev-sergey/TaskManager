package com.example.TaskManager.service.impl;

import com.example.TaskManager.dto.CreateUserDto;
import com.example.TaskManager.dto.UserDto;
import com.example.TaskManager.entity.User;
import com.example.TaskManager.repository.UserRepository;
import com.example.TaskManager.security.SecurityUser;
import com.example.TaskManager.security.UserDetailsManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsManager {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    @Override
    public UserDto createUser(CreateUserDto userDto) {
        User user = new User();
        user.setUsername(userDto.username());
        user.setAuthority(userDto.authority());
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setEmail(userDto.email());
        user.setPassword(passwordEncoder.encode(userDto.password()));

        User savedUser = userRepository.save(user);
        return toDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAllByOrderByUsernameAsc().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(this::toDto)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public UserDto updateUser(String username, UserDto userDto) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setUsername(userDto.username());
        user.setAuthority(userDto.authority());
        user.setEmail(userDto.email());
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        User updatedUser = userRepository.save(user);
        return toDto(updatedUser);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public void changePassword(String username, String newPassword) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    private UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getAuthority(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
    }
}
