package com.example.jpa.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lecture extends BaseEntity {

    private String name;
//--------------------relation between Lecture and Section-------------------
    @ManyToOne
    @JoinColumn(
            name = "section_id"
    )
    private Section section;
//------------------------------------------------------------------------

//----------------relation between Lecture and Resource----------------------
    @OneToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;
//------------------------------------------------------------------------
}
