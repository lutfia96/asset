package egaz.go.tz.assets.service;

import egaz.go.tz.assets.entity.AuditLogs;
import egaz.go.tz.assets.repository.AuditLogsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogsRepo auditLogsRepo;

    public void auditLogs(String action, String entityName, String entityId, String message, String performdeBy) {
       AuditLogs auditLog = new AuditLogs();
        auditLog.setAction(action);
        auditLog.setEntityName(entityName);
        auditLog.setEntityId(entityId);
        auditLog.setMessage(message);
        auditLog.setPerformedBy(performdeBy);
        auditLog.setTimestamp(LocalDateTime.now());
         auditLogsRepo.save(auditLog);

    }

}
