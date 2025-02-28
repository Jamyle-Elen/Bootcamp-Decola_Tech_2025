package dio.dio_spring_security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping
    public String welcome() {
        return "Welcome to My Spring Boot Web API";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('managers', 'users')")
    public String users() {
        return "Authorized user";
    }

    @GetMapping("/managers")
    @PreAuthorize("hasAnyRole('managers')")
    public String managers() {
        return "Authorized manager";
    }


}
