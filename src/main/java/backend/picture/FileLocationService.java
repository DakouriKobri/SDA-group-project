package backend.picture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class FileLocationService {

    @Autowired
    FileSystemRepository fileSystemRepository;

    @Autowired
    PictureDbRepository pictureDbRepository;

    Long save(byte[] bytes, String pictureName) throws Exception {
        String location = fileSystemRepository.save(bytes, pictureName);

        return pictureDbRepository.save(new Picture(pictureName, location)).getId();
    }

    FileSystemResource find(Long pictureId) {
        Picture picture = pictureDbRepository.findById(pictureId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return fileSystemRepository.findInFileSystem(picture.getLocation());
    }
}
