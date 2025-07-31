/**
 * 
 */
package com.demo.controller;

/**
 * @author PrasadBonam
 *
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        if ("admin".equals(username) && "admin".equals(password)) {
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok("Bearer " + token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}


/**
 * http://localhost:8080/auth/login?username=admin&password=admin
 * POST http://localhost:8080/auth/login
Body: form-data or query params
- username: admin
- password: admin
*/
