package com.example.jpa.Service;

import com.example.jpa.models.Resource;
import com.example.jpa.repositories.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResourceService extends BaseService<Resource, Integer> {

  private final ResourceRepository resourceRepository;

  public ResourceService(ResourceRepository resourceRepository) {
    super(resourceRepository);
    this.resourceRepository = resourceRepository;
  }

  public List<Resource> searchResources(String name, Integer size, String url) {
    return resourceRepository.searchResources(name, size, url);
  }
}