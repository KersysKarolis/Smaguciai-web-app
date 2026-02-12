package org.smaguciai.controllers;

import org.smaguciai.entities.Content;
import org.smaguciai.entities.HomeImage;
import org.smaguciai.events.ContentDto;
import org.smaguciai.events.HomeImageDto;
import org.smaguciai.repositories.HomeContentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/content")
public class ContentController {

    private final HomeContentRepository repository;

    public ContentController(HomeContentRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/list")
    public List<ContentDto> list(){
        return List.of(new ContentDto("home.hero.title", "Home - Hero pavadinimas", null),
                new ContentDto("home.about.text", "Home - Aprasymas", null));
    }

    @GetMapping("/imageList")
        public List<HomeImageDto> imageList(){
            return List.of(new HomeImageDto("home.header.image", "Header - nuotrauka", 0 ),
                    new HomeImageDto("home.header.image", "Header - nuotrauka", 1),
                    new HomeImageDto("home.about.image", "About - nuotrauka", 0)
                    );

        }

    @GetMapping("/")
    public ContentDto getContentDto (@RequestParam String key){
        String value = repository.findById(key).map(Content::getAllContent).orElse("");
        return new ContentDto(key, key, value);


    }
    @PostMapping("/update")
    public void update(@RequestBody ContentDto content){
        repository.save(new Content(content.key(), content.value()));
    }


}
