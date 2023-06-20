package br.com.clusterlab.kafkaconfluentreplicatororchestrator.repositories;

import br.com.clusterlab.kafkaconfluentreplicatororchestrator.entities.Topic;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    List<Topic> findTopicsByCluster(String cluster);
    List<Topic> findTopicsByWorker(String worker);
    List<Topic> findTopicsByName(String name);
    Integer countTopicByWorkerAndClusterIs(String worker, String cluster);
    boolean existsTopicByNameAndClusterIs(String name, String cluster);

}
