package backend.user;

import backend.auth.AuthService;
import backend.picture.FileLocationService;
import backend.picture.Picture;
import backend.picture.PictureDbRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    UserRepository userRepository;
    UserService userService;
    FileLocationService fileLocationService;

    private AuthService authService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService, FileLocationService fileLocationService) {
        this.userRepository= userRepository;
        this.userService = userService;
        this.fileLocationService = fileLocationService;
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

    @PutMapping("/users/{userId}/avatar/{pictureId}")
    public ResponseEntity<User> setUserAvatar(@PathVariable Long userId, @PathVariable Long pictureId) {
        User user = userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
        Picture picture = fileLocationService.findPicture(pictureId); // Find the picture by id
        user.setAvatar(picture);
        userRepository.save(user);
        return ResponseEntity.ok(user); // Maybe change return status code


        //picture.setAvatarOwner(user)
       // user.setAvatarOwner()
        //fileLocationService. save(picture)
        // return 201
    }
}