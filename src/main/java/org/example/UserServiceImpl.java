package org.example;

import org.example.userservice.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserServiceImpl implements UserService {

    private final Map<Long, User> userMap = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public User createUser(User user) {
        long id = idCounter.incrementAndGet();
        user.setId(id);
        userMap.put(id, user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public User updateUser(Long id, User user) {
        if (userMap.containsKey(id)) {
            user.setId(id);
            userMap.put(id, user);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        return userMap.remove(id) != null;
    }
}
