package com.example.jpa.Controller;

import com.example.jpa.Service.ResourceService;
import com.example.jpa.Service.SectionService;
import com.example.jpa.models.Lecture;
import com.example.jpa.Service.LectureService;
import com.example.jpa.models.Resource;
import com.example.jpa.models.Section;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lectures")
@RequiredArgsConstructor
public class LectureController {

  private final LectureService lectureService;
  private final SectionService sectionService;
  private final ResourceService resourceService;

  @PostMapping
  public ResponseEntity<Lecture> create(@RequestBody LectureRequest lectureRequest) {
    Section section = sectionService.findById(lectureRequest.getSectionId());
    Resource resource = null;
    if (lectureRequest.getResourceId() != null) {
      resource = resourceService.findById(lectureRequest.getResourceId());
    }

    Lecture lecture = Lecture.builder()
        .name(lectureRequest.getName())
        .section(section)
        .resource(resource)
        .build();

    return ResponseEntity.ok(lectureService.save(lecture));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Lecture> findById(@PathVariable Integer id) {
    return ResponseEntity.ok(lectureService.findById(id));
  }

  @GetMapping
  public ResponseEntity<List<Lecture>> findAll() {
    return ResponseEntity.ok(lectureService.findAll());
  }

  @GetMapping("/paged")
  public ResponseEntity<Page<Lecture>> findAll(Pageable pageable) {
    return ResponseEntity.ok(lectureService.findAll(pageable));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    lectureService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/section/{sectionId}")
  public ResponseEntity<List<Lecture>> findBySectionId(@PathVariable Integer sectionId) {
    return ResponseEntity.ok(lectureService.findBySectionId(sectionId));
  }

  @GetMapping("/search")
  public ResponseEntity<List<Lecture>> searchLectures(
      @RequestParam(required = false) String name
  ) {
    return ResponseEntity.ok(lectureService.searchLectures(name));
  }
}

@Data
class LectureRequest {
  private String name;
  private Integer sectionId;
  private Integer resourceId;
}