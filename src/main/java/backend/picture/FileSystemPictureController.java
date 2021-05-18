package backend.picture;

import backend.user.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
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

    @DeleteMapping("/pictures/{pictureId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePicture(@PathVariable long pictureId) {
        Picture picture = fileLocationService.pictureDbRepository.findById(pictureId)
                .orElseThrow(ResourceNotFoundException::new);

        // Remove the image from file storage itself
        // storageLocation = picture.getLocation();
        // FileSystem.delete(storageLocation)

        // Check if picture is used as an avatar
        // User owner = picture.getPictureOwner();
        // if owner.avatar == picture {
        //    owner.setAvatar(null)
        // }


        fileLocationService.pictureDbRepository.delete(picture);
    }

    @PostMapping("/pictures/{pictureId}/likes")
    public ResponseEntity<Picture> addPictureLike(@PathVariable Long pictureId) {

        Picture picture = fileLocationService.pictureDbRepository.findById(pictureId)
                .orElseThrow(ResourceNotFoundException::new);
        picture.addLike();
        fileLocationService.pictureDbRepository.save(picture);
        return ResponseEntity.ok(picture);
    }

    @GetMapping("/pictures/{pictureId}/likes")
    public ResponseEntity<Integer> getLike(@PathVariable Long pictureId) {

        Picture picture = fileLocationService.pictureDbRepository.findById(pictureId)
                .orElseThrow(ResourceNotFoundException::new);
        int count = picture.getLikes();
        return ResponseEntity.ok(count);
    }

    @PostMapping("/pictures/{pictureId}/dislikes")
    public ResponseEntity<Picture> addPictureDisLike(@PathVariable Long pictureId){

        Picture picture = fileLocationService.pictureDbRepository
                .findById(pictureId).orElseThrow(ResourceNotFoundException::new);
        picture.addDisLike();
        fileLocationService.pictureDbRepository.save(picture);
        return ResponseEntity.ok(picture);
    }

}
