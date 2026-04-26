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
                new ContentDto("home.about.text", "Home - Aprasymas", null),
                new ContentDto("home.program.text", "Home - Programos aprasymas", null),
                new ContentDto("home.personageUpper.text", "Home - Personazu virsutinis aprasymas", null),
                new ContentDto("home.personageLower.text", "Home - Personazu apatinis aprasymas", null));
    }

    @GetMapping("/imageList")
        public List<HomeImageDto> imageList(){
            return List.of(new HomeImageDto("home.header.image", "Header - nuotrauka POS0", "banner" ),
                    new HomeImageDto("home.about.image", "About - nuotrauka POS0", "about"),
                    new HomeImageDto("home.program.image", "Program - Temininai", "teminiai"),
                    new HomeImageDto("home.program.image", "Program - Muzikiniai", "muzikiniai"),
                    new HomeImageDto("home.program.image", "Program - Ekspermentai", "ekspermentai"),
                    new HomeImageDto("home.program.image", "Program - Burbulinis", "burbulinis"),
                    new HomeImageDto("home.program.image", "Program - Laikinos", "laikinos"),
                    new HomeImageDto("home.program.image", "Program - Veiduku", "veiduku"),
                    new HomeImageDto("home.program.image", "Program - Balionu", "balionu"),
                    new HomeImageDto("home.program.image", "Program - Pabaiga", "pabaiga"),
                    new HomeImageDto("home.program.image", "Program - Pinjata", "pinjata"),
                    new HomeImageDto("home.program.image", "Program - PunchBox", "punchBox"),
                    new HomeImageDto("home.program.image", "Program - Cukraus vata", "sugar"),
                    new HomeImageDto("home.program.image", "Program - Burbulu fiesta", "bubble"),
                    new HomeImageDto("home.personage.image", "Personazas - Liutukas", "liutukas"),
                    new HomeImageDto("home.personage.image", "Personazas - Stitcas", "stitcas"),
                    new HomeImageDto("home.personage.image", "Personazas - Vienarage", "vienarage"),
                    new HomeImageDto("home.personage.image", "Personazas - Drugelis", "drugelis"),
                    new HomeImageDto("home.personage.image", "Personazas - Feja", "feja"),
                    new HomeImageDto("home.personage.image", "Personazas - Treciadiene", "treciadiene"),
                    new HomeImageDto("home.personage.image", "Personazas - Elza", "elza"),
                    new HomeImageDto("home.personage.image", "Personazas - Klouniuke", "klouniuke")
                    );

        }

    @GetMapping("/get")
    public ContentDto getContentDto (@RequestParam String key){
        String value = repository.findById(key).map(Content::getAllContent).orElse("");
        return new ContentDto(key, key, value);


    }
    @PostMapping("/update")
    public void update(@RequestBody ContentDto content){
        repository.save(new Content(content.key(), content.value()));
    }


}
