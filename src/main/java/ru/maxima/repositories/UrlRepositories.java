package ru.maxima.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxima.models.Url;

import java.util.Optional;

@Repository
public interface UrlRepositories extends JpaRepository<Url, Integer> {
    Optional<Url> findByGeneratedUrl(String generatedUrl);

    Optional<Url> findByOriginalUrl(String originalUrl);


}
