package com.backend.signos_vitales_productor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.backend.signos_vitales_productor.model.SignosVitales;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaProductorService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "signos_vitales";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(SignosVitales signosVitales) {
        try {
            String mensajeJson = objectMapper.writeValueAsString(signosVitales);
            
            //Enviar a Kafka
            kafkaTemplate.send(TOPIC, mensajeJson);
            
            System.out.println("Mensaje enviado a Kafka: " + mensajeJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
