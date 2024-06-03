package RIWI.simulacro.api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CourseResponse {
    private int id;
    private String coursName;
    private String description;
    private UserResponse instructor; 
}
