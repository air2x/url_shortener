package ru.maxima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maxima.models.Url;
import ru.maxima.repositories.UrlRepositories;

import java.util.Random;

@Service
public class UrlService {

    private final UrlRepositories urlRepositories;
    private final static String AVAILABLE_SYMBOLS = "0123456789abcdefghijklmnopqrtuvwxyz";

    @Autowired
    public UrlService(UrlRepositories urlRepositories) {
        this.urlRepositories = urlRepositories;
    }

    public Url saveUrl(String urlString) {
        Url url = new Url();
        if (urlRepositories.findByOriginalUrl(urlString).isPresent()) {
            throw new RuntimeException("This URL exists");
        } else {
            url.setOriginalUrl(urlString);
            String shorteningString = shortening();
            url.setGeneratedUrl(shorteningString);
            urlRepositories.save(url);
        }
        return url;
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

    public Url findByShortName(String shortUrl) {
        return urlRepositories.findByGeneratedUrl(shortUrl).orElse(null);
    }
}
