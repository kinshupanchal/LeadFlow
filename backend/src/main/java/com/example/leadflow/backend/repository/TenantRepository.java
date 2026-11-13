package com.example.leadflow.backend.repository;

import com.example.leadflow.backend.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TenantRepository
        extends JpaRepository<Tenant, UUID> {
}