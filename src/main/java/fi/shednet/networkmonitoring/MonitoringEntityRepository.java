package fi.shednet.networkmonitoring;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitoringEntityRepository extends JpaRepository<MonitoringEntityModel, Integer> {
}
