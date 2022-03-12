package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.Topic;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v2/topics")
public class CourseController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/{topicId}/courses")
    public List<Course> getAllCourses(@PathVariable String topicId) {
        Optional<Topic> topic = topicRepository.findById(topicId);
        return topic.get().getCourses();
    }


    @GetMapping("/{topicId}/courses/{id}")
    public Optional<Course> getCourse(@PathVariable String id, String topicId) {
        return courseRepository.findById(id);
    }

    @PostMapping("/{topicId}/courses")
    public ResponseEntity<Object> addCourse(@RequestBody Course course, @PathVariable String topicId) {
        Optional<Topic> topicOptional = topicRepository.findById(topicId);
        Topic topic = topicOptional.get();
        course.setTopic(topic);
        courseRepository.save(course);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @PutMapping(value="/{topicId}/courses/{id}")
    public ResponseEntity<Object> updateTopic(@RequestBody Course course, @PathVariable String topicId) {
        Optional<Topic> topicOptional = topicRepository.findById(topicId);
        Topic topic = topicOptional.get();
        course.setTopic(topic);
        courseRepository.save(course);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value="/{topicId}/courses/{id}")
    public  void deleteTopic(@PathVariable String id) {
        courseRepository.deleteById(id);
    }
}
