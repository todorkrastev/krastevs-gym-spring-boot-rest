package com.todorkrastev.krastevsgymrest.repository;

import com.todorkrastev.krastevsgymrest.model.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    @Query("select a from Activity a order by a.created asc")
    List<Activity> findAllByCreatedAsc();

    Boolean existsByTitle(String title);

    boolean existsByTitleAndIdNot(String title, Long id);
}
