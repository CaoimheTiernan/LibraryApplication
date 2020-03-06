package ie.ucd.DoHO.controller;

import ie.ucd.DoHO.model.ArtifactRepository;
import ie.ucd.DoHO.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LibrarianController {
    @Autowired
    private UserSession userSession;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArtifactRepository artifactRepository;
    private Logger logger = LoggerFactory.getLogger(LibrarianController.class);

    @DeleteMapping("/artifact")
    public String deleteArtifact(@RequestParam(name = "aID") Integer id,
                                 HttpServletResponse response)
            throws IOException {
        if (userSession.isAdmin()) {
            artifactRepository.deleteById(id);
            response.sendRedirect("librarian_portal");
        }
        return "index";
    }
}