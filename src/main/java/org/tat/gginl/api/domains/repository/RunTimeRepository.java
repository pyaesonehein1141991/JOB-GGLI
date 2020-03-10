package org.tat.gginl.api.domains.repository;

import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tat.gginl.api.domains.TimeToSave;

@Repository
public interface RunTimeRepository extends JpaRepository<TimeToSave, String> {


  @Query(value = "SELECT RUNTIME FROM RUNTIMETABLE", nativeQuery = true)
  Date findRuntime();
}
