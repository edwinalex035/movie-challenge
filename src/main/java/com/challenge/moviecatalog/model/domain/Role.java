package com.challenge.moviecatalog.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table(name = "mrole")
public class Role {
    @Id
    private Long id;

    private String name;

    @OneToMany
    @JsonIgnore
    private List<User> users;
}
