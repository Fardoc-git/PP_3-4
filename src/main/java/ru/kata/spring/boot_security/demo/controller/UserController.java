package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

//    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public ModelAndView startAdmin(Principal currentUser) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("curUser", userService.findByLastName(currentUser.getName()));
        modelAndView.addObject("users", userService.findAll());
        modelAndView.addObject("roles", roleService.findAllRoles());
        modelAndView.addObject("newUser", new User());
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @PostMapping("/admin/createUser")
    public ModelAndView createUser(@ModelAttribute("newUser") User user) {
        ModelAndView modelAndView = new ModelAndView();
        userService.saveUser(user);
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }


    @PostMapping("/admin/updateUser")
    public ModelAndView updateUser(User user) {
        ModelAndView modelAndView = new ModelAndView();
        userService.saveUser(user);
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }


    @PostMapping("/admin/deleteUser")
    public ModelAndView deleteUser(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView();
        userService.deleteById(id);
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView startUser(Principal currentUser) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("curUser", userService.findByLastName(currentUser.getName()));
        modelAndView.setViewName("user");
        return modelAndView;
    }
}