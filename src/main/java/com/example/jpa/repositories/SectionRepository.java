package com.example.jpa.repositories;

import com.example.jpa.models.Section;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectionRepository extends BaseRepository<Section, Integer> {

  List<Section> findByCourseIdOrderBySectionOrderAsc(Integer courseId);

  @Query("SELECT s FROM Section s WHERE " +
      "(:name is null OR s.name = :name) AND " +
      "(:sectionOrder is null OR s.sectionOrder = :sectionOrder) AND " +
      "(:courseId is null OR s.course.id = :courseId)")
  List<Section> searchSections(
      @Param("name") String name,
      @Param("sectionOrder") Integer sectionOrder,
      @Param("courseId") Integer courseId
  );
}