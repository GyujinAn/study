package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author agj017@gmail.com
 * @since 2021/10/06
 */

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

}
