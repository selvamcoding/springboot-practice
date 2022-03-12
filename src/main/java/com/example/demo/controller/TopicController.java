package com.example.demo.controller;

import com.example.demo.model.Topic;
import com.example.demo.repository.TopicRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/v2/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping
    @ApiOperation(value = "Retrieve Topic Details")
    public Iterable<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Topic> getTopic(@PathVariable String id) throws Exception {
        return topicRepository.findById(id);
    }

    @PostMapping
    public ResponseEntity<Object> addTopic(@Valid @RequestBody Topic topic) {
        Topic savedTopic = topicRepository.save(topic);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedTopic.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Object> updateTopic(@RequestBody Topic topic, @PathVariable String id) {
        // topicRepository.save(topic);
        return (topicRepository.existsById(id))
                ? new ResponseEntity<>(topicRepository.save(topic), HttpStatus.OK)
                : new ResponseEntity<>(topicRepository.save(topic), HttpStatus.CREATED);
    }

    @DeleteMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  void deleteTopic(@PathVariable String id) {
        topicRepository.deleteById(id);
    }
}
