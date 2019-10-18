package com.quem.arrisca.service;

import com.quem.arrisca.model.User;
import com.quem.arrisca.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;
    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User(1L, "username", "password");
        when(userRepository.save(any(User.class))).thenReturn(user);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveUser() {
        UserService service = new UserService(userRepository);
        User expectedUser = service.saveUser(user);
        assertEquals(expectedUser, user);
    }

}