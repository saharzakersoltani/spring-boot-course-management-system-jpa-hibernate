package com.example.jpa.Service;

import com.example.jpa.models.Author;
import com.example.jpa.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthorService extends BaseService<Author, Integer> {
  private final AuthorRepository authorRepository;

  public AuthorService(AuthorRepository authorRepository) {
    super(authorRepository);
    this.authorRepository = authorRepository;
  }

  public List<Author> searchAuthors(String firstName, String lastName, String email, Integer age) {
    return authorRepository.searchAuthors(firstName, lastName, email, age);
  }
}