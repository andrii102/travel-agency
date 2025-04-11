    package com.epam.finaltask.controller;

    import com.epam.finaltask.service.UserServiceImpl;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;

    @Controller
    @RequestMapping("/admin")
    public class UsersController {

        @Autowired
        private UserServiceImpl userService;

        @GetMapping("/users")
        public String users(Model model) {
            userService.getAllUser();
            model.addAttribute("users", userService.getAllUser());
            return "user/dashboard";
        }

        @PostMapping("/users/block/{id}")
        public String blockUser(@PathVariable String id) {
            userService.blockUser(id);
            return "redirect:/admin/users";
        }

        @PostMapping("/users/unblock/{id}")
        public String unblockUser(@PathVariable String id) {
            userService.unblockUser(id);
            return "redirect:/admin/users";
        }


    }
