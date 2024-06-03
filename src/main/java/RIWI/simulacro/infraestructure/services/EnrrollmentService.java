package RIWI.simulacro.infraestructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import RIWI.simulacro.api.dtos.reqest.EnrollmentsRequest;
import RIWI.simulacro.api.dtos.response.CourseResponse;
import RIWI.simulacro.api.dtos.response.EnrollmentsResponse;
import RIWI.simulacro.api.dtos.response.UserResponse;
import RIWI.simulacro.domain.entities.Course;
import RIWI.simulacro.domain.entities.Enrollment;
import RIWI.simulacro.domain.entities.User;
import RIWI.simulacro.domain.repositories.CourseRespository;
import RIWI.simulacro.domain.repositories.EnrollmentRespository;
import RIWI.simulacro.domain.repositories.UserRespository;
import RIWI.simulacro.infraestructure.abstractServices.IEnrollmentService;
import RIWI.simulacro.utils.exceptcions.IdNotFoundException;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping(path = "/enrollments")
@AllArgsConstructor
public class EnrrollmentService implements IEnrollmentService {

    @Autowired
    private final UserRespository userRespository;
    
    @Autowired
    private final EnrollmentRespository enrollmentRespository;

    @Autowired 
    private final CourseRespository courseRespository;

    @Override
    public Page<EnrollmentsResponse> getAll(int page, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public EnrollmentsResponse create(EnrollmentsRequest request) {
        Enrollment enrollmentsaved = enrollmentRespository.save(convertEnrollmentRequestToEnrollment(request));
        return convertEnrollToEnrollmentsResponse(enrollmentsaved);
    }

    @Override
    public EnrollmentsResponse update(EnrollmentsRequest request, Integer id) {
        enrollmentRespository.findById(id).orElseThrow(()-> new IdNotFoundException("Enrollment"));
        Enrollment enrollmentsaved = enrollmentRespository.save(convertEnrollmentRequestToEnrollment(request));
        return convertEnrollToEnrollmentsResponse(enrollmentsaved);
    }

    @Override
    public void delete(Integer id) {
        enrollmentRespository.findById(id).orElseThrow(()-> new IdNotFoundException("Enrollment"));
        enrollmentRespository.deleteById(id);
    }

    @Override
    public EnrollmentsResponse getById(Integer id) {
        Enrollment enrollment = enrollmentRespository.findById(id).orElseThrow(()-> new IdNotFoundException("Enrollment"));
        return convertEnrollToEnrollmentsResponse(enrollment);
    }


    public Enrollment convertEnrollmentRequestToEnrollment(EnrollmentsRequest request){
        Enrollment enrollment = new Enrollment();
        User user = userRespository.findById(request.getUserid()).orElseThrow(()->new IdNotFoundException("User"));
        Course course = courseRespository.findById(request.getCourseid()).orElseThrow(()->new IdNotFoundException("Course"));
        BeanUtils.copyProperties(request, enrollment);
        enrollment.setCourse(course);
        enrollment.setUser(user);
        return enrollment;
    }

    public EnrollmentsResponse convertEnrollToEnrollmentsResponse(Enrollment entity){
        EnrollmentsResponse enrollmentsResponse = new EnrollmentsResponse();
        BeanUtils.copyProperties(entity, enrollmentsResponse);
        enrollmentsResponse.setCourse(convertCourseToCourseResponse(entity.getCourse()));
        enrollmentsResponse.setUser(convertUserToUserResponse(entity.getUser()));

        return enrollmentsResponse;

    }
     public UserResponse convertUserToUserResponse(User entity){
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

     public CourseResponse convertCourseToCourseResponse(Course entity){
        CourseResponse response = new CourseResponse();
        BeanUtils.copyProperties(entity, response);
        response.setInstructor(convertUserToUserResponse(entity.getInstructor()));
        return response;
    }




}
