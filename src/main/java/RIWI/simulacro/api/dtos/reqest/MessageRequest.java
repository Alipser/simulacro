package RIWI.simulacro.api.dtos.reqest;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageRequest {
        private int id;
        @NotBlank(message = "Content must not be null")
        private String messageContent;
        @NotNull(message = "Id sender must not be null")
        private int senderId;
        @NotNull(message = "Id reciber must not be null")
        private int reciverId;
        @NotNull(message = "Id course must not be null")
        private int courseId;
}
