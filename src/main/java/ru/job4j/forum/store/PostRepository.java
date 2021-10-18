package ru.job4j.forum.store;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    Post findById(Integer id);
    Post save(Post post);
    List<Post> findAllByUser(User user);
}
