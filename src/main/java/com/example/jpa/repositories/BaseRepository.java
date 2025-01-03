package com.example.jpa.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

  // --- Basic CRUD Operations ---
  @NonNull
  @Override
  <S extends T> S save(@NonNull S entity);

  @NonNull
  @Override
  Optional<T> findById(@NonNull ID id);

  @Override
  void deleteById(@NonNull ID id);

  // --- Custom Pagination ---
  @NonNull
  @Override
  Page<T> findAll(@NonNull Pageable pageable);

  // --- List All ---
  @NonNull
  @Override
  List<T> findAll();

  // --- Delete ---
  @Override
  @Transactional
  void delete(@NonNull T entity);
}