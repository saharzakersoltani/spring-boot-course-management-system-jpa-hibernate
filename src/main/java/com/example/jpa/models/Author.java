package com.example.jpa.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author extends BaseEntity {

  private String firstName;

  private String lastName;

  @Column(unique = true, nullable = false)
  private String email;

  private int age;


  //------------relation between Author and Course------------------
  @ManyToMany(mappedBy = "authors")
  @JsonIgnoreProperties({"authors", "sections"})
  @Builder.Default
  private List<Course> courses = new ArrayList<>();
  //---------------------------------------------------------------

}


