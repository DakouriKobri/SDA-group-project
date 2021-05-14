package backend.picture;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;

@Repository
public class FileSystemRepository {

    private final String pictureDirectory = System.getProperty("user.dir") + "/uploads/";

    public void makeDirectoryIfNotExist(String pictureDirectory) {
        File directory = new File(this.pictureDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public String save(byte[] bytes, String fileName) throws IOException {
        makeDirectoryIfNotExist(pictureDirectory);
        Path path = Paths.get(pictureDirectory + new Date().getTime() + "-" + fileName);

        Files.write(path, bytes);

        return path.toAbsolutePath().toString();
    }

    FileSystemResource findInFileSystem(String location) {
        try {
            return new FileSystemResource(Paths.get(location));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
