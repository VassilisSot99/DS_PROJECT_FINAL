package gr.hua.huaproject.controller;

import gr.hua.huaproject.entity.Application;
import gr.hua.huaproject.repository.ApplicationRepository;
import gr.hua.huaproject.repository.ApplicationRepositoryCustom;
import gr.hua.huaproject.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationRepositoryCustom applicationRepositoryCustom;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public String home(){
        return "student-home";
    };

    @GetMapping("/courses")
    public String getCourses(Model model){
        model.addAttribute("courses", courseRepository.findAll());
        return "courses";
    }

    @GetMapping("/applicationForm")
    public String getApplicationForm(){
        return "application-form";
    }

    @PostMapping("/newappl")
    public String submitApplication(@ModelAttribute Application application) {

        //finds and sets the student who is currently logged in, and the course by the int the student inputs
        application.setStudent(applicationRepositoryCustom.findCurrentlyLoggedInStudent());
        int thisCourseId = application.getTempCourseId();
        application.setCourse(courseRepository.getById(thisCourseId));
        application.setChecked("null");
        application.setEvaluated("null");
        Application savedApplication = applicationRepository.save(application);
        System.out.println("student id " + savedApplication.getId());

        return "application-success";
   }

   @GetMapping("/myApplications") String myApplications(Model model){
        model.addAttribute("applications", applicationRepositoryCustom.getApplicationsForOneStudentId());
        return "my-applications";
   }
}

