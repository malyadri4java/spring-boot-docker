package com.dailycode.k8s.service;

import com.dailycode.k8s.dao.UserRepository;
import com.dailycode.k8s.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository repository;

    @CachePut(value = "devCache", key = "#result.id")
    //@CachePut(key = "'allUser'", value = "devCache")
    public User addUser(User user) {
        User newUser = repository.save(user);
        log.info("User added into database successfully...");
        return newUser;
    }

    @CachePut(value = "devCache", key = "#user.id")
    public User updateUser(User user) {
        log.info("User updating into database with userId {}", user.getId());
        return repository.save(user);
    }

    @CacheEvict(value = "devCache", key = "#userId")
    public String deleteUser(Integer userId) {
        log.info("Deleting the user from database with userId {}", userId);
        repository.deleteById(userId);
        return "Deleted user with Id :" + userId;
    }

    //@Cacheable(value = "devCache", keyGenerator = "customKeyGenerator")
    @Cacheable(key = "'allUser'", value = "devCache")
    public List<User> getUsers() {
        log.info("Fetching all users from database ...");
        return repository.findAll();
    }

    @Cacheable(value = "devCache", key = "#userId")
    public User getUser(Integer userId) {
        log.info("Fetching user from database by userId {}", userId);
        Optional<User> user = repository.findById(userId);
        if (user.isPresent()) return user.get();
        return new User();
    }

    @Cacheable(value = "devCache", key = "#username")
    public User getUserByName(String username) {
        log.info("Fetching user from database by username {}", username);
        return repository.findByUsername(username);
    }

    @CacheEvict(value = "devCache", key = "'allUser'")
    public String evictAllAuthorsCached() {
        log.info("Evicted All Authors Cached from : devCache");
        return "Evicted All Authors Cached from : devCache";
    }
}
