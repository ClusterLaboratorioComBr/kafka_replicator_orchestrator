package br.com.clusterlab.kafkaconfluentreplicatororchestrator.controllers;

import br.com.clusterlab.kafkaconfluentreplicatororchestrator.dto.Request;
import br.com.clusterlab.kafkaconfluentreplicatororchestrator.dto.Response;
import br.com.clusterlab.kafkaconfluentreplicatororchestrator.entities.Topic;
import br.com.clusterlab.kafkaconfluentreplicatororchestrator.services.TopicService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TopicController {
    @Autowired
    private TopicService topicService;

    private ObjectMapper om = new ObjectMapper();
    @ResponseBody
    @GetMapping("/api/json/topic")
    public String getTopicsInJSON() throws JsonProcessingException {
        List<String> responses = new ArrayList<>();
        for(Topic topic : topicService.findAll()){
            Response response = new Response();
            response.setName(topic.getName());
            response.setCluster(topic.getCluster());
            response.setWorker(topic.getWorker());
            response.setUpdated(String.valueOf(topic.getUpdated()));
            responses.add(om.writeValueAsString(response));
        }
        return responses.toString();
    }
    @GetMapping("/api/html/topic")
    public String getTopicsInHTML(Model model) {
        model.addAttribute("topics", topicService.findAll());
        return "topic.html";
    }

    @GetMapping("/api/html/topic/{name}")
    public String getTopicByNameInHTML(@PathVariable String name, Model model) {
        model.addAttribute("topics", topicService.findTopicsByName(name));
        return "topic.html";
    }
    @GetMapping("/api/html/topic/worker/{worker}")
    public String getTopicsByWorkerInHTML(@PathVariable String worker, Model model) {
        model.addAttribute("topics", topicService.findTopicsByWorker(worker));
        return "topic.html";
    }
    @GetMapping("/api/html/topic/cluster/{cluster}")
    public String getTopicsByclusterInHTML(@PathVariable String cluster, Model model) {
        model.addAttribute("topics", topicService.findTopicsByCluster(cluster));
        return "topic.html";
    }
}
