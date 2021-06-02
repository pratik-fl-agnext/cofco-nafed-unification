package com.agnext.unification.repository;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BaseRepository<T> extends JpaRepository<T, Long> {

  
   
}
