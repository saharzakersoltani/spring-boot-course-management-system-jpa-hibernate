package com.example.jpa.repositories;

import com.example.jpa.models.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends BaseRepository<Course, Integer> {
  @Query("SELECT c FROM Course c JOIN c.authors a WHERE a.id = :authorId")
  List<Course> findByAuthorId(@Param("authorId") Integer authorId);

  @Query("SELECT c FROM Course c WHERE " +
      "(:title is null OR c.title = :title) AND " +
      "(:description is null OR c.description = :description)")
  List<Course> searchCourses(
      @Param("title") String title,
      @Param("description") String description
  );
}
