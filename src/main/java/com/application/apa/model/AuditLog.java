package com.application.apa.model;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table( name = "audit_logs")
public class AuditLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String entityName;
	
	private String entityId;
	
	private String action;
	
	private String changedBy;
	
	private LocalDateTime timestamp;
	
	@Column(columnDefinition = "TEXT")
	private String  oldValue;

	@Column(columnDefinition = "TEXT")
	private String newValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime localDateTime) {
		this.timestamp = localDateTime;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public AuditLog(Long id, String entityName, String entityId, String action, String changedBy, LocalDateTime timestamp,
			String oldValue, String newValue) {
		super();
		this.id = id;
		this.entityName = entityName;
		this.entityId = entityId;
		this.action = action;
		this.changedBy = changedBy;
		this.timestamp = timestamp;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	public AuditLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
