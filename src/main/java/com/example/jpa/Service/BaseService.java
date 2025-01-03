package com.example.jpa.Service;

import com.example.jpa.repositories.BaseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class BaseService<T, ID> {

  protected final BaseRepository<T, ID> repository;

  protected BaseService(BaseRepository<T, ID> repository) {
    this.repository = repository;
  }

  @Transactional
  public T save(T entity) {
    return repository.save(entity);
  }

  @Transactional(readOnly = true)
  public T findById(ID id) {
    return repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
  }

  @Transactional(readOnly = true)
  public List<T> findAll() {
    return repository.findAll();
  }

  @Transactional(readOnly = true)
  public Page<T> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Transactional
  public void deleteById(ID id) {
    repository.deleteById(id);
  }
}