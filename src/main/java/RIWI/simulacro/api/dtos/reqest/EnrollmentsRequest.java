package RIWI.simulacro.api.dtos.reqest;

import java.time.LocalDate;
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
public class EnrollmentsRequest {

        
        private int id;
        @NotNull(message = "User id realated to this enrollment must be not null")
        private int userid;
        @NotNull(message = "User id realated to this enrollment must be not null")
        private int courseid;
        @NotBlank(message = "Identify the enrollmet Date, It cant be Null")
        private LocalDate enrrolmentDate;
    
}
