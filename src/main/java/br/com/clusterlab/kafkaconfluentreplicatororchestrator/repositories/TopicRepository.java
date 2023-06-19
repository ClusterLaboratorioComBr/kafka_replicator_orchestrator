package br.com.clusterlab.kafkaconfluentreplicatororchestrator.repositories;

import br.com.clusterlab.kafkaconfluentreplicatororchestrator.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
}
