package com.rnd.gw.jason_validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

@Component
public class MqttMessageHandler {

    @Autowired
    private JsonValidatorService jsonValidatorService;

    public MessageHandler createHandler() {
        return message -> {
            String payload = message.getPayload().toString();
            System.out.println("Received message: " + payload);

            // Validate JSON
            boolean isValid = jsonValidatorService.validate(payload);
            if (isValid) {
                System.out.println("Valid JSON message.");
            } else {
                System.err.println("Invalid JSON message.");
            }
        };
    }
}

