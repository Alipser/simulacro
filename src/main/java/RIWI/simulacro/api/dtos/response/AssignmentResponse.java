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
public class AssignmentResponse {

    private int id; 
   
    private String assignmentTitle;

    private String definition;
    
    private LocalDate duDate;

    private LessonResponse lesson;

}
