package RIWI.simulacro.api.dtos.response;





import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonResponse {
      
        private int id;
        private String lessonTitle;
        private String content;
        private CourseResponse course;
        
}
