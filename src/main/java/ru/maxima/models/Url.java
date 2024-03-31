package ru.maxima.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "url")
public class Url {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "original_url")
    @NotEmpty
    @Size(min = 2, max = 2000, message = "Url min 2 chars, max 2000 chars")
    private String originalUrl;

    @Column(name = "generated_url")
    private String generatedUrl;

    @Column(name = "number_of_clicks")
    private int numberOfClicks;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;
}
