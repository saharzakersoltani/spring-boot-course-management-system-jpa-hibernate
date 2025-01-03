package com.example.jpa.Controller;

import com.example.jpa.Service.CourseService;
import com.example.jpa.models.Course;
import com.example.jpa.models.Section;
import com.example.jpa.Service.SectionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
@RequiredArgsConstructor
public class SectionController {

  private final SectionService sectionService;
  private final CourseService courseService;

  @PostMapping
  public ResponseEntity<Section> create(@RequestBody SectionRequest sectionRequest) {
    Course course = courseService.findById(sectionRequest.getCourseId());
    Section section = Section.builder()
        .name(sectionRequest.getName())
        .sectionOrder(sectionRequest.getSectionOrder())
        .course(course)
        .build();
    return ResponseEntity.ok(sectionService.save(section));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Section> findById(@PathVariable Integer id) {
    return ResponseEntity.ok(sectionService.findById(id));
  }

  @GetMapping
  public ResponseEntity<List<Section>> findAll() {
    return ResponseEntity.ok(sectionService.findAll());
  }

  @GetMapping("/paged")
  public ResponseEntity<Page<Section>> findAll(Pageable pageable) {
    return ResponseEntity.ok(sectionService.findAll(pageable));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    sectionService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/course/{courseId}")
  public ResponseEntity<List<Section>> findByCourseId(@PathVariable Integer courseId) {
    return ResponseEntity.ok(sectionService.findByCourseId(courseId));
  }

  @GetMapping("/search")
  public ResponseEntity<List<Section>> searchSections(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) Integer sectionOrder,
      @RequestParam(required = false) Integer courseId
  ) {
    return ResponseEntity.ok(sectionService.searchSections(name, sectionOrder, courseId));
  }
}

@Data
class SectionRequest {
  private String name;
  private int sectionOrder;
  private Integer courseId;
}