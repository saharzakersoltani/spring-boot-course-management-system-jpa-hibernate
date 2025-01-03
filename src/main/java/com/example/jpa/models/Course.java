package com.example.jpa.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Course extends BaseEntity {

  @Column(nullable = false)
  private String title;

  private String description;



  //----------------relation between Course and Author-------------------
  @ManyToMany(
      fetch = FetchType.LAZY
  )
  @JoinTable(
      name = "authors_courses",
      joinColumns = @JoinColumn(name = "course_id"),
      inverseJoinColumns = @JoinColumn(name = "author_id")
  )
  @JsonIgnoreProperties({"courses", "email", "age"})
  @Builder.Default
  private List<Author> authors = new ArrayList<>();
//---------------------------------------------------------------



//--------------------relation between Course and Section--------------------------
  @OneToMany(
      mappedBy = "course",
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      orphanRemoval = true
  )
  @Builder.Default
  @JsonIgnoreProperties("course")
  private List<Section> sections = new ArrayList<>();
//------------------------------------------------------------------------------



  // Helper methods for managing bidirectional relationships
  public void addSection(Section section) {
    sections.add(section);
    section.setCourse(this);
  }

  public void removeSection(Section section) {
    sections.remove(section);
    section.setCourse(null);
  }

  public void addAuthor(Author author) {
    authors.add(author);
    author.getCourses().add(this);
  }

  public void removeAuthor(Author author) {
    authors.remove(author);
    author.getCourses().remove(this);
  }
}
