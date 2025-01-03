package com.example.jpa.repositories;

import com.example.jpa.models.Lecture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectureRepository extends BaseRepository<Lecture, Integer> {

  List<Lecture> findBySectionId(Integer sectionId);

  @Query("SELECT l FROM Lecture l WHERE " +
      "(:name is null OR l.name = :name)")
  List<Lecture> searchLectures(
      @Param("name") String name
  );
}