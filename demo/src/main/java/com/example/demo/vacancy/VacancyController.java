package com.example.demo.vacancy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class VacancyController {
    @Autowired
    private VacancyService vacancyService;

//    @GetMapping("/vacancies")
//    public String vacancies(Model model) {
//        model.addAttribute("vacancies", vacancyService.getAllTasks());
//        return "vacancies";
//    }
    @GetMapping("/vacancies")
    public String getAllVacancies(@RequestParam(value = "search", required = false) String search,
                                  @RequestParam(value = "jobType", required = false) String jobType,
                                  @RequestParam(value = "minSalary", required = false) Double minSalary,
                                  @RequestParam(value = "maxSalary", required = false) Double maxSalary,
                                  Model model) {
        List<Vacancy> vacancies = vacancyService.getFilteredVacancies(search, jobType, minSalary, maxSalary);
        model.addAttribute("vacancies", vacancies);
        model.addAttribute("search", search);
        model.addAttribute("jobType", jobType);
        model.addAttribute("minSalary", minSalary);
        model.addAttribute("maxSalary", maxSalary);
        return "vacancies";
    }

    @GetMapping("/vacancies/new")
    public String newVacancy(Model model) {
        model.addAttribute("vacancy", new Vacancy());
        return "createVacancy";
    }

    @PostMapping("/vacancies")
    public String saveVacancy(@ModelAttribute Vacancy vacancy, @RequestParam("image") MultipartFile image) {
        if (!image.isEmpty()) {
            String imagePath = saveImage(image);
            vacancy.setImagePath(imagePath);
        }
        vacancyService.saveVacancy(vacancy);
        return "redirect:/vacancies";
    }

    @GetMapping("/vacancies/{id}")
    public String showVacancy(@PathVariable("id") Long id, Model model) {
        Vacancy task = vacancyService.getVacancyById(id).orElseThrow(() -> new IllegalArgumentException("vacancy not found"));
        model.addAttribute("vacancy", task);
        return "showVacancy";
    }

    @PostMapping("/vacancies/delete/{id}")
    public String deleteVacancy(@PathVariable("id") Long id) {
        vacancyService.deleteVacancy(id);
        return "redirect:/vacancies";
    }

    private String saveImage(MultipartFile image) {
        try {
            byte[] bytes = image.getBytes();
            Path path = Paths.get("demo/src/main/resources/static/uploads/" + image.getOriginalFilename());
            Files.createDirectories(path.getParent()); // Ensure the directory exists
            Files.write(path, bytes);
            return "uploads/" + image.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
