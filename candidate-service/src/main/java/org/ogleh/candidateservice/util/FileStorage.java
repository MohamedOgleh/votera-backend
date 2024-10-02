package org.ogleh.candidateservice.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Configuration
public class FileStorage {

    public static String addFile(MultipartFile image, String directory) throws IOException {

        if (!image.isEmpty()) {
            validateFile(image);

            Path dirPath = Paths.get(directory);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            String fileName = UUID.randomUUID() + "." + getFileExtension(image.getOriginalFilename());
            Path imagePath = dirPath.resolve(fileName);

            image.transferTo(imagePath.toFile());

            return fileName;
        }
        return "NAN";
    }

    public static byte[] findImage(String filePath, String directory) throws IOException {
        Path path = getPath(filePath, directory);
        return Files.readAllBytes(path);
    }

    public static void deleteImage(String filePath, String directory) throws IOException {
        Path path = getPath(filePath, directory);
        Files.deleteIfExists(path);
    }

    public static String updateImage(String filePath, String directory, MultipartFile profile) throws IOException {
        //Add the new image before deleting the prev image because if the adding image is field the prev image not fail
        String newFileName = addFile(profile, directory);
        deleteImage(filePath, directory);
        return newFileName;
    }

    private static Path getPath(String filePath, String directory) throws FileNotFoundException {
        Path path = Paths.get(directory, filePath);
        if (!Files.exists(path)) {
            throw new FileNotFoundException("Image not found: " + filePath);
        }
        return path;
    }

    private static String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            throw new IllegalArgumentException("Invalid image name");
        }
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }

    private static void validateFile(MultipartFile image) {
//        if (image.isEmpty()) {
//            throw new IllegalArgumentException("Image is not exist");
//        }
        String contentType = image.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Only image files are allowed");
        }
    }
}