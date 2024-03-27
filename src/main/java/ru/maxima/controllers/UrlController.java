package ru.maxima.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.maxima.dto.UrlDTO;
import ru.maxima.service.UrlService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UrlController {

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shortening")
    public ResponseEntity<UrlDTO> getUrl(@RequestBody @Valid UrlDTO urlDTO,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return null;
        }
        return null;
    }
}
