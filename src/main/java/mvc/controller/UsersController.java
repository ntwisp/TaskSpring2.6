package mvc.controller;

import mvc.model.User;
import mvc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.readAllUsers());
        return "list";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping
    public RedirectView saveUser(@ModelAttribute User user, RedirectAttributes attributes) {
        if (user.getId() == null) {
            userService.createUser(user);
        } else {
            userService.updateUser(user);
        }
        attributes.addFlashAttribute("message", "User saved successfully");
        return new RedirectView("/users");
    }

@GetMapping(value = "/edit", params = "id")
    public String editUser(@RequestParam("id") Integer id,
                               Model model) {
        User user = userService.readUser(id);
        model.addAttribute("user", user);
        return "form";
    }

@GetMapping(value = "/delete")
public RedirectView deleteUser(@RequestParam(value = "id", defaultValue = "") Integer id,
                               RedirectAttributes attributes) {
        userService.deleteUser(id);
        attributes.addFlashAttribute("message", "User deleted successfully");
        return new RedirectView("/users");
    }
}