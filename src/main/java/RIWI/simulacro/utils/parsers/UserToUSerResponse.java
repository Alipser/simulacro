package RIWI.simulacro.utils.parsers;

import org.apache.catalina.User;

import RIWI.simulacro.api.dtos.response.UserResponse;

public class UserToUSerResponse {

    public UserResponse response;

    public UserResponse UserToUSerResponse(User entity) {
        SuperParser parseador = new SuperParser<>(response.getClass());
        response = (UserResponse) parseador.entityToResponse(entity);
        return response;
    }  
}
