package br.com.clusterlab.kafkaconfluentreplicatororchestrator.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name="topic")
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String cluster;

    private String worker;

    private final LocalDate updated = LocalDate.now();
}
