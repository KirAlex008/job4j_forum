package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.store.UserRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class PostControl {
    private final PostService postService;
    private final UserRepository userRepository;

    public PostControl(PostService postService, UserRepository userRepository) {
        this.postService = postService;
        this.userRepository = userRepository;
    }



    @GetMapping("/create")
    public String create(Model model) {
        List<Post> posts = postService.getAll();
        model.addAttribute("posts", posts);
        return "post/create";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        return "post/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByUsername(userName);
        if (user.isPresent()) {
            post.setUser(user.orElse(null));
        }
        postService.save(post);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/";
    }
}
