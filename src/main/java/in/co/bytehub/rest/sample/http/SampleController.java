package in.co.bytehub.rest.sample.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/")
    public String welcome() {
        return "Welcome to our Service !!!";
    }
}
