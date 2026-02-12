package org.smaguciai.configs;

import lombok.RequiredArgsConstructor;
import org.smaguciai.entities.Order;
import org.smaguciai.enumerators.Characters;
import org.smaguciai.enumerators.OrderGenre;
import org.smaguciai.enumerators.OrderStatus;
import org.smaguciai.repositories.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final OrderRepository orderRepository;

    @Bean
    CommandLineRunner initOrders() {
        return args -> {

            if (orderRepository.count() > 0) {
                return; // kad nekurtų iš naujo kas kartą
            }

            Order o1 = new Order();
            o1.setChildName("Emilija");
            o1.setAge(6);
            o1.setAmountOfChildren(12);
            o1.setCharacter(Characters.ELZA);
            o1.setOrderGenre(OrderGenre.GIMTADIENIS);
            o1.setPhoneNumber("+37060000001");
            o1.setEmail("emilija@test.lt");
            o1.setLocation("Vilnius");
            o1.setNotes("Frozen tema, balti balionai");
            o1.setTitle("Emilijos gimtadienis");
            o1.setStartTime(LocalDateTime.now().plusDays(2).withHour(15));
            o1.setEndTime(LocalDateTime.now().plusDays(2).withHour(17));
            o1.setStatus(OrderStatus.LAUKIAMAS);

            Order o2 = new Order();
            o2.setChildName("Nojus");
            o2.setAge(5);
            o2.setAmountOfChildren(8);
            o2.setCharacter(Characters.ŽMOGUS_VORAS);
            o2.setOrderGenre(OrderGenre.GIMTADIENIS);
            o2.setPhoneNumber("+37060000002");
            o2.setEmail("nojus@test.lt");
            o2.setLocation("Kaunas");
            o2.setNotes("Superherojų tema");
            o2.setTitle("Nojaus gimtadienis");
            o2.setStartTime(LocalDateTime.now().plusDays(5).withHour(14));
            o2.setEndTime(LocalDateTime.now().plusDays(5).withHour(16));
            o2.setStatus(OrderStatus.LAUKIAMAS);

            Order o3 = new Order();
            o3.setChildName("Ugnė");
            o3.setAge(7);
            o3.setAmountOfChildren(15);
            o3.setCharacter(Characters.VIENARAGĖ);
            o3.setOrderGenre(OrderGenre.KRIKSTYNOS);
            o3.setPhoneNumber("+37060000003");
            o3.setEmail("ugne@test.lt");
            o3.setLocation("Klaipėda");
            o3.setNotes("Pastelinės spalvos");
            o3.setTitle("Ugnės krikštynos");
            o3.setStartTime(LocalDateTime.now().plusDays(10).withHour(13));
            o3.setEndTime(LocalDateTime.now().plusDays(10).withHour(16));
            o3.setStatus(OrderStatus.LAUKIAMAS);

            orderRepository.save(o1);
            orderRepository.save(o2);
            orderRepository.save(o3);

            System.out.println("✅ Testiniai užsakymai sukurti");
        };
    }
}