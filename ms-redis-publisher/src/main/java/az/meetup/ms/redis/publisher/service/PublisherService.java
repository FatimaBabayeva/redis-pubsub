package az.meetup.ms.redis.publisher.service;

import az.meetup.ms.redis.publisher.model.MessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.redisson.api.RTopicAsync;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

    private static final String TEST_REDIS_TOPIC = "test-redis-topic";
    private final RedissonClient redissonClient;
    private final ObjectMapper objectMapper;
    private static Logger logger = LogManager.getLogger(PublisherService.class);

    public PublisherService(RedissonClient redissonClient, ObjectMapper objectMapper) {
        this.redissonClient = redissonClient;
        this.objectMapper = objectMapper;
    }

    public void publishViaRedis(MessageDto dto) {

        try {
            String messageString = objectMapper.writeValueAsString(dto);

            RTopicAsync publishTopic = redissonClient.getTopic(TEST_REDIS_TOPIC);
            publishTopic.publishAsync(messageString);
        } catch (Exception e) {
            logger.error("Publish message via Redis throws exception", e);
        }
    }
}
