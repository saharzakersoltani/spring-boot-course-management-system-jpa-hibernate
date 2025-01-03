package com.example.jpa.Service;

import com.example.jpa.models.Section;
import com.example.jpa.repositories.SectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SectionService extends BaseService<Section, Integer> {

  private final SectionRepository sectionRepository;

  public SectionService(SectionRepository sectionRepository) {
    super(sectionRepository);
    this.sectionRepository = sectionRepository;
  }

  @Transactional(readOnly = true)
  public List<Section> findByCourseId(Integer courseId) {
    return sectionRepository.findByCourseIdOrderBySectionOrderAsc(courseId);
  }

  public List<Section> searchSections(String name, Integer sectionOrder, Integer courseId) {
    return sectionRepository.searchSections(name, sectionOrder, courseId);
  }
}