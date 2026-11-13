
package com.example.leadflow.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Getter
@Entity
@Table(name = "tenants")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String businessName;

    @Column(length = 100)
    private String industry;

    @Column(nullable = false, length = 50)
    private String timezone = "Asia/Kolkata";

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    public Tenant(String businessName, String industry) {
        this.businessName = businessName;
        this.industry = industry;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }

    public void updateBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void updateIndustry(String industry) {
        this.industry = industry;
    }

    public void updateTimezone(String timezone) {
        this.timezone = timezone;
    }
}
