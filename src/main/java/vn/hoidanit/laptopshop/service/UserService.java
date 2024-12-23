package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleSaveUser(User user) {
        return this.userRepository.save(user);
    }

    public User handleUpdateUser(User user) {
        return this.userRepository.save(user);
    }

    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public List<User> getAllUserByEmailAndAddress(String email, String Address) {
        return this.userRepository.findByEmailAndAddress(email, Address);
    }

    public User getUserById(long id) {
        return this.userRepository.findById(id);
    }

    public String handleGetHomePage() {
        return "Hello From Service";
    }

    public void handleDeleteUserById(long id) {
        this.userRepository.deleteById(id);
    }
}
