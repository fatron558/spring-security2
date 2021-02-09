package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.models.LoginAndPassword;
import web.models.Role;
import web.models.User;
import web.service.RoleService;
import web.service.UserService;
import web.service.UserServiceImpl;

import java.util.*;


@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/users/add")
    public String addNewUser(Model model) {
            model.addAttribute("user", new User());
            model.addAttribute("login", new LoginAndPassword());
            model.addAttribute("allRoles", roleService.getAll());
        return "add";
    }


    @PostMapping("/users/add")
    public String saveUser(@ModelAttribute("user") User user, @ModelAttribute("login") LoginAndPassword login) {
//        Set<Role> roles = user.getLoginAndPassword().getRoles();
//        Set<Role> newRoles = new HashSet<>();
//        for (Role role : roles) {
//            newRoles.add(roleService.getRole(role.getId()));
//        }
//        user.getLoginAndPassword().setRoles(newRoles);
        userService.saveUser(user);
        return "redirect:/users";
    }


    @GetMapping("/edit")
    public String edit(@RequestParam(name = "id") int id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.editUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "hello";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
}
