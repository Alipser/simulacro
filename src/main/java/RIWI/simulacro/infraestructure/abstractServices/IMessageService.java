package RIWI.simulacro.infraestructure.abstractServices;

import java.util.List;

import RIWI.simulacro.api.dtos.reqest.MessageRequest;
import RIWI.simulacro.api.dtos.response.MessageResponse;

public interface IMessageService extends CrudAbstractService<MessageRequest, MessageResponse, Integer> {

    public List<MessageResponse> getBetweenUsers(int sender_id, int revicer_id);

}
