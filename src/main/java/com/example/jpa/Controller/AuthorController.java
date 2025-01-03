package com.example.jpa.Controller;

import com.example.jpa.models.Author;
import com.example.jpa.Service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

  private final AuthorService authorService;

  @PostMapping
  public ResponseEntity<Author> create(@RequestBody Author author) {
    return ResponseEntity.ok(authorService.save(author));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Author> findById(@PathVariable Integer id) {
    return ResponseEntity.ok(authorService.findById(id));
  }

  @GetMapping
  public ResponseEntity<List<Author>> findAll() {
    return ResponseEntity.ok(authorService.findAll());
  }

  @GetMapping("/paged")
  public ResponseEntity<Page<Author>> findAll(Pageable pageable) {
    return ResponseEntity.ok(authorService.findAll(pageable));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    authorService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/search")
  public ResponseEntity<List<Author>> searchAuthors(
      @RequestParam(required = false) String firstName,
      @RequestParam(required = false) String lastName,
      @RequestParam(required = false) String email,
      @RequestParam(required = false) Integer age
  ) {
    return ResponseEntity.ok(authorService.searchAuthors(firstName, lastName, email, age));
  }
}