package fi.shednet.networkmonitoring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor //because of hibernate
@Table(name="network_statistics")
public class MonitoringEntityModel{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "network_statistics_event_id_seq")
    @SequenceGenerator(name = "network_statistics_event_id_seq", sequenceName = "network_statisics_event_id_seq", allocationSize = 1)
    @Column(name="event_id")
    private Long event_id;
    private LocalDateTime time;
    private BigDecimal ping;
    private BigDecimal download;
    private BigDecimal upload;
    private Long server_id;
    private String server_name;
    private String ip_address;

}
