package fi.shednet.networkmonitoring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitoringEntityRepository extends JpaRepository<MonitoringEntityModel, Integer> {
    List<MonitoringEntityModel> findFirst576ByOrderByTimestampDesc();
}
