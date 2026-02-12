package org.smaguciai.controllers;
import org.smaguciai.entities.Content;
import org.smaguciai.repositories.HomeContentRepository;
import org.smaguciai.repositories.HomeImageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        //model.addAttribute("headerImage", imageRepository.findBySectionAndPosition("home.header.image", 0));
        //model.addAttribute("aboutImage", imageRepository.findBySectionAndPosition("home.about.image",0));

        model.addAttribute("images", Map.of("header", imageRepository.findBySectionOrderByPosition("home.header.image"),
                                                        "about", imageRepository.findBySectionOrderByPosition("home.about.image")));

        return "home";
    }
}
