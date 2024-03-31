package ru.maxima.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.maxima.models.Url;

@Getter
@Setter
public class UrlResponseDTO implements Comparable<UrlResponseDTO> {

    @NotEmpty
    @Size(min = 2, max = 2000, message = "Url min 2 chars, max 2000 chars")
    private String originalUrl;

    private String generatedUrl;

    private int numberOfClicks;

    @Override
    public int compareTo(UrlResponseDTO o) {
        return o.getNumberOfClicks() - this.getNumberOfClicks();
    }
}
