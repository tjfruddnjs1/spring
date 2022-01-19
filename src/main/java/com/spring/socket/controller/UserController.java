package com.spring.socket.controller;

import com.spring.socket.domain.User;
import com.spring.socket.exception.DataNotFoundException;
import com.spring.socket.service.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Secured({"ROLE_ADMIN"})
public class UserController {

    @NonNull
    private final UserService userService;

    @GetMapping("/list")
    public String list(Model model) {
        List<User> userList = userService.findAll();

        model.addAttribute("userList", userList);
        return "user/list";
    }

    @GetMapping({"/edit", "/edit/{id}"})
    public String edit(@PathVariable(name = "id", required = false) Integer id, Model model) {
        User user = (id != null) ?
            userService.findById(id).orElseThrow(DataNotFoundException::new) : new User();

        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/save")
    public String save(Model model, @Valid @ModelAttribute("user") User user, BindingResult result) {
        if (!result.hasErrors()) {
            try {
                userService.save(user);
                return "redirect:/user/list";
            } catch (DataIntegrityViolationException e) {
                result.rejectValue("username", "error.username", "이미 동일한 이메일이 존재합니다.");
            }
        }

        return "user/edit";
    }
}
