package mvc.controller;

import mvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import mvc.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"", "/", "list"})
    public String userList(Model model,
                           @ModelAttribute("flashMessage") String flashAttribute) {
        model.addAttribute("users", userService.readAllUsers());
        return "list";
    }

    @GetMapping(value = "/create")
    public String addUserForm(@ModelAttribute("user") User user) {
        return "form";
    }

    @GetMapping(value = "/edit", params = "id")
    public String edidtUserForm(@RequestParam("id") int id,
                                RedirectAttributes attributes, Model model) {
        try {
            model.addAttribute("user", userService.readUser(id));
        } catch (NumberFormatException | NullPointerException e) {
            attributes.addFlashAttribute("flashMessage", "User are not exists!");
            return "redirect:/users/list";
        }
        return "form";
    }

    @PostMapping
    public RedirectView saveUser(@ModelAttribute("user") User user,
                                 RedirectAttributes attributes) {
        if (null == user.getId()) {
            userService.createUser(user);
        } else {
            userService.updateUser(user);
        }

        attributes.addFlashAttribute("flashMessage", "User " + user.getFirstName() + " successfully created!");
        return new RedirectView("/users/list");
    }

    @GetMapping(value = "/delete")
    public RedirectView deleteUser(@RequestParam(value = "id", defaultValue = "") String id,
                                   RedirectAttributes attributes) {
        try {
            User user = userService.deleteUser(Integer.parseUnsignedInt(id));
            attributes.addFlashAttribute("flashMessage", "User " + user.getFirstName() + " successfully deleted!");
        } catch (NumberFormatException | NullPointerException e) {
            attributes.addFlashAttribute("flashMessage", "User are not exists!");
        }

        return new RedirectView("/users/list");
    }
}
