package com.todorkrastev.krastevsgymrest.repository;

import com.todorkrastev.krastevsgymrest.model.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    @Query("select a from Activity a order by a.id asc")
    Set<Activity> findAllByOrderByIdAsc();
}
