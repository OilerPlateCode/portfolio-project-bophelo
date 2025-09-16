package com.oilerplatecode.bophelo.run;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "run")
public class Run {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 250)
    @Column(nullable = false, length = 250)
    private String title;

    @NotNull
    @Column(name = "started_on", nullable = false)
    private LocalDateTime startedOn;

    @Column(name = "completed_on")
    private LocalDateTime completedOn;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Integer meters;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Location location;

    protected Run() {}

    public Run(String title,
               LocalDateTime startedOn,
               LocalDateTime completedOn,
               Integer meters,
               Location location) {
        if (completedOn != null && !completedOn.isAfter(startedOn)) {
            throw new IllegalArgumentException("completedOn must be after startedOn");
        }
        this.title = title;
        this.startedOn = startedOn;
        this.completedOn = completedOn;
        this.meters = meters;
        this.location = location;
    }

    public Integer getId() { return id; }
    public String getTitle() { return title; }
    public LocalDateTime getStartedOn() { return startedOn; }
    public LocalDateTime getCompletedOn() { return completedOn; }
    public Integer getMeters() { return meters; }
    public Location getLocation() { return location; }

    public void setCompletedOn(LocalDateTime completedOn) {
        if (completedOn != null && !completedOn.isAfter(this.startedOn)) {
            throw new IllegalArgumentException("completedOn must be after startedOn");
        }
        this.completedOn = completedOn;
    }
}
