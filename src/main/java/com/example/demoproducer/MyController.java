package com.example.demoproducer;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    public String send(String name, Integer age){
        Profile profile = new Profile();
        profile.setName(name);
        profile.setAge(age);
        rabbitTemplate.convertAndSend("simple.queue", profile);
        return name;
    }

    @PostMapping("/send2")
    public String send2(String name, Integer age) {
        Profile profile = new Profile();
        profile.setName(name);
        profile.setAge(age);
        rabbitTemplate.convertAndSend("my.topic", "profile_routing_key", profile);
        return name;
    }


}
