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
        
        @NotBlank(message= "Content Cant be Null")
        private String messageContent;
     
        private int senderId;
       
        private int reciverId;
       
        private int courseId;
}
