package RIWI.simulacro.infraestructure.abstractServices;


import RIWI.simulacro.api.dtos.reqest.AssignmentRequest;
import RIWI.simulacro.api.dtos.response.AssginmenteResponseFull;
import RIWI.simulacro.api.dtos.response.AssignmentResponse;

public interface IAssignmentService extends CrudAbstractService<AssignmentRequest, AssignmentResponse, Integer>{
public AssginmenteResponseFull getByIdSubmission(Integer id);
}
