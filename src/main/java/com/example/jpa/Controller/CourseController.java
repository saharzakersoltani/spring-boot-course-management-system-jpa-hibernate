package com.example.jpa.Controller;

import com.example.jpa.Service.AuthorService;
import com.example.jpa.models.Author;
import com.example.jpa.models.Course;
import com.example.jpa.Service.CourseService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

  private final CourseService courseService;
  private final AuthorService authorService;

  @PostMapping
  public ResponseEntity<Course> create(@RequestBody CourseRequest courseRequest) {
    Author author = authorService.findById(courseRequest.getAuthorId());
    Course course = Course.builder()
        .title(courseRequest.getTitle())
        .description(courseRequest.getDescription())
        .authors(List.of(author))
        .build();
    return ResponseEntity.ok(courseService.save(course));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Course> findById(@PathVariable Integer id) {
    return ResponseEntity.ok(courseService.findById(id));
  }

  @GetMapping
  public ResponseEntity<List<Course>> findAll() {
    return ResponseEntity.ok(courseService.findAll());
  }

  @GetMapping("/paged")
  public ResponseEntity<Page<Course>> findAll(Pageable pageable) {
    return ResponseEntity.ok(courseService.findAll(pageable));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    courseService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/search/author/{authorId}")
  public ResponseEntity<List<Course>> findByAuthorId(@PathVariable Integer authorId) {
    return ResponseEntity.ok(courseService.findByAuthorId(authorId));
  }

  @GetMapping("/search")
  public ResponseEntity<List<Course>> searchCourses(
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String description
  ) {
    return ResponseEntity.ok(courseService.searchCourses(title, description));
  }
}

@Data
class CourseRequest {
  private String title;
  private String description;
  private Integer authorId;
}
