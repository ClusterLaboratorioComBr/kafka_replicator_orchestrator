package br.com.clusterlab.kafkaconfluentreplicatororchestrator.controllers;

import br.com.clusterlab.kafkaconfluentreplicatororchestrator.entities.Topic;
import br.com.clusterlab.kafkaconfluentreplicatororchestrator.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopicController {
    @Autowired
    private TopicService topicService;
    @GetMapping("/api/topic")
    public String topic(Model model) {
        model.addAttribute("topics", topicService.findAll());
        return "topic.html";
    }
}
