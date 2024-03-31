package ru.maxima.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.maxima.dto.UrlRequestDTO;
import ru.maxima.dto.UrlResponseDTO;
import ru.maxima.models.Url;
import ru.maxima.service.UrlService;

import java.util.List;

@RestController
@RequestMapping
public class UrlController {

    private final UrlService urlService;
    private final ModelMapper modelMapper;

    @Autowired
    public UrlController(UrlService urlService, ModelMapper modelMapper) {
        this.urlService = urlService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/shortening")
    public ResponseEntity<UrlResponseDTO> getUrl(@RequestBody @Valid UrlRequestDTO urlRequestDTO,
                                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Url url = urlService.saveUrl(urlRequestDTO.getOriginalUrl());
        UrlResponseDTO urlResponseDTO = modelMapper.map(url, UrlResponseDTO.class);
        return ResponseEntity.ok(urlResponseDTO);
    }

    @GetMapping("/{shortUrl}")
    public RedirectView redirect(@PathVariable String shortUrl) {
        UrlResponseDTO urlResponseDTO = modelMapper.map(urlService.findByShortName(shortUrl), UrlResponseDTO.class);
        urlService.saveClickOnUrl(shortUrl);
        return new RedirectView(urlResponseDTO.getOriginalUrl());
    }

    @GetMapping("/allStatistics")
    public List<UrlResponseDTO> getAllStatisticsClicksOnUrl() {
        return urlService.getStatisticClickOnUrl();
    }
}
