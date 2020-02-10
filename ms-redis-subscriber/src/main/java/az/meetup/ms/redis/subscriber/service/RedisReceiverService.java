package az.meetup.ms.redis.subscriber.service;

import az.meetup.ms.redis.subscriber.model.MessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.redisson.api.RTopicAsync;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RedisReceiverService {
    private static final String TEST_REDIS_TOPIC = "test-redis-topic";
    private final RedissonClient redissonClient;
    private final MessagesService messagesService;
    private ObjectMapper objectMapper;

    private static Logger logger = LogManager.getLogger(RedisReceiverService.class);

    public RedisReceiverService(RedissonClient redissonClient,
                                MessagesService messagesService,
                                ObjectMapper objectMapper) {
        this.redissonClient = redissonClient;
        this.messagesService = messagesService;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void registerSubscribers() {
        RTopicAsync topic = redissonClient.getTopic(TEST_REDIS_TOPIC);
        topic.addListenerAsync(String.class, (channel, msg) -> onMessageReceive(msg));
    }

    public void onMessageReceive(String message) {

        try {
            MessageDto dto = objectMapper.readValue(message, MessageDto.class);
            messagesService.saveMessage(dto);
        } catch (JsonProcessingException e) {
            logger.error("Receive redis message throws exception", e);
            e.printStackTrace();
        }
    }
}
