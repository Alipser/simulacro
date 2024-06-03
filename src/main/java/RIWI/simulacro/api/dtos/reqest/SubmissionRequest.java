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
public class SubmissionRequest {

        private int id;
        
        @NotBlank(message = "Content must be a text not null")
        private String content;
                
        private double grade;

        @NotNull
        private int userId;

        @NotNull
        private int assignmentId;
}
