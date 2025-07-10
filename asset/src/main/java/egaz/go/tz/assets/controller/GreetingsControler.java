package egaz.go.tz.assets.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/greetings")
public class GreetingsControler {
    @GetMapping
    public String greetings(){
        return "HELLO WORLD";
    }
}
