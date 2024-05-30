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
public class LessonRequest {

        
        private int id;
        
        @NotBlank(message= "Lesson Tittle cant be null")
        @Size(min=4, max = 100, message="Title allowed between 4chars to 100chars")
        private String lessonTitle;

        private String content;

        private int courseId;
       
    
}
