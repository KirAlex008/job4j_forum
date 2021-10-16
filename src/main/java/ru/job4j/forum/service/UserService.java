package ru.job4j.forum.service;

import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UserService {
    private final Map<Integer, User> users = new HashMap<>();
    private final AtomicInteger id = new AtomicInteger(2);


    public List<User> getAll() {
        List<User> copyOfUsers = new ArrayList<>();
        for (var el : users.values()) {
            copyOfUsers.add(el);
        }
        return copyOfUsers;
    }

    public User save(User user) {
        int currId = id.incrementAndGet();
        user.setId(currId);
        users.put(user.getId(), user);
        return user;
    }

    public User findByName(String name) {
        User user = null;
        for (var el : users.values()) {
            if (el.getUsername().equals(name)) {
                user = el;
            }
        }
            return user;
    }
}
