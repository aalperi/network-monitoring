package fi.shednet.networkmonitoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class NetworkMonitoringService {

    int PAGE_SIZE = 576;
    @Autowired
    private MonitoringEntityRepository monitoringEntityRepository;

    public List<MonitoringEntityModel> getMonitoringEvents(String useCase) {
        if (useCase.equals("all")) {
            return this.monitoringEntityRepository.findAll();
        } else
            return this.monitoringEntityRepository.findFirst576ByOrderByTimestampDesc();
    }

    public List<MonitoringEntityModel> getPageableMonitoringEvents(Integer page){
        Pageable pageable = PageRequest.of(page-1,PAGE_SIZE);
        Page events=this.monitoringEntityRepository.findAll(pageable);
        return events.getContent();
    }

    public List<List<Object>> getDownloadUpload(String useCase) {
        List<MonitoringEntityModel> all = getMonitoringEvents(useCase);
        if (!all.isEmpty()) {
            List<List<Object>> entities = new ArrayList<>();
            BigDecimal sumDownload = BigDecimal.valueOf(0);
            BigDecimal sumUpload = BigDecimal.valueOf(0);
            for (int i = 0; i < all.size(); i++) {
                sumDownload=sumDownload.add(all.get(i).getDownload().divide(BigDecimal.valueOf(1000000L),2, RoundingMode.HALF_UP));
                sumUpload=sumUpload.add(all.get(i).getUpload().divide(BigDecimal.valueOf(1000000L),2, RoundingMode.HALF_UP));
            }
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i) != null) {
                    if (all.get(i).getTimestamp() != null) {
                        entities.add(List.of(
                                all.get(i).getTimestamp().toInstant().toEpochMilli(),
                                all.get(i).getDownload().divide(BigDecimal.valueOf(1000000L),2, RoundingMode.HALF_UP),
                                all.get(i).getUpload().divide(BigDecimal.valueOf(1000000L),2, RoundingMode.HALF_UP),
                                sumDownload.divide(BigDecimal.valueOf(all.size()),2, RoundingMode.HALF_UP),
                                sumUpload.divide(BigDecimal.valueOf(all.size()),2, RoundingMode.HALF_UP)
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
            BigDecimal sumPing = BigDecimal.valueOf(0);
            for (int i = 0; i < all.size(); i++) {
                sumPing=sumPing.add(all.get(i).getPing());
            }
            List<List<Object>> entities = new ArrayList<>();
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i) != null) {
                    if (all.get(i).getTimestamp() != null) {
                        entities.add(List.of(
                                all.get(i).getTimestamp().toInstant().toEpochMilli(),
                                all.get(i).getPing(),
                                sumPing.divide(BigDecimal.valueOf(all.size()),2, RoundingMode.HALF_UP)
                        ));
                    }
                }
            }
            return entities;
        }
        return null;
    }
    public List<List<Object>> getPageableDownloadUpload(Integer page) {
        List<MonitoringEntityModel> all = getPageableMonitoringEvents(page);
        if (!all.isEmpty()) {
            List<List<Object>> entities = new ArrayList<>();
            BigDecimal sumDownload = BigDecimal.valueOf(0);
            BigDecimal sumUpload = BigDecimal.valueOf(0);
            for (int i = 0; i < all.size(); i++) {
                sumDownload=sumDownload.add(all.get(i).getDownload().divide(BigDecimal.valueOf(1000000L),2, RoundingMode.HALF_UP));
                sumUpload=sumUpload.add(all.get(i).getUpload().divide(BigDecimal.valueOf(1000000L),2, RoundingMode.HALF_UP));
            }
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i) != null) {
                    if (all.get(i).getTimestamp() != null) {
                        entities.add(List.of(
                                all.get(i).getTimestamp().toInstant().toEpochMilli(),
                                all.get(i).getDownload().divide(BigDecimal.valueOf(1000000L),2, RoundingMode.HALF_UP),
                                all.get(i).getUpload().divide(BigDecimal.valueOf(1000000L),2, RoundingMode.HALF_UP),
                                sumDownload.divide(BigDecimal.valueOf(all.size()),2, RoundingMode.HALF_UP),
                                sumUpload.divide(BigDecimal.valueOf(all.size()),2, RoundingMode.HALF_UP)
                        ));

                    }
                }
            }
            return entities;
        }
        return null;
    }

    public List<List<Object>> getPageablePing(Integer page) {
        List<MonitoringEntityModel> all = getPageableMonitoringEvents(page);
        if (!all.isEmpty()) {
            BigDecimal sumPing = BigDecimal.valueOf(0);
            for (int i = 0; i < all.size(); i++) {
                sumPing=sumPing.add(all.get(i).getPing());
            }
            List<List<Object>> entities = new ArrayList<>();
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i) != null) {
                    if (all.get(i).getTimestamp() != null) {
                        entities.add(List.of(
                                all.get(i).getTimestamp().toInstant().toEpochMilli(),
                                all.get(i).getPing(),
                                sumPing.divide(BigDecimal.valueOf(all.size()),2, RoundingMode.HALF_UP)
                        ));
                    }
                }
            }
            return entities;
        }
        return null;
    }
}
