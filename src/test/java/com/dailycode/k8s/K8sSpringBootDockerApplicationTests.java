package com.dailycode.k8s;

import com.dailycode.k8s.dao.UserRepository;
import com.dailycode.k8s.model.User;
import com.dailycode.k8s.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class K8sSpringBootDockerApplicationTests {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Test
    public void getUsersTest() {
        when(repository.findAll()).thenReturn(Stream
                .of(getUser()).collect(Collectors.toList()));
        assertEquals(1, service.getUsers().size());
    }

    @Test
    public void saveUserTest() {
        User user = getUser();
        when(repository.save(user)).thenReturn(user);
        assertEquals(user, service.addUser(user));
    }

    @Test
    public void deleteUserTest() {
        User user = getUser();
        service.deleteUser(user.getId());
        verify(repository, times(1)).deleteById(user.getId());
    }

    public User getUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("Malya");
        user.setPassword("test123");
        user.setEmail("test1@gmail.com");
        user.setRole("View");
        return user;
    }
}
