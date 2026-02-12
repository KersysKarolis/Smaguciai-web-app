package org.smaguciai.repositories;

import org.smaguciai.entities.Content;
import org.smaguciai.entities.HomeImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HomeContentRepository extends JpaRepository<Content, String>{
    //Optional<Content> findBySectionKey(String sectionKey);

}
