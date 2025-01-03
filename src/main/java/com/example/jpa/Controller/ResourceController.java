package com.example.jpa.Controller;

import com.example.jpa.models.Resource;
import com.example.jpa.Service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourceController {

  private final ResourceService resourceService;

  @PostMapping
  public ResponseEntity<Resource> create(@RequestBody Resource resource) {
    return ResponseEntity.ok(resourceService.save(resource));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Resource> findById(@PathVariable Integer id) {
    return ResponseEntity.ok(resourceService.findById(id));
  }

  @GetMapping
  public ResponseEntity<List<Resource>> findAll() {
    return ResponseEntity.ok(resourceService.findAll());
  }

  @GetMapping("/paged")
  public ResponseEntity<Page<Resource>> findAll(Pageable pageable) {
    return ResponseEntity.ok(resourceService.findAll(pageable));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    resourceService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/search")
  public ResponseEntity<List<Resource>> searchResources(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) Integer size,
      @RequestParam(required = false) String url
  ) {
    return ResponseEntity.ok(resourceService.searchResources(name, size, url));
  }
}