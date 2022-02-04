package fi.shednet.networkmonitoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class NetworkMonitoringService {

    @Autowired
    private MonitoringEntityRepository monitoringEntityRepository;

    public List<MonitoringEntityModel> getMonitoringEvents() {
        return this.monitoringEntityRepository.findAll();
    }

    public List<List<Object>> getDownloadUploadPing() {
        List<MonitoringEntityModel> all = this.monitoringEntityRepository.findAll();
        if (!all.isEmpty()) {
            List<List<Object>> entities = new ArrayList<>();
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i) != null) {
                    if (all.get(i).getTime() != null) {
                        entities.add(List.of(
                                all.get(i).getTime().toEpochSecond(ZoneOffset.UTC),
                                all.get(i).getDownload(),
                                all.get(i).getUpload(),
                                all.get(i).getPing())
                        );
                    }

                }

            }
            return entities;
        }
        return null;
    }
}
