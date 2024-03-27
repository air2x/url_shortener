package ru.maxima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maxima.repositories.UrlRepositories;

@Service
public class UrlService {

    private final UrlRepositories urlRepositories;

    @Autowired
    public UrlService(UrlRepositories urlRepositories) {
        this.urlRepositories = urlRepositories;
    }
}
