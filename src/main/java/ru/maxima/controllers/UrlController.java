package ru.maxima.controllers;

import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.maxima.dto.UrlRequestDTO;
import ru.maxima.dto.UrlResponseDTO;
import ru.maxima.models.Url;
import ru.maxima.service.UrlService;

@RestController
@RequestMapping
public class UrlController {

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shortening")
    public ResponseEntity<UrlResponseDTO> getUrl(@RequestBody @Valid UrlRequestDTO urlRequestDTO,
                                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Url savedUrl = urlService.saveUrl(urlRequestDTO.getOriginalUrl());
        UrlResponseDTO urlResponseDTO = new UrlResponseDTO();
        urlResponseDTO.setGeneratedUrl(savedUrl.getGeneratedUrl());
        return ResponseEntity.ok(urlResponseDTO);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<UrlResponseDTO> getUrl(@PathVariable String shortUrl) {
        Url url = urlService.findByShortName(shortUrl);
        if (url == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            UrlResponseDTO urlResponseDTO = new UrlResponseDTO();
            urlResponseDTO.setGeneratedUrl(url.getGeneratedUrl());
            return ResponseEntity.ok(urlResponseDTO);
        }
    }
}
