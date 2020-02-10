package az.meetup.ms.redis.subscriber.dao;

import az.meetup.ms.redis.subscriber.dao.model.MessageEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessagesRepository extends CrudRepository<MessageEntity, Long> {
    List<MessageEntity> findAll();
}
