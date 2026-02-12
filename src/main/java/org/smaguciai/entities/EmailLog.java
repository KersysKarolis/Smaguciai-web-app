package org.smaguciai.entities;

import jakarta.persistence.*;
import org.smaguciai.enumerators.EmailStatus;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
public class EmailLog {
    @Id
    @GeneratedValue
    private Long id;
    private String toEmail;
    @Enumerated(EnumType.STRING)
    private EmailStatus status; //pending, sent, failed
    private int attempts;
    private LocalDateTime lastAttempt;

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public EmailStatus getStatus() {
        return status;
    }

    public void setStatus(EmailStatus status) {
        this.status = status;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public LocalDateTime getLastAttempt() {
        return lastAttempt;
    }

    public void setLastAttempt(LocalDateTime lastAttempt) {
        this.lastAttempt = lastAttempt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
