package RIWI.simulacro.api.dtos.response;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class StudentResponse extends UserResponse {
        @Schema
        private List<MessageResponseInUser> sended;
        @Schema
        private List<MessageResponseInUser> recived;
        @Schema
        private List<EnrollmentsResponseInUser> enrollments;
        @Schema
        private List<SubmissionResponseInUser> submisions;
}
