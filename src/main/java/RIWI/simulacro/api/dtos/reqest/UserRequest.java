package RIWI.simulacro.api.dtos.reqest;

import RIWI.simulacro.utils.enums.RoleUser;
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
public class UserRequest {

        private int id;

        @NotBlank(message = "username must be not null")
        @Size(min =4 , max =35 , message = "username size must be mayor than 4 and less than 35 chars")
        private String userName;
        @Size(min =4 , max =64 , message = "username size must be mayor than 4 and less than 64 chars")
        @NotBlank(message = "password  must be not null")
        private String password;

        @NotBlank(message = "email  must be not null")
        @Size(min =4 , max =100 , message = "username size must be mayor than 4 and less than 100 chars")
        private String email;

        @NotBlank(message = "Please introduce your fullname")
        @Size(min =4 , max =70 , message = "username size must be mayor than 4 and less than 70 chars")
        private String fullName;

        @NotBlank(message = "Select a Roll")
        private RoleUser rol;
}
