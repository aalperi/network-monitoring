package fi.shednet.networkmonitoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetworkMonitoringService {

    @Autowired
    private MonitoringEntityRepository monitoringEntityRepository;

    public List<MonitoringEntityModel> getMonitoringEvents(){
        return this.monitoringEntityRepository.findAll();
    }

}
