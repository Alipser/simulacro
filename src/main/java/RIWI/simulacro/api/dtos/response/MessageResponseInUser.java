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
public class MessageResponseInUser {
    private int id;
        
    private String messageContent;
 
    private String sender;

    private String reciver;

    private String course;

    private LocalDate sendDate;

}
