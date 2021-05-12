package backend.picture;

import backend.auth.AuthService;
import backend.user.User;
import backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FileLocationService {

    @Autowired
    private AuthService authService;

    @Autowired
    FileSystemRepository fileSystemRepository;

    @Autowired
    PictureDbRepository pictureDbRepository;

    @Autowired
    UserService userService;


    Long save(byte[] bytes, String pictureName) throws Exception {
        String location = fileSystemRepository.save(bytes, pictureName);

        String userName = authService.getLoggedInUserEmail();
        User owner = userService.findUserByEmail(userName);

        Picture picture = new Picture(pictureName, location);
        picture.setPictureOwner(owner);

        //owner.getPictures().add(picture);

        return pictureDbRepository.save(picture).getId();
    }

    FileSystemResource find(Long pictureId) {
        Picture picture = pictureDbRepository.findById(pictureId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return fileSystemRepository.findInFileSystem(picture.getLocation());
    }
}
