package br.com.clusterlab.kafkaconfluentreplicatororchestrator.services;

import br.com.clusterlab.kafkaconfluentreplicatororchestrator.dto.Request;
import br.com.clusterlab.kafkaconfluentreplicatororchestrator.dto.Response;
import br.com.clusterlab.kafkaconfluentreplicatororchestrator.entities.Topic;
import br.com.clusterlab.kafkaconfluentreplicatororchestrator.repositories.TopicRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;
    public List<Topic> findAll(){
        return topicRepository.findAll();
    }
    public List<Topic> findTopicsByCluster(String cluster){
        return topicRepository.findTopicsByCluster(cluster);
    }
    public List<Topic> findTopicsByWorker(String worker){
        return topicRepository.findTopicsByWorker(worker);
    }
    public List<Topic> findTopicsByName(String name){
        return topicRepository.findTopicsByName(name);
    }
    public Integer countTopicByWorkerAndClusterIs(String worker, String cluster){ return topicRepository.countTopicByWorkerAndClusterIs(worker,cluster);}
    public static String getEntitiesAsString(List<Topic> topics) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        List<String> responses = new ArrayList<>();
        for(Topic topic : topics){
            Response response = new Response();
            response.setName(topic.getName());
            response.setCluster(topic.getCluster());
            response.setWorker(topic.getWorker());
            response.setUpdated(String.valueOf(topic.getUpdated()));
            responses.add(om.writeValueAsString(response));
        }
        return responses.toString();
    }
//    public void insertTopics(Request resquest){
//        List<Topic> topicsEntities = new ArrayList<>();
//        String cluster = resquest.getCluster();
//        String action = resquest.getAction();
//        List<String> servers = resquest.getServers();
//        List<String> topics = resquest.getTopics();
//
//        topicRepository.saveAll(topicsEntities);
//    }
    public Map<String,Integer> getWorkerWithLessTopicsHashMap(List<String> servers,String cluster){
        Map<String,Integer> server_map  = new HashMap<>();
        for (String server: servers){
            server_map.put(server,this.countTopicByWorkerAndClusterIs(server,cluster));
        }
        return server_map;
    }

}
