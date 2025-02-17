package com.backend.signos_vitales_productor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.backend.signos_vitales_productor.model.SignosVitales;
import com.backend.signos_vitales_productor.service.KafkaProductorService;

@RestController
@RequestMapping("/api/signos-vitales")
public class SignosVitalesController {
    @Autowired
    KafkaProductorService kafkaProductorService;

    @PostMapping("/enviar")
    public String enviarSignosVitales(@RequestBody SignosVitales signosVitales) {
        kafkaProductorService.sendMessage(signosVitales);
        return "Signos vitales enviados a Kafka: " + signosVitales.getNombrePaciente();
    }
}