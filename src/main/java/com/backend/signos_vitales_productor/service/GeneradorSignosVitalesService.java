package com.backend.signos_vitales_productor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.backend.signos_vitales_productor.model.SignosVitales;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class GeneradorSignosVitalesService {

    @Autowired
    KafkaProductorService kafkaProducerService;
    private final Random random = new Random();

    private final List<SignosVitales> listaPacientes = Arrays.asList(
        // Pacientes con signos vitales normales
        new SignosVitales("Paciente-1", 75, 36.7, 120, 80),  
        new SignosVitales("Paciente-2", 80, 36.5, 122, 82),  
        new SignosVitales("Paciente-3", 72, 36.6, 118, 78),  
        new SignosVitales("Paciente-4", 85, 36.8, 125, 83),  
        new SignosVitales("Paciente-5", 70, 36.4, 119, 79),  
        new SignosVitales("Paciente-6", 40, 34.8, 90, 60), 
        new SignosVitales("Paciente-7", 160, 39.5, 170, 110),
        new SignosVitales("Paciente-8", 180, 40.0, 190, 120),
        new SignosVitales("Paciente-9", 50, 35.2, 80, 50),
        new SignosVitales("Paciente-10", 30, 33.5, 70, 40) 
    );
    


    @Scheduled(fixedRate = 15000)  //Ejecutar cada 30 segundos
    public void generarYEnviarSignosVitales() {
        //Selecciona un paciente aleatorio de la lista
        SignosVitales signosVitales = listaPacientes.get(random.nextInt(listaPacientes.size()));
    
        kafkaProducerService.sendMessage(signosVitales);

        System.out.println("Se envió la información del paciente: " + signosVitales.getNombrePaciente() +
                " | Ritmo Cardíaco: " + signosVitales.getRitmoCardiaco() +
                " | Temperatura: " + signosVitales.getTemperatura() +
                " | Presión: " + signosVitales.getPresionSistolica() + "/" + signosVitales.getPresionDiastolica());
    }
    
}