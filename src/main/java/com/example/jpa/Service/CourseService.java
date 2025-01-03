package com.example.jpa.Service;

import com.example.jpa.models.Author;
import com.example.jpa.models.Course;
import com.example.jpa.repositories.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService extends BaseService<Course, Integer> {

  private final CourseRepository courseRepository;
  private final AuthorService authorService;

  public CourseService(CourseRepository courseRepository, AuthorService authorService) {
    super(courseRepository);
    this.courseRepository = courseRepository;
    this.authorService = authorService;
  }

  @Override
  @Transactional
  public Course save(Course course) {
    if (course.getAuthors() != null && !course.getAuthors().isEmpty()) {
      List<Author> authors = course.getAuthors().stream()
          .map(author -> authorService.findById(author.getId()))
          .toList();
      course.setAuthors(new ArrayList<>(authors));
    }
    return courseRepository.save(course);
  }

  @Transactional(readOnly = true)
  public List<Course> findByAuthorId(Integer authorId) {
    return courseRepository.findByAuthorId(authorId);
  }

  public List<Course> searchCourses(String title, String description) {
    return courseRepository.searchCourses(title, description);
  }
}