package backend.picture;

import backend.auth.AuthService;
import backend.user.User;
import backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileLocationService {

    @Autowired
    AuthService authService;

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

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String uploadedDate = formatter.format(timestamp);

        Picture picture = new Picture(pictureName, uploadedDate, location, 0, 0);
        picture.setPictureOwner(owner);

        return pictureDbRepository.save(picture).getId();
    }

    public FileSystemResource find(Long pictureId) {
        Picture picture = pictureDbRepository.findById(pictureId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return fileSystemRepository.findInFileSystem(picture.getLocation());
    }

    public Picture findPicture(Long pictureId) {
        return pictureDbRepository.findById(pictureId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
