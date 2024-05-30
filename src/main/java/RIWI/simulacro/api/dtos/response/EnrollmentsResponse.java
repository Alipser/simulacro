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
public class EnrollmentsResponse {

        
        private int id;
        
        private UserResponse user;
       
        private CourseResponse course;
        
        private LocalDate enrrolmentDate;
    
}
