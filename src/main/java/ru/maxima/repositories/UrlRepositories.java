package ru.maxima.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxima.models.Url;

@Repository
public interface UrlRepositories extends JpaRepository<Url, Integer> {
}
