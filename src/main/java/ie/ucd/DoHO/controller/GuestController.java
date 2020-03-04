package ie.ucd.DoHO.controller;

import ie.ucd.DoHO.model.Artifact;
import ie.ucd.DoHO.model.ArtifactRepository;
import ie.ucd.DoHO.model.User;
import ie.ucd.DoHO.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Optional;

@Controller
public class GuestController {
    @Autowired
    private UserSession userSession;
    @Autowired
    private ArtifactRepository artifactRepository;
    @Autowired
    private UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(GuestController.class);

    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("user", userSession.getUser());
    }

    @GetMapping("/")
    public String index(Model model) {
        if(userSession.getUser() != null)
            model.addAttribute("id", userSession.getUser().getId());
        return "index";
    }

    @GetMapping("/loan_history")
    public String loan_history() {
        return "loan_history";
    }

    @GetMapping("/artifact")
    public String viewArtifact(@RequestParam(name = "aID") Integer id, Model model)
            throws IOException {
        Optional<Artifact> artifact = artifactRepository.findById(id);
        if (artifact.isPresent()) {
            model.addAttribute("artifact", artifact.get());
            model.addAttribute("additionalDetails", artifact.get().getAdditionalDetails());

            model.addAttribute("isAdmin", userSession.isAdmin());
            logger.info("In viewArtifact: user=" + userSession.getUser() + " isAdmin=" + userSession.isAdmin());
        } else {
            // todo return a proper error page here
            return "404";
        }
        return "artifact";
    }

    @GetMapping("/search_user")
    public String displayUsers(Model model){
        model.addAttribute("users",userRepository.findAll());
        return "search_users.html";
    }

    @GetMapping("/user_profile")
    public String user(@RequestParam("id") Integer id, Model model) {
        Optional<User> user = userRepository.findById(id);
        model.addAttribute("fullName", user.get().getFullName());
        model.addAttribute("username", user.get().getUsername());
        model.addAttribute("email", user.get().getEmail());
        model.addAttribute("phoneNumber", user.get().getPhoneNumber());
        model.addAttribute("id", user.get().getId());
        model.addAttribute("created", user.get().getCreated());
        return "user_profile";
    }

    @GetMapping("/search_artifact")
    public String displayArtifacts() {
        return "search_artifact.html";
    }
}
