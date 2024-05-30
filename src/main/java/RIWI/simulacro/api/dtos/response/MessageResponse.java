package RIWI.simulacro.api.dtos.response;


import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageResponse {
        private int id;
        
        private String messageContent;
     
        private UserResponse sender;
  
        private UserResponse reciver;

        private CourseResponse course;

        private LocalDate creationDate;
}
