package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@ApiModel(description = "Topic Details")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Topic {

    @Id
    private String id;

    @NotBlank(message = "Name should not be blank")
    private String name;

    @JsonIgnore
    private String description;

    @ToString.Exclude
    @OneToMany(mappedBy = "topic") // Topic can have many courses
    private List<Course> courses;

    public Topic(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
