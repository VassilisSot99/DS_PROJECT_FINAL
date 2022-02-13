package gr.hua.huaproject.controller;

import gr.hua.huaproject.entity.AuthPK;
import gr.hua.huaproject.entity.Authorities;
import gr.hua.huaproject.entity.User;
import gr.hua.huaproject.repository.AuthorityRepository;
import gr.hua.huaproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authRepository;

    @GetMapping()
    public String home(){
        return "admin-home";
    }

    @GetMapping("/addUser")
    public String getUserForm(User user){
        return "user-create-form";
    }

    //Create, Update User
    //if admin use an existing username, it replaces it with the inserted data
    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        Authorities auth = new Authorities("ROLE_STUDENT", user);
        userRepository.save(user);
        authRepository.save(auth);
        return "added-user";
    }

    @GetMapping("/deleteUser")
    public String deleteForm(){
        return "user-delete-form";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam(name="username") String username){
        System.out.println(username);
        userRepository.deleteById(username);
        return "user-deleted";
    }


    @GetMapping("/addRole")
    public String getRoleForm(){
        return "role-form";
    }

    @PostMapping("/addRole")
    public String addRole(@RequestParam(name="username") String username,
                          @RequestParam(name="authority") String authority){
        Authorities auth = new Authorities();
        auth.setUser(userRepository.getById(username));
        auth.setAuthority(authority);
        authRepository.save(auth);
        return "role-added";
    }

    @GetMapping("/deleteRole")
    public String deleteRoleForm(){
        return "role-delete-form";
    }

    @PostMapping("/deleteRole")
    public String deleteRole(@RequestParam(name="username") String username,
                             @RequestParam(name = "role") String role){
        System.out.println(username);
        AuthPK id = new AuthPK(username, role);
        authRepository.deleteById(id); //δεν τρεχει!!!

        return "role-deleted";
    }
}
