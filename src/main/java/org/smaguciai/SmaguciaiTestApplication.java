package org.smaguciai;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SmaguciaiTestApplication {
    public static void main(String[] args){
        SpringApplication.run(SmaguciaiTestApplication.class, args);
    }
    @Component
    public class WsTest {

        public WsTest(SimpMessagingTemplate template) {
            System.out.println("✅ SimpMessagingTemplate OK");
        }
    }

}
