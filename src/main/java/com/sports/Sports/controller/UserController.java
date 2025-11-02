//package com.sports.Sports.controller;
//
//import com.sports.Sports.Service.UserService;
//import com.sports.Sports.Service.CustomUserDetail;
//import com.sports.Sports.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    // Example: get current authenticated user info
//    @GetMapping("/me")
//    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
//        CustomUserDetail cud = (CustomUserDetail) authentication.getPrincipal();
//        User user = cud.getUser();
//        // Return only necessary fields
//        return ResponseEntity.ok(
//                new java.util.HashMap<String, Object>() {{
//                    put("id", user.getId());
//                    put("name", user.getName());
//                    put("email", user.getEmail());
//                }}
//        );
//    }
//
//    // Example: endpoint only accessible by USER or ADMIN
//    @GetMapping("/profile")
//    public ResponseEntity<?> profile(Authentication authentication) {
//        CustomUserDetail cud = (CustomUserDetail) authentication.getPrincipal();
//        User user = cud.getUser();
//        return ResponseEntity.ok("Hello, " + user.getName() + ". This is your profile.");
//    }
//}
