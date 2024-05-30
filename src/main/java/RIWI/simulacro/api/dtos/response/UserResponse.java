package RIWI.simulacro.api.dtos.response;

import RIWI.simulacro.utils.enums.RoleUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

        private int id;

        private String userName;
   
        private String password;

        private String email;

        private String fullName;

        private RoleUser rol;
}
