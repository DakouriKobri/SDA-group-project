package backend.user;

import backend.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    UserRepository userRepository;
    UserService userService;

    private AuthService authService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository= userRepository;
        this.userService = userService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateName(@PathVariable Long id, @RequestBody User updatedName) {
        userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updatedName.setId(id);
        User user = userRepository.save(updatedName);
        return ResponseEntity.ok(updatedName);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
