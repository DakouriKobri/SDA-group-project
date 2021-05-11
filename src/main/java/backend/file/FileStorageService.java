/*
package backend.file;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


@Service
public class FileStorageService {

    @Autowired
    private FileSystemRepository fileSystemRepository;

    @Autowired
    private FileDBRepository fileDBRepository;

    long store(byte[] bytes, String fileName) throws Exception {
        String location = fileSystemRepository.store(bytes, fileName);

        return fileDBRepository.save(new FileDB(fileName, likes, location)).getId();
    }

    FileSystemResource find(Long fileId){
        FileDB file = fileDBRepository.findById(fileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return fileSystemRepository.findInFileSystem(file.getLocation());
    }

*/
/*    public FileDB store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        //FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes(), "0");
        FileDB FileDB = new FileDB(fileName, file.getContentType(), "0");
        return fileDBRepository.save(FileDB);
    }*//*


   */
/* public void makeDirectoryIfNotExist() {
        String imageDirectory = System.getProperty("user.dir") + "/uploads/";
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
    public void store(MultipartFile file) throws IOException {
        String imageDirectory = System.getProperty("user.dir") + "/uploads/";
        byte[] bytes = file.getBytes();
        Path path = Paths.get(imageDirectory + file.getOriginalFilename());
        Files.write(path, bytes);
    }*//*


    public FileDB getFile(Long id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    public FileDB findById(Long id) {
        return fileDBRepository.findById(id).get();
    }
    
}


*/
