package com.example.demo.controller;

import com.example.demo.model.Topic;
import com.example.demo.service.TopicServiceWithOutJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/v1")
public class TopicControllerThroughService {

    @Autowired
    private TopicServiceWithOutJPA topicService;

    @GetMapping("/topics")
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    @GetMapping("/topics/{id}")
    public Topic getTopic(@PathVariable String id) throws Exception {
         Topic topic = topicService.getTopic(id);
         return topic;
    }

    @PostMapping(value="/topics")
    public ResponseEntity<Object> addTopic(@Valid @RequestBody Topic topic) {
        Topic savedTopic = topicService.addTopic(topic);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedTopic.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value="/topics/{id}")
    public void updateTopic(@RequestBody Topic topic, @PathVariable String id) {
        topicService.updateTopic(id, topic);
    }

    @DeleteMapping(value="/topics/{id}")
    public  void deleteTopic(@PathVariable String id) {
        topicService.deleteTopic(id);
    }
}
