package ru.maxima.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlRequestDTO {

    @NotEmpty
    @Size(min = 2, max = 2000, message ="Url min 2 chars, max 2000 chars")
    private String originalUrl;
}
