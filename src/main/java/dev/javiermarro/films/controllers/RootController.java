package dev.javiermarro.films.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class RootController {

    @GetMapping("/")
    public String home() {
        return "Marro films API is running, check the endpoint /api/v1 for resources.";
    }

    @GetMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getApiInfo() throws IOException {
        ClassPathResource resource = new ClassPathResource("endpoints.json");
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }
}
