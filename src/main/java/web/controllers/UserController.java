package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.models.Role;
import web.models.User;
import web.service.RoleService;
import web.service.UserService;

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

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/admin/add")
    public String addNewUser(Model model) {
            model.addAttribute("user", new User());
            model.addAttribute("allRoles", roleService.getAll());
        return "add";
    }


    @PostMapping("/admin/add")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(name ="allRoles", required = false) String[] roles) {
        if (roles != null) {
            Set<Role> rolesSet = new HashSet<>();
            for (String role : roles) {
                rolesSet.add(roleService.findRoleByName(role));
            }
            user.setRoles(rolesSet);
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }


    @GetMapping("/admin/edit")
    public String edit(@RequestParam(name = "id") int id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("allRoles", roleService.getAll());
        return "edit";
    }

    @PostMapping("/admin/edit")
    public String editUser(@ModelAttribute("user") User user,
                           @RequestParam(name = "allRoles", required = false) String[] roles) {
        if (roles != null) {
            Set<Role> rolesSet = new HashSet<>();
            for (String role : roles) {
                rolesSet.add(roleService.findRoleByName(role));
            }
            user.setRoles(rolesSet);
        }
        userService.editUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete")
    public String delete(@RequestParam(name = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String userInfo(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "userInfo";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
}
