package az.meetup.ms.redis.publisher.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Model to test message publishing")
public class MessageDto {

    @ApiModelProperty("Message to publish")
    private String message;
}


