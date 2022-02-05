package fi.shednet.networkmonitoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class NetworkMonitoringService {

    @Autowired
    private MonitoringEntityRepository monitoringEntityRepository;

    public List<MonitoringEntityModel> getMonitoringEvents(String useCase) {
        if (useCase.equals("all")) {
            return this.monitoringEntityRepository.findAll();
        } else
            return this.monitoringEntityRepository.findFirst576ByOrderByTimestampDesc();
    }

    public List<List<Object>> getDownloadUpload(String useCase) {
        List<MonitoringEntityModel> all = getMonitoringEvents(useCase);
        if (!all.isEmpty()) {
            List<List<Object>> entities = new ArrayList<>();
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i) != null) {
                    if (all.get(i).getTimestamp() != null) {
                        entities.add(List.of(
                                all.get(i).getTimestamp().toInstant().toEpochMilli(),
                                all.get(i).getDownload().divide(BigDecimal.valueOf(Long.valueOf(1000000))),
                                all.get(i).getUpload().divide(BigDecimal.valueOf(Long.valueOf(1000000)))
                        ));
                    }
                }
            }
            return entities;
        }
        return null;
    }

    public List<List<Object>> getPing(String useCase) {
        List<MonitoringEntityModel> all = getMonitoringEvents(useCase);
        if (!all.isEmpty()) {
            List<List<Object>> entities = new ArrayList<>();
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i) != null) {
                    if (all.get(i).getTimestamp() != null) {
                        entities.add(List.of(
                                all.get(i).getTimestamp().toInstant().toEpochMilli(),
                                all.get(i).getPing()
                        ));
                    }
                }
            }
            return entities;
        }
        return null;
    }
}
