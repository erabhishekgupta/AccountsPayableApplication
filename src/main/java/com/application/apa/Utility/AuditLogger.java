package com.application.apa.Utility;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.application.apa.repository.AuditRepository;
import com.application.apa.model.AuditLog;


@Component
public class AuditLogger {
	
	@Autowired
	private AuditRepository auditRepository;
	
	public void logChange(String entityName, String entityId,
			String action, String oldValue, String newValue, String changedBy) {

		AuditLog log = new AuditLog();
		log.setEntityName(entityName);
		log.setEntityId(entityId);
		log.setAction(action);
		log.setOldValue(oldValue);
		log.setNewValue(newValue);
		log.setChangedBy(changedBy);
		log.setTimestamp(LocalDateTime.now());
		
		auditRepository.save(log);
		
		
	}
}
