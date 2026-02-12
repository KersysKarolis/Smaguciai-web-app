package org.smaguciai.configs;

import org.smaguciai.entities.Order;
import org.smaguciai.events.OrderApprovedEvent;
import org.smaguciai.services.EmailService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OrderEmailListener {
    private final EmailService emailService;

    public OrderEmailListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @TransactionalEventListener(phase= TransactionPhase.AFTER_COMMIT)
        public void handleOrderApproved(OrderApprovedEvent event){
        Order order = event.order();
        emailService.sendApproved(order);
        }
}


