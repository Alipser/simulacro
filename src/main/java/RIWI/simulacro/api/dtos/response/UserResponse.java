package RIWI.simulacro.api.dtos.response;

import RIWI.simulacro.utils.enums.RoleUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserResponse {

        private int id;

        private String userName;
   
        private String password;

        private String email;

        private String fullName;

        private RoleUser rol;
}
