package RIWI.simulacro.api.dtos.reqest;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequest {
    private int id;
    @NotBlank(message = "Name must be not Null")

    @NotBlank(message = "Name must be valid")
    @Size(min=4, max = 100, message="Name allowed between 4chars to 100chars")
    private String coursName;

    private String description;

    private int instructorId; 
}
