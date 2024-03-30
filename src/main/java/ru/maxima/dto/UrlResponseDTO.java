package ru.maxima.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UrlResponseDTO {

    @NotEmpty
    private String generatedUrl;

}
