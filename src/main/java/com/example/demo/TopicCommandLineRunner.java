package com.example.demo;

import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.TopicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TopicCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(TopicCommandLineRunner.class);

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void run(String... args) throws Exception {
        Topic topic = new Topic("java", "Core Java", "Core Java Description");
        topicRepository.save(topic);
        log.info("New Topic is created: " + topic);

        Course course = new Course("security", "Java security", "Java security courses");
        course.setTopic(topic);
        courseRepository.save(course);
        log.info("New Course is created: " + course);
    }
}
