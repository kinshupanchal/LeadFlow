package com.example.leadflow.backend.entity;
import com.example.leadflow.backend.entity.LeadStatus;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.Instant;
import java.util.UUID;

@Getter
@Entity
@Table(
        name = "leads",
        indexes = {
                @Index(
                        name = "idx_leads_tenant",
                        columnList = "tenant_id"
                ),
                @Index(
                        name = "idx_leads_tenant_status",
                        columnList = "tenant_id,status"
                )
        }
)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String fullName;

    @Column(length = 20)
    private String phone;

    @Column(length = 150)
    private String email;

    @Column(length = 50)
    private String source;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private LeadStatus status = LeadStatus.NEW;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    public Lead(
            String fullName,
            String phone,
            String email,
            String source,
            Tenant tenant
    ) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.source = source;
        this.tenant = tenant;
    }

    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

    public void updateStatus(LeadStatus status) {
        this.status = status;
    }

    public void updateNotes(String notes) {
        this.notes = notes;
    }

    public void updateContactDetails(
            String fullName,
            String phone,
            String email
    ) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
    }
}