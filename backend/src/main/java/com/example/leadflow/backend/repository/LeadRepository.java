package com.example.leadflow.backend.repository;

import com.example.leadflow.backend.entity.Lead;
import com.example.leadflow.backend.entity.LeadStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LeadRepository
        extends JpaRepository<Lead, UUID> {

    List<Lead> findAllByTenant_Id(UUID tenantId);

    Optional<Lead> findByIdAndTenant_Id(
            UUID leadId,
            UUID tenantId
    );

    List<Lead> findAllByTenant_IdAndStatus(
            UUID tenantId,
            LeadStatus status
    );
}