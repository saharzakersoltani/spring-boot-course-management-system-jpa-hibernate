package com.example.jpa.repositories;

import com.example.jpa.models.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AuthorRepository extends BaseRepository<Author, Integer> {
  @Query("SELECT a FROM Author a WHERE " +
      "(:firstName is null OR a.firstName = :firstName) AND " +
      "(:lastName is null OR a.lastName = :lastName) AND " +
      "(:email is null OR a.email = :email) AND " +
      "(:age is null OR a.age = :age)")
  List<Author> searchAuthors(
      @Param("firstName") String firstName,
      @Param("lastName") String lastName,
      @Param("email") String email,
      @Param("age") Integer age
  );

}




