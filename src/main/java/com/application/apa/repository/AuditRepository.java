package com.application.apa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.apa.model.AuditLog;

public interface AuditRepository extends JpaRepository<AuditLog, Long>{

}
