package org.aleks4ay.springbootsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class Controller {

    @GetMapping
    public String index() {
        return "Home page.";
    }

    @GetMapping("/auth")
    public String getAuyhenticated(Principal principal) {
        return "Authenticated Page. Name = '" + principal.getName() + "'";
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "Admin Page.";
    }
}
