package backend.picture;

import backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class FileSystemPictureController {

    @Autowired
    FileLocationService fileLocationService;

    @PostMapping("/upload")
    Long uploadPicture(@RequestParam MultipartFile picture) throws Exception {
        return fileLocationService.save(picture.getBytes(), picture.getOriginalFilename());
    }

    @GetMapping("/pictures")
    public ResponseEntity<List<Picture>> listPictures(){
        return ResponseEntity.ok(fileLocationService.pictureDbRepository.findAll());
    }

    @GetMapping("/pictures/{pictureId}")
    FileSystemResource downloadPicture(@PathVariable Long pictureId) throws Exception {
        return fileLocationService.find(pictureId);
    }

}
