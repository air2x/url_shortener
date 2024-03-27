package ru.maxima.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UrlDTO {

    @NotEmpty
    @Size(min = 2, max = 2000, message ="Url min 2 chars, max 2000 chars")
    private String originalUrl;

    private String generatedUrl;

    private int numberOfClicks;
}
