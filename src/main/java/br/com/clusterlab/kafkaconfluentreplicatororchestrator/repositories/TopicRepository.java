package br.com.clusterlab.kafkaconfluentreplicatororchestrator.repositories;

import br.com.clusterlab.kafkaconfluentreplicatororchestrator.entities.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    List<Topic> findTopicsByCluster(String cluster);
    List<Topic> findTopicsByWorker(String worker);
    List<Topic> findTopicsByName(String name);
    List<Topic> findTopicsByUpdatedAfter(LocalDate date);
}
