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
        if(!all.isEmpty()){

            List<List<Object>> entities = new ArrayList<>();
            all.stream().forEach((result) ->
                    entities.add(List.of(
                            result.getTime().toEpochSecond(ZoneOffset.UTC),
                            result.getDownload(),
                            result.getUpload(),
                            result.getPing())
                    )
            );
            return entities;
        }
        return null;
    }
}
