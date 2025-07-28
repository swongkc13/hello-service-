package com.simon.hello_service.user;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> getAll() {
        return repo.findAll();
    }
  public Optional<User> getById(Long id) {
      return repo.findById(id);
  }

    public User save(User user) {
        return repo.save(user);
    }

    public boolean deleteById(Long id) {
    Optional<User> existingUser = repo.findById(id);
    if (existingUser.isPresent()) {
        repo.deleteById(id);
        return true;
    }
    return false;
    }
}