package az.meetup.ms.redis.subscriber.service;

import az.meetup.ms.redis.subscriber.dao.model.MessageEntity;
import az.meetup.ms.redis.subscriber.model.MessageDto;
import az.meetup.ms.redis.subscriber.dao.MessagesRepository;
import az.meetup.ms.redis.subscriber.mapper.MessagesMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessagesService {
    private final MessagesRepository messagesRepository;

    public MessagesService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public Long saveMessage(MessageDto dto) {
        MessageEntity entity = messagesRepository.save(MessagesMapper.INSTANCE.dtoToEntity(dto));
        return entity.getId();
    }

    public List<MessageDto> getMessages() {
        List<MessageEntity> entities = messagesRepository.findAll();
        return entities.stream()
                .map(MessagesMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }
}

