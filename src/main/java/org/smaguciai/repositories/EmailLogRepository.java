package org.smaguciai.repositories;

import org.smaguciai.entities.EmailLog;
import org.smaguciai.enumerators.EmailStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailLogRepository extends JpaRepository<EmailLog, Long> {
    List<EmailLog> findByStatusAndAttempts(
        EmailStatus status,
        int attempts
    );

}
