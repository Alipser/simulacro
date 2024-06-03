package RIWI.simulacro.infraestructure.abstractServices;

import RIWI.simulacro.api.dtos.reqest.CourseRequest;
import RIWI.simulacro.api.dtos.response.CourseResponse;
import RIWI.simulacro.api.dtos.response.CourseResponseFull;

public interface ICourseService extends CrudAbstractService<CourseRequest, CourseResponse, Integer> {
    public CourseResponseFull getByIdLessons(Integer id);
}
