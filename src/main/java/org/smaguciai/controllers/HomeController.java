package org.smaguciai.controllers;
import org.smaguciai.entities.Content;
import org.smaguciai.entities.HomeImage;
import org.smaguciai.repositories.HomeContentRepository;
import org.smaguciai.repositories.HomeImageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    private final HomeContentRepository repository;
    private final HomeImageRepository imageRepository;
public HomeController(HomeContentRepository repository, HomeImageRepository imageRepository){
    this.repository=repository;
    this.imageRepository=imageRepository;
}
    @GetMapping("/")
    public String home(Model model){





       model.addAttribute("heroTitle",
               repository.findById("home.hero.title").map(Content::getAllContent).orElse("Personazai Vaikams"));
       model.addAttribute("aboutText",
       repository.findById("home.about.text").map(Content::getAllContent).orElse("Apie mus skilti galima redaguoti per ADMIN panele"));
       model.addAttribute("programText", repository.findById("home.program.text").map(Content::getAllContent).orElse("Programos aprasymas koreguojamas per ADMIN panele"));
       model.addAttribute("personageUpper", repository.findById("home.personageUpper.text").map(Content::getAllContent).orElse("Personazu aprasymas koreguojamas per ADMIN"));
        model.addAttribute("personageLower", repository.findById("home.personageLower.text").map(Content::getAllContent).orElse("Personazu aprasymas koreguojamas per ADMIN"));
       //model.addAttribute("headerImage", imageRepository.findBySectionAndPosition("home.header.image", 0));
       //model.addAttribute("aboutImage", imageRepository.findBySectionAndPosition("home.about.image",0));

    //  model.addAttribute("images", Map.of("header", imageRepository.findBySectionOrderByContentKey("home.header.image"),
      //                                                "about", imageRepository.findBySectionOrderByContentKey("home.about.image")));
    //                                                   "program", imageRepository.findBySectionOrderByPosition("home.program.image")));
        // map metodas
        Map<String, Map<String, HomeImage>> images = new HashMap<>();
        List<HomeImage> all = imageRepository.findAll();
        for(HomeImage img: all){
            images.computeIfAbsent(img.getSection(), k->new HashMap<>())
                    .put(img.getContentKey(), img);
        }
        model.addAttribute("images", images);
        return "redirect:/login";
    }
}
