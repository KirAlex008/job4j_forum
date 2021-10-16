package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostControl {
    private final PostService postService;

    public PostControl(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<Post> posts = new ArrayList<>();
        postService.getAll().forEach(posts::add);
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
        postService.save(post);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Post post) {
        postService.update(post);
        return "redirect:/";
    }
}
