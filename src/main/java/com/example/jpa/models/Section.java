package com.example.jpa.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Section extends BaseEntity {

    private String name;

    private int sectionOrder;



//------------------relation between Section and Course--------------
    @ManyToOne
    @JoinColumn(
            name = "course_id"
    )
    @JsonIgnoreProperties({"sections", "authors"})
    private Course course;
//-------------------------------------------------------------------



//-------------------relation between Section and Lecture-----------------------
    @OneToMany(
            mappedBy = "section"
    )
    @JsonIgnoreProperties("section")
    private List<Lecture> lectures;
//-----------------------------------------------------------------------------------

}
