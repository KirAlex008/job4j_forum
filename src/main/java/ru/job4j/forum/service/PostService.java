package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.store.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository posts;

    public PostService(PostRepository posts) {
        this.posts = posts;
    }

    public List<Post> getAll() {
        List<Post> rsl = new ArrayList<>();
        posts.findAll().forEach(rsl::add);
        return rsl;
    }

    public Post findById(Integer id) {
        return posts.findById(id);
    }

    public Post save(Post post) {
        posts.save(post);
        return post;
    };

    public List<Post> findAllByUser(User user) {
        List<Post> rsl = new ArrayList<>();
        posts.findAllByUser(user).forEach(rsl::add);
        return rsl;
    }


}
