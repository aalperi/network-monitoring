package fi.shednet.networkmonitoring;

import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

//@Entity
@Data
public class MonitoringEntityModel {
    private Long event_id;
    private LocalDateTime time;
    private BigDecimal ping;
    private BigDecimal download;
    private BigDecimal upload;
    private Long server_id;
    private Long server_name;
    private String ip_address;

}
