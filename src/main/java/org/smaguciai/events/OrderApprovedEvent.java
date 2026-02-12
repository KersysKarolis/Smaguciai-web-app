package org.smaguciai.events;

import org.smaguciai.entities.Order;

public record OrderApprovedEvent(Order order) {
}
