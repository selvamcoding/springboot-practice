package com.example.demo.controller;

import com.example.demo.Topic;
import com.example.demo.repository.TopicRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v2")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/topics")
    @ApiOperation(value = "Retrieve Topic Details")
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @GetMapping("/topics/{id}")
    public Optional<Topic> getTopic(@PathVariable String id) throws Exception {
        return topicRepository.findById(id);
    }

    @PostMapping(value="/topics")
    public ResponseEntity<Object> addTopic(@Valid @RequestBody Topic topic) {
        Topic savedTopic = topicRepository.save(topic);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedTopic.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value="/topics/{id}")
    public void updateTopic(@RequestBody Topic topic, @PathVariable String id) {
        topicRepository.save(topic);
    }

    @DeleteMapping(value="/topics/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  void deleteTopic(@PathVariable String id) {
        topicRepository.deleteById(id);
    }
}
