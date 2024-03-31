package ru.maxima.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.dto.UrlResponseDTO;
import ru.maxima.models.Url;
import ru.maxima.repositories.UrlRepositories;

import java.util.*;

@Service
public class UrlService {

    private final UrlRepositories urlRepositories;
    private final ModelMapper modelMapper;
    private final static String AVAILABLE_SYMBOLS = "0123456789abcdefghijklmnopqrtuvwxyz";

    @Autowired
    public UrlService(UrlRepositories urlRepositories, ModelMapper modelMapper) {
        this.urlRepositories = urlRepositories;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Url saveUrl(String urlString) {
        Url url = new Url();
        if (urlRepositories.findByOriginalUrl(urlString).isPresent()) {
            throw new RuntimeException("This URL exists");
        } else {
            url.setOriginalUrl(urlString);
            url.setGeneratedUrl(shortening());
            urlRepositories.save(url);
        }
        return url;
    }

    public Url findByShortName(String shortUrl) {
        return urlRepositories.findByGeneratedUrl(shortUrl).orElse(null);
    }

    @Transactional
    public void saveClickOnUrl(String shortUrl) {
        Url url = urlRepositories.findByGeneratedUrl(shortUrl).orElse(null);
        Objects.requireNonNull(url).setNumberOfClicks(url.getNumberOfClicks() + 1);
    }

    public List<UrlResponseDTO> getStatisticClickOnUrl() {
        List<UrlResponseDTO> allUrlResponseDto = new ArrayList<>();
        List<Url> allUrl = urlRepositories.findAll();
        for (Url url : allUrl) {
            UrlResponseDTO urlResponseDTO = modelMapper.map(url, UrlResponseDTO.class);
            allUrlResponseDto.add(urlResponseDTO);
        }
        Collections.sort(allUrlResponseDto);
        return allUrlResponseDto;
    }

    private String shortening() {
        StringBuilder url = new StringBuilder();
        Random random = new Random();
        do {
            for (int i = 0; i < 7; i++) {
                url.append(AVAILABLE_SYMBOLS.charAt(random.nextInt(AVAILABLE_SYMBOLS.length())));
            }
        } while (findByShortName(url.toString()) != null);
        return url.toString();
    }
}
