package ru.maxima.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maxima.dto.UrlDTO;
import ru.maxima.models.Url;
import ru.maxima.repositories.UrlRepositories;

import java.util.Random;

@Service
public class UrlService {

    private final UrlRepositories urlRepositories;
    private final ModelMapper modelMapper;

    @Autowired
    public UrlService(UrlRepositories urlRepositories, ModelMapper modelMapper) {
        this.urlRepositories = urlRepositories;
        this.modelMapper = modelMapper;
    }

    public void saveUrl(UrlDTO urlDTO) {
        urlDTO.setGeneratedUrl(shortening(urlDTO.getOriginalUrl()));
        urlRepositories.save(convertToUrl(urlDTO));
    }

//    Создание короткой ссылки по полному URL, короткая ссылка содержит символы из диапазона: [0-9, a-z].
    private String shortening(String originalUrl) {
        StringBuilder url = null;
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            url.append(Character.toString(random.nextInt(10)));
        }
        return url.toString();
    }

    private Url convertToUrl(UrlDTO urlDTO) {
        return modelMapper.map(urlDTO, Url.class);
    }

    private UrlDTO convertToUrlDTO(Url url) {
        return modelMapper.map(url, UrlDTO.class);
    }
}
