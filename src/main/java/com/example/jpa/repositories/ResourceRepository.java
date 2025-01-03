package com.example.jpa.repositories;

import com.example.jpa.models.Resource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourceRepository extends BaseRepository<Resource, Integer> {

  @Query("SELECT r FROM Resource r WHERE " +
      "(:name is null OR r.name = :name) AND " +
      "(:size is null OR r.size = :size) AND " +
      "(:url is null OR r.url = :url)")
  List<Resource> searchResources(
      @Param("name") String name,
      @Param("size") Integer size,
      @Param("url") String url
  );
}