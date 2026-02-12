package org.smaguciai.services;

import org.smaguciai.entities.EmailLog;
import org.smaguciai.entities.Order;
import org.smaguciai.enumerators.EmailStatus;
import org.smaguciai.repositories.EmailLogRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmailService {
    private EmailLogRepository emailLogRepository;

    public EmailService(EmailLogRepository emailLogRepository) {
        this.emailLogRepository = emailLogRepository;
    }

    public void sendApproved(Order order){
        EmailLog emailLog = new EmailLog();
        emailLog.setToEmail(order.getEmail());
        EmailLog log = emailLogRepository.save(emailLog);
        try{
            System.out.printf("Siunciamas email: " + emailLog.getToEmail() + emailLog.getAttempts() + emailLog.getLastAttempt() + emailLog.getId() + emailLog.getStatus());
            log.setStatus(EmailStatus.SENT);
            System.out.printf("Siunciamas email: " + emailLog.getToEmail() + emailLog.getAttempts() + emailLog.getLastAttempt() + emailLog.getId() + emailLog.getStatus());
            }catch(Exception e){
            log.setStatus(EmailStatus.FAILED);
        }
        emailLogRepository.save(log);
    }
  @Scheduled(fixedDelay = 60000)
public void retryFailedEmails(){
        List<EmailLog> failed = emailLogRepository.findByStatusAndAttempts(EmailStatus.FAILED, 3);
        for(EmailLog log :failed){
            trySendAgain(log);
        }
  }
  private void trySendAgain(EmailLog log){
        try{
            log.setStatus(EmailStatus.SENT);
        } catch(Exception e){
            log.setStatus(EmailStatus.FAILED);
        }
        emailLogRepository.save(log);
  }

}
