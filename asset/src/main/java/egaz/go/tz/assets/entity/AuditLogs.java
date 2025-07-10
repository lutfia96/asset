package egaz.go.tz.assets.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class AuditLogs {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "check_log_id", updatable = false, nullable = false)
    private UUID id;


    private String action;         // e.g., CREATE, UPDATE, DELETE
    private String entityName;     // e.g., "User", "Asset"
    private String entityId;       // e.g., "123"
    private String message;        // Optional details
    private String performedBy;    // Username or system
    private LocalDateTime timestamp;
}

