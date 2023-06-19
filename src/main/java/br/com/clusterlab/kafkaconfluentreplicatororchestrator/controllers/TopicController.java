package br.com.clusterlab.kafkaconfluentreplicatororchestrator.controllers;


import br.com.clusterlab.kafkaconfluentreplicatororchestrator.services.TopicService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TopicController {
    @Autowired
    private TopicService topicService;


    @ResponseBody
    @GetMapping("/api/json/topic")
    public String getTopicsInJSON() throws JsonProcessingException {
        return TopicService.getEntitiesAsString(topicService.findAll());
    }
    @ResponseBody
    @GetMapping("/api/json/topic/{name}")
    public String getTopicsByNameInJSON(@PathVariable String name) throws JsonProcessingException {
        return TopicService.getEntitiesAsString(topicService.findTopicsByName(name));
    }
    @ResponseBody
    @GetMapping("/api/json/topic/worker/{worker}")
    public String getTopicsByWorkerInJSON(@PathVariable String worker) throws JsonProcessingException {
        return TopicService.getEntitiesAsString(topicService.findTopicsByWorker(worker));
    }
    @ResponseBody
    @GetMapping("/api/json/topic/cluster/{cluster}")
    public String getTopicsByClusterInJSON(@PathVariable String cluster) throws JsonProcessingException {
        return TopicService.getEntitiesAsString(topicService.findTopicsByCluster(cluster));
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
