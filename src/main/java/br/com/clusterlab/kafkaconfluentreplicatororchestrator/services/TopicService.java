package br.com.clusterlab.kafkaconfluentreplicatororchestrator.services;

import br.com.clusterlab.kafkaconfluentreplicatororchestrator.dto.Request;
import br.com.clusterlab.kafkaconfluentreplicatororchestrator.dto.Response;
import br.com.clusterlab.kafkaconfluentreplicatororchestrator.entities.Topic;
import br.com.clusterlab.kafkaconfluentreplicatororchestrator.repositories.TopicRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;
    static Logger logger = LoggerFactory.getLogger(TopicService.class);
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
    public boolean existsTopicByNameAndClusterIs(String name, String cluster){
        return topicRepository.existsTopicByNameAndClusterIs(name,cluster);

    }
    public void deleteById(Integer id){
        topicRepository.deleteById(id);
    }
    public void saveAll(List<Topic> topics){
        topicRepository.saveAll(topics);
    }

    public void insertTopics(Request resquest){
        List<Topic> topicsEntities = new ArrayList<>();
        String cluster = resquest.getCluster();
        String action = resquest.getAction();
        List<String> servers = resquest.getServers();
        List<String> topics = resquest.getTopics();
        logger.info("Topics update. Action=" + action + ", cluster=" + cluster + ", servers=" + servers.toString() + ", topics_count=" + topics.size());

        if (Objects.equals(action, "redistribute")){
            List<Topic> topicsToDelete = findTopicsByCluster(cluster);
            for ( Topic topic: topicsToDelete){
                logger.info("Redistributing topics. Deleting all topics for cluster " + cluster + " before distribution.");
                deleteById(topic.getId());
            }
        }
        Map<String,Integer> server_map = this.getWorkerWithLessTopicsHashMap(servers,cluster);
        Integer associationCount = 0;
        for (String topic: topics){
            if ( existsTopicByNameAndClusterIs(topic, cluster) ){
                continue;
            }
            String designatedWorker = Collections.min(server_map.entrySet(), Map.Entry.comparingByValue()).getKey();
            Integer designatedWorkerCount = server_map.get(designatedWorker);
            Topic topicEntity = new Topic();
            topicEntity.setName(topic);
            topicEntity.setWorker(designatedWorker);
            topicEntity.setCluster(cluster);
            topicsEntities.add(topicEntity);
            server_map.remove(designatedWorker);
            server_map.put(designatedWorker,designatedWorkerCount + 1);
            associationCount++;
            logger.info("Associating topic (" + associationCount + ") " + topicEntity.getCluster() + "/" + topicEntity.getName() + ":" + topicEntity.getWorker());
        }
        logger.info("Updating " + topicsEntities.size() + " Topic(s) in database");
        saveAll(topicsEntities);

        topicsEntities = findTopicsByCluster(cluster);
        for (Topic topicEntity: topicsEntities){
            boolean found = false;
            for (String topic: topics){
                if (Objects.equals(topicEntity.getName(),topic) && Objects.equals(topicEntity.getCluster(),cluster) ){
                    found=true;
                    break;
                }
            }
            if ( ! found ){
                if (Objects.equals(topicEntity.getCluster(),cluster)){
                    deleteById(topicEntity.getId());
                    logger.info("Deleting orphan topic from records, " + topicEntity.getCluster() + "/" + topicEntity.getName() + ":" + topicEntity.getWorker());
                }

            }

        }
    }
    public Map<String,Integer> getWorkerWithLessTopicsHashMap(List<String> servers,String cluster){
        Map<String,Integer> server_map  = new HashMap<>();
        for (String server: servers){
            Integer count = this.countTopicByWorkerAndClusterIs(server,cluster);
            server_map.put(server,count);
            logger.info("Number of topics per worker: name=" + server + ", count=" + count);
        }
        return server_map;
    }


}
