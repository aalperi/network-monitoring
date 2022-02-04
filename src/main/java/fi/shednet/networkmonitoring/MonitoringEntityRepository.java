package fi.shednet.networkmonitoring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoringEntityRepository extends JpaRepository<MonitoringEntityModel, Integer> {
}
