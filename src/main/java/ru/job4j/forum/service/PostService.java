package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PostService {

    private final Map<Integer, Post> posts = new HashMap<>();
    private final AtomicInteger id = new AtomicInteger(0);


    public List<Post> getAll() {
        List<Post> copyOfPosts = new ArrayList<>();
        for (var el : posts.values()) {
            copyOfPosts.add(el);
        }
        return copyOfPosts;
    }

    public Post save(Post post) {
        int currId = id.incrementAndGet();
        post.setId(currId);
        posts.put(post.getId(), post);
        return post;
    }

    public Post findById(Integer id) {
        return posts.get(id);
    }

    public void update(Post post) {
        posts.replace(post.getId(), post);
    }
}
