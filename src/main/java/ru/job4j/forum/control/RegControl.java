package ru.job4j.forum.control;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class RegControl {

    private final UserService userService;

    public RegControl(UserService userService) {
        this.userService = userService;
    }

}
