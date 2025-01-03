package com.example.jpa.Service;

import com.example.jpa.models.Lecture;
import com.example.jpa.models.Resource;
import com.example.jpa.models.Section;
import com.example.jpa.repositories.LectureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class LectureService extends BaseService<Lecture, Integer> {

  private final LectureRepository lectureRepository;
  private final SectionService sectionService;
  private final ResourceService resourceService;

  public LectureService(LectureRepository lectureRepository,
                        SectionService sectionService,
                        ResourceService resourceService) {
    super(lectureRepository);
    this.lectureRepository = lectureRepository;
    this.sectionService = sectionService;
    this.resourceService = resourceService;
  }

  @Override
  @Transactional
  public Lecture save(Lecture lecture) {
    if (lecture.getSection() != null && lecture.getSection().getId() != null) {
      Section section = sectionService.findById(lecture.getSection().getId());
      lecture.setSection(section);
    }
    if (lecture.getResource() != null && lecture.getResource().getId() != null) {
      Resource resource = resourceService.findById(lecture.getResource().getId());
      lecture.setResource(resource);
    }
    return lectureRepository.save(lecture);
  }

  @Transactional(readOnly = true)
  public List<Lecture> findBySectionId(Integer sectionId) {
    return lectureRepository.findBySectionId(sectionId);
  }

  public List<Lecture> searchLectures(String name) {
    return lectureRepository.searchLectures(name);
  }
}