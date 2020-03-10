package org.tat.gginl.api.domains.services;

import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tat.gginl.api.domains.TimeToSave;
import org.tat.gginl.api.domains.repository.RunTimeRepository;

@Service
public class RunTimeService {

  @Autowired
  RunTimeRepository runTimeRepository;

  @Transactional
  public Optional<TimeToSave> findbyId(String id) {
    return runTimeRepository.findById(id);
  }

  @Transactional
  public void updateTime(Optional<TimeToSave> runtimes) {
    if (runtimes.isPresent()) {
      runTimeRepository.saveAndFlush(runtimes.get());
    }
  }

  public Date findRuntime() {
    return runTimeRepository.findRuntime();
  }

}
