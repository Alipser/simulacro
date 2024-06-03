package RIWI.simulacro.infraestructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import RIWI.simulacro.api.dtos.reqest.AssignmentRequest;
import RIWI.simulacro.api.dtos.response.AssginmenteResponseFull;
import RIWI.simulacro.api.dtos.response.AssignmentResponse;
import RIWI.simulacro.api.dtos.response.CourseResponse;
import RIWI.simulacro.api.dtos.response.LessonResponse;
import RIWI.simulacro.api.dtos.response.SubmissionResponse;
import RIWI.simulacro.api.dtos.response.UserResponse;
import RIWI.simulacro.domain.entities.Assignment;
import RIWI.simulacro.domain.entities.Course;
import RIWI.simulacro.domain.entities.Lesson;
import RIWI.simulacro.domain.entities.Submission;
import RIWI.simulacro.domain.entities.User;
import RIWI.simulacro.domain.repositories.AssignmentRespository;
import RIWI.simulacro.domain.repositories.LessonRespository;
import RIWI.simulacro.infraestructure.abstractServices.IAssignmentService;
import RIWI.simulacro.utils.exceptcions.IdNotFoundException;

import lombok.AllArgsConstructor;


@Service
@Transactional
@AllArgsConstructor
public class AssignmentService implements IAssignmentService {

    @Autowired
    private final AssignmentRespository assignmentRespository;
    
    @Autowired
    private final LessonRespository lessonRespository;

   

    @Override
    public Page<AssignmentResponse> getAll(int page, int size) {
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public AssignmentResponse create(AssignmentRequest request) { 
        Assignment response = assignmentRespository.save(convertAssignmentRequestToAssignment(request)) ; 
        return(convertAssignmentToAssignmentResponse(response));
    }

    @Override
    public AssignmentResponse update(AssignmentRequest request, Integer id) {
        assignmentRespository.findById(id).orElseThrow(() -> new IdNotFoundException("Assignment"));
        Assignment response = assignmentRespository.save(convertAssignmentRequestToAssignment(request)) ; 
        return(convertAssignmentToAssignmentResponse(response));
    }

    @Override
    public void delete(Integer id) {
        assignmentRespository.findById(id).orElseThrow(() -> new IdNotFoundException("Assignment"));
        assignmentRespository.deleteById(id);
    }

    @Override
    public AssignmentResponse getById(Integer id) {
        AssignmentResponse response =convertAssignmentToAssignmentResponse(assignmentRespository.findById(id).orElseThrow(()->new IdNotFoundException("Assginmen")));
        return response;
    }

    @Override
    public AssginmenteResponseFull getByIdSubmission(Integer id) {
        AssginmenteResponseFull response =convertAssignmentToAssignmentResponseFull(assignmentRespository.findById(id).orElseThrow(()->new IdNotFoundException("Assignment")));
        return response;
    }

    public AssignmentResponse convertAssignmentToAssignmentResponse(Assignment entity){
        AssignmentResponse response = new AssignmentResponse();
        BeanUtils.copyProperties(entity, response);
        response.setLesson(convertLessonToLessonResponse(entity.getLesson())); 
        return response;
    }

    public AssginmenteResponseFull convertAssignmentToAssignmentResponseFull(Assignment entity){
        AssginmenteResponseFull response = new AssginmenteResponseFull();
        BeanUtils.copyProperties(entity, response);
        response.setLesson(convertLessonToLessonResponse(entity.getLesson())); 
        response.setSubmisions(entity.getSubmisions().stream().map(this::convertSubmiToSubmissionResponse).toList());
        return response;
    }

 public SubmissionResponse convertSubmiToSubmissionResponse(Submission entity){
        SubmissionResponse response = new SubmissionResponse();
        BeanUtils.copyProperties(entity, response);
        response.setUser(convertUserToUserResponse(entity.getUser()));
        response.setAssignment(null);
        return response;
    }

    public Assignment convertAssignmentRequestToAssignment(AssignmentRequest request){
        Assignment response = new Assignment();
        BeanUtils.copyProperties(request, response);
        Lesson lesson = lessonRespository.findById(request.getLessonId())
        .orElseThrow(() -> new IdNotFoundException("Lesson"));
        response.setLesson(lesson);          
        return response;
    }

    public LessonResponse convertLessonToLessonResponse(Lesson entity){
        LessonResponse response = new LessonResponse();
        BeanUtils.copyProperties(entity, response);
        response.setCourse(convertCourseToCourseResponse(entity.getCourse()));
        
        return response;
    }
     public CourseResponse convertCourseToCourseResponse(Course entity){
        CourseResponse response = new CourseResponse();
        BeanUtils.copyProperties(entity, response);
        response.setInstructor(convertUserToUserResponse(entity.getInstructor()));
        return response;
    }
     public UserResponse convertUserToUserResponse(User entity){
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    
}
