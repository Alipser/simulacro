package RIWI.simulacro.api.dtos.reqest;

import java.time.LocalDate;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentRequest {

    private int id;
    
    @NotBlank(message = "Tittle must be valid")
    @Size(min=4, max = 100, message="Tittles allowed between 4chars to 100chars")
    private String assignmentTitle;

    private String definition;

    @FutureOrPresent(message = "Due Date have to be a future Date")
    private LocalDate duDate;

    @NotNull(message = "Lesson Id must not be null")
    private int lessonId;

}
