package egaz.go.tz.assets.repository;

import egaz.go.tz.assets.entity.AuditLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogsRepo extends JpaRepository<AuditLogs, Long> {
}
