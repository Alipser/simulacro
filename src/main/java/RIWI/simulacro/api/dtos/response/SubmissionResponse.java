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
public class SubmissionResponse {

        private int id;
        
        private String content;

        private LocalDate submissionDate;

        private double grade;

        private UserResponse user;

        private AssignmentResponse assignment;
}
