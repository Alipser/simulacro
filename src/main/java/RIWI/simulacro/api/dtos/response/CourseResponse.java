package RIWI.simulacro.api.dtos.response;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponse {
    private int id;
    private String coursName;
    private String description;
    private UserResponse instructor; 
}
