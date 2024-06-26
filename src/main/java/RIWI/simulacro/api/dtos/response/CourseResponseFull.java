package RIWI.simulacro.api.dtos.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CourseResponseFull extends CourseResponse {
     private List<MessageResponse> messages;
     private List<LessonResponse> lessons;
}
