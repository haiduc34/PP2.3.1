package web.controller;

import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String indexPage(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.userService.listUsers());

        return "users";
    }

    @GetMapping(value = "/users")
    public String listUsers(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.userService.listUsers());

        return "users";
    }

    @PostMapping(value = "/users/add")
    public String addUser(@RequestParam ("userName") String userName, @RequestParam("email") String email, @RequestParam("age") Integer age){
        userService.addUser(new User(userName, email, age));
        return "redirect:/users";
    }

    @PostMapping("/users/remove")
    public String removeUser(@RequestParam("id") Integer id, Model model){

        userService.removeUser(id);

        return "redirect:/users";
    }

    @GetMapping("/users/edit")
    public String editUser(@PathVariable("id") Integer id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("listUsers", userService.listUsers());

        return "redirect:/users";
    }

    @GetMapping("/users/update")
    public String userData(Model model, @RequestParam("id") Integer id){
                model.addAttribute("user", this.userService.getUserById(id));
        return "updateUser";
    }

    @PostMapping("/users/update")
    public String userData(Model model, @RequestParam("id") Integer id, @RequestParam("userName")
            String userName, @RequestParam("email") String email, @RequestParam int age){
       this.userService.updateUser(new User(id, userName, email, age));
        return "redirect:/users";
    }
}
