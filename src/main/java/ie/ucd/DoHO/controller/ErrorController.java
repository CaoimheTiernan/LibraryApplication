package ie.ucd.DoHO.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ErrorController {
    @GetMapping("/error")
    public String error(@RequestParam("title") String title,
                        @RequestParam("msg") String message,
                        Model model, HttpServletResponse response) {
        model.addAttribute("message", message);
        return "errors/error?title=" + title + "&msg=" + message;
    }

    @GetMapping("/error/user_not_found")
    public void userNotFound(@RequestParam("name") String name, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(
                "/error?title=UserNotFound&msg=NoSuchUser:" + name
        );
    }

    @GetMapping("/error/invalid_admin")
    public void invalidAdminAction(@RequestParam("name") String name, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(
                "/error?title=InvalidUser&msg=" + name + "isAdmin."
        );
    }

    @GetMapping("/error/loan_not_found")
    public void invalidUserForReceive(HttpServletResponse response)
            throws IOException {
        response.sendRedirect(
                "/error?title=LoanNotFound&msg=LoanNotFound'"
        );
    }

    @GetMapping("/error/already_reserved")
    public void invalidLoanRenewal(HttpServletResponse response) throws IOException{
            response.sendRedirect("/error?title=InvalidRenewal&msg=CannotRenewLoan");
    }
}
