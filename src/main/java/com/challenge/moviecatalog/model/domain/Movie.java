package com.challenge.moviecatalog.model.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate releaseDate;

    private String sypnosis;

    @Enumerated(EnumType.STRING)
    private Category category;

    private int createdBy;

    private LocalDateTime createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "movie")
    private List<Rate> ratings;


}
