package ma.anizar.frmw.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot store empty file");
        }

        String filename = generateUniqueFileName(file.getOriginalFilename());
        Path destinationPath = Paths.get(uploadDir).resolve(filename);
        Files.copy(file.getInputStream(), destinationPath);

        return filename;
    }

    private String generateUniqueFileName(String originalFilename) {
        // Assuming originalFilename has format 'name.extension'
        String[] parts = originalFilename.split("\\.(?=[^\\.]+$)");
        String name = parts[0];
        String extension = parts.length > 1 ? parts[1] : "";
        String uniqueID = UUID.randomUUID().toString();

        return name + "_" + uniqueID + (extension.isEmpty() ? "" : "." + extension);
    }
}
