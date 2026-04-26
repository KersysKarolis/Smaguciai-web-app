package org.smaguciai.services;

import jakarta.transaction.Transactional;
import org.smaguciai.entities.HomeImage;
import org.smaguciai.repositories.HomeImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ImageService {
    private final HomeImageRepository repository;

    public ImageService(HomeImageRepository repository) {
        this.repository = repository;
    }

    @Value("${file.upload-dir}")
    private String uploadDir;
    public Optional<HomeImage> getBySectionAndContentKey(String section, String contentKey){
        return repository.findBySectionAndContentKey(section, contentKey);
    }
    public void saveOrUpdate (String section, String contentKey, MultipartFile file)throws IOException {
        if(file.isEmpty()) return;
        Path uploadPath = Paths.get(uploadDir);
        Files.createDirectories(uploadPath);

        String fileName = UUID.randomUUID() +"_"+ file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        HomeImage image = repository
                .findBySectionAndContentKey(section, contentKey)
                .orElse(new HomeImage(section, null, contentKey));

        image.setFileName("/uploads/" + fileName);
        repository.save(image);
    }

    public void delete (String section, String contentKey){
         repository.deleteBySectionAndContentKey(section, contentKey);
    }

}
