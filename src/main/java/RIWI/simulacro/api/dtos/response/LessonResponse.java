package RIWI.simulacro.api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class LessonResponse {
      
        private int id;
        private String lessonTitle;
        private String content;
        private CourseResponse course;
        
}
