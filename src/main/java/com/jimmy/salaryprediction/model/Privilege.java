package com.jimmy.salaryprediction.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Table(name = "Privileges")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Privilege {
    @Id
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "privileges")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Role> roles;
}
