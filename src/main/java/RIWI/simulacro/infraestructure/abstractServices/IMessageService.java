package RIWI.simulacro.infraestructure.abstractServices;

import RIWI.simulacro.api.dtos.reqest.MessageRequest;
import RIWI.simulacro.api.dtos.response.MessageResponse;

public interface IMessageService extends CrudAbstractService<MessageRequest, MessageResponse, Integer> {

}
