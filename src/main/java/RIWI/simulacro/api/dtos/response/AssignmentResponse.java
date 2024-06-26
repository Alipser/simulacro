package RIWI.simulacro.api.dtos.response;

import java.time.LocalDate;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AssignmentResponse {

    private int id; 
   
    private String assignmentTitle;

    private String definition;
    
    private LocalDate duDate;

    private LessonResponse lesson;

}
