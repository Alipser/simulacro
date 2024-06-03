package RIWI.simulacro.infraestructure.abstractServices;

import RIWI.simulacro.api.dtos.reqest.LessonRequest;
import RIWI.simulacro.api.dtos.response.LessonResponse;
import RIWI.simulacro.api.dtos.response.LessonResponseFull;


public interface ILessonService extends CrudAbstractService<LessonRequest, LessonResponse, Integer>  {
    public LessonResponseFull getByIdAssignments(Integer id);
}
