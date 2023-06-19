package br.com.clusterlab.kafkaconfluentreplicatororchestrator.services;

import br.com.clusterlab.kafkaconfluentreplicatororchestrator.entities.Topic;
import br.com.clusterlab.kafkaconfluentreplicatororchestrator.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> findAll(){
        return topicRepository.findAll();
    }
}
