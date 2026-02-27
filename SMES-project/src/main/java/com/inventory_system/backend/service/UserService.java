package com.inventory_system.backend.service;
import com.inventory_system.backend.dto.UserDTO;
import com.inventory_system.backend.entity.Product;
import com.inventory_system.backend.entity.User;
import com.inventory_system.backend.entity.UserRole;
import com.inventory_system.backend.repo.UserRepository;
import com.inventory_system.backend.security.UserData;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder){
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Transactional
    public User getById(UUID id){
        User user = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("Cant find user by id."));
        user.getAddedProducts().size();
        return user;
    }


    public void save(User user){
        this.userRepository.save(user);
    }
    public void signup(UserDTO userDTO, String role) {
        if(role.equals("USER")) this.userRepository.save(this.mapUser(userDTO, UserRole.USER));
        else if(role.equals("ADMIN")) this.userRepository.save(this.mapUser(userDTO, UserRole.ADMIN));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Username not found"));
        return new UserData(user.getId(), user.getUsername(), user.getPassword(), user.getRole(), user.isActive());
    }

    private User mapUser(UserDTO userDTO, UserRole role){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setActive(true);
        user.setRole(role);
        user.setAge(35);
        user.setCreatedOn(LocalDate.now());
        user.setUpdatedOn(LocalDate.now());
        user.setAddedProducts(new ArrayList<Product>());
        return user;
    }
}
