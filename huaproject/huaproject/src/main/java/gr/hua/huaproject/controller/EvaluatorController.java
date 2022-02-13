package gr.hua.huaproject.controller;

import gr.hua.huaproject.entity.Application;
import gr.hua.huaproject.repository.ApplicationRepository;
import gr.hua.huaproject.repository.ApplicationRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/evaluator")
public class EvaluatorController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationRepositoryCustom applicationRepositoryCustom;

    @GetMapping
    public String home(){
        return "evaluator-home";
    };

    @GetMapping("/applications")
    public String getAllApplications(Model model) {
        model.addAttribute("applications", applicationRepository.findAll());
        return "all-applications";
    }

    @GetMapping("/pending")
    public String getApplicationsToBeEvaluated(Model model) {
        model.addAttribute("applications", applicationRepositoryCustom.getApplicationsToBeEvaluated());
        return "evaluator-pending";
    }

    @GetMapping("/{id}")
    public String getEvaluationForm(@PathVariable int id, Model model) {

        //checks if the application exists
        Optional<Application> application = applicationRepository.findById(id);
        //if it doesnt
        if (!application.isPresent())
            throw new ApplicationNotFoundException("id-" + id);
        //if it does, add the applications id into the model
        model.addAttribute("id", id);

        return "evaluation-form";
    }

    //Δεχεται Post request αλλα κανει δουλεία PUT (γιατι η html form υποστηριζει μονο methods GET, POST)
    @PostMapping(value="/{id}")
    public String evaluateApplication(@PathVariable int id, @RequestParam(name="evaluated") String evaluated) {
        applicationRepositoryCustom.evaluateApplication(id, evaluated);
        return "successful-evaluation";
    }



}
