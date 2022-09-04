package com.beerair.core.common.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class HealthController {
    @GetMapping({"/", "/health"})
    public String health() {
        return "Health Good";
    }
}
