package az.meetup.ms.redis.publisher.controller;

import az.meetup.ms.redis.publisher.model.MessageDto;
import az.meetup.ms.redis.publisher.service.PublisherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/redis-publisher",
        headers = {"Accept=" + MediaType.APPLICATION_JSON_UTF8_VALUE},
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Controller to test PubSub publisher via Redis")
public class PublisherController {

    private final PublisherService service;

    public PublisherController(PublisherService service) {
        this.service = service;
    }

    @PostMapping("/publish")
    @ApiOperation("Method to publish message via Redis")
    public ResponseEntity testRedisPublishing(@RequestBody MessageDto request) {
        service.publishViaRedis(request);
        return ResponseEntity.ok().build();
    }
}
