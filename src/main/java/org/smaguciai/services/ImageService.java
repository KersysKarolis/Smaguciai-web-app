package org.smaguciai.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ImageService {
    private final HomeImageRepository repository;
    private final Cloudinary cloudinary;

    public ImageService(HomeImageRepository repository, Cloudinary cloudinary) {

        this.repository = repository;
        this.cloudinary=cloudinary;
    }

    public Optional<HomeImage> getBySectionAndContentKey(String section, String contentKey){
        return repository.findBySectionAndContentKey(section, contentKey);
    }
    public void saveOrUpdate (String section, String contentKey, MultipartFile file, String title)throws IOException {
        if(file==null|| file.isEmpty()) return;


        HomeImage image = repository
                .findBySectionAndContentKey(section, contentKey)
                .orElse(new HomeImage(section, null, contentKey,title));
        if (image.getPublicId() !=null && !image.getPublicId().isBlank()){
            cloudinary.uploader().destroy(image.getPublicId(), ObjectUtils.emptyMap());
        }

        Map<?,?> uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap("folder", "smaguciai", "resource_type", "image")
        );
        String imageUrl=uploadResult.get("secure_url").toString();
        String publicId =uploadResult.get("public_id").toString();

        image.setPublicId(publicId);
        image.setFileName(imageUrl);
        repository.save(image);
    }

    public void delete (String section, String contentKey){
         repository.findBySectionAndContentKey(section, contentKey).ifPresent(
                 homeImage -> {
                     try{
                         if(homeImage.getPublicId()!=null && !homeImage.getPublicId().isBlank()){

                             cloudinary.uploader().destroy(homeImage.getPublicId(), ObjectUtils.emptyMap());
                         }
                     } catch (IOException e){
                         throw new RuntimeException("Nepavyko istrinti failo is debesies", e);
                     }
                     homeImage.setFileName(null);
                     homeImage.setPublicId(null);
                     repository.save(homeImage);
                 }

         );
    }

}
