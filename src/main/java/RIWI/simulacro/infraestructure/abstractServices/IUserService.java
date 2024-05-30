package RIWI.simulacro.infraestructure.abstractServices;

import RIWI.simulacro.api.dtos.reqest.UserRequest;
import RIWI.simulacro.api.dtos.response.UserResponse;


public interface IUserService extends CrudAbstractService<UserRequest, UserResponse, Integer> {
}
