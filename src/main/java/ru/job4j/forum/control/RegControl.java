package ru.job4j.forum.control;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.store.AuthorityRepository;
import ru.job4j.forum.store.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class RegControl {

    private final PasswordEncoder encoder;
    private final UserRepository users;
    private final AuthorityRepository authorities;

    public RegControl(PasswordEncoder encoder, UserRepository users, AuthorityRepository authorities) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
    }

    @PostMapping("/reg")
    public String save(@ModelAttribute User user, HttpServletRequest req) {
        Optional<User> usersExistence = users.findByUsername(user.getUsername());
        if (usersExistence.isPresent()) {
            HttpSession sc = req.getSession();
            String errorMessage = "Try another user name, this name is taken !!";
            sc.setAttribute("errorMessageReg", errorMessage);
            return "redirect:/reg";
        } else {
            user.setEnabled(true);
            user.setPassword(encoder.encode(user.getPassword()));
            user.setAuthority(authorities.findByAuthority("ROLE_USER"));
            users.save(user);
            return "redirect:/login";
        }
    }

    @GetMapping("/reg")
    public String reg() {
        return "reg";
    }
}
