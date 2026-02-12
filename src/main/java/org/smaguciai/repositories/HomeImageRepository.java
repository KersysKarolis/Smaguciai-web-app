package org.smaguciai.repositories;

import org.smaguciai.entities.HomeImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HomeImageRepository extends JpaRepository<HomeImage, Long> {
    Optional<HomeImage> findFirstBySection(String section);
    List<HomeImage> findBySectionOrderByPosition(String section);
    Optional<HomeImage> findBySectionAndPosition(String section, int position);
    void deleteBySectionAndPosition(String section, int position);
}
