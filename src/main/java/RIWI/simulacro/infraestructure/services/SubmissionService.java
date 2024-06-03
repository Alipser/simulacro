package RIWI.simulacro.infraestructure.services;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import RIWI.simulacro.api.dtos.reqest.SubmissionRequest;
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
import RIWI.simulacro.domain.repositories.SubmissionRespository;
import RIWI.simulacro.domain.repositories.UserRespository;
import RIWI.simulacro.infraestructure.abstractServices.ISubmissionService;
import RIWI.simulacro.utils.exceptcions.IdNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@Service
@Transactional
@AllArgsConstructor
public class SubmissionService implements ISubmissionService {

    @Autowired 
    private final SubmissionRespository submissionRespository;

    @Autowired
    private final AssignmentRespository assignmentRespository;

    @Autowired
    private final UserRespository userRespository;

    @Override
    public Page<SubmissionResponse> getAll(int page, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public SubmissionResponse create(SubmissionRequest request) {
        Submission submission =  convertSubmissionRequestToEntity(request);
        submission.setSubmissionDate(LocalDate.now());
        SubmissionResponse response = convertSubmiToSubmissionResponse( submissionRespository.save(submission));
       return response;
    }

    @Override
    public SubmissionResponse update(SubmissionRequest request, Integer id) {
        Submission response = submissionRespository.findById(id).orElseThrow(()-> new IdNotFoundException("Submission"));
        Assignment assignment = assignmentRespository.findById(request.getAssignmentId()).orElseThrow(()->new IdNotFoundException("Assignment"));
        User user =  userRespository.findById(request.getUserId()).orElseThrow(()->new IdNotFoundException("User"));
        response.setUser(user);
        response.setAssignment(assignment);
        response.setGrade(request.getGrade());
        response.setContent(request.getContent());
        response.setSubmissionDate(LocalDate.now());
        return convertSubmiToSubmissionResponse(submissionRespository.save(response));
        
    }

    @Override
    public void delete(Integer id) {
        submissionRespository.findById(id).orElseThrow(()-> new IdNotFoundException("Submission"));
        submissionRespository.deleteById(id);
    }

    @Override
    public SubmissionResponse getById(Integer id) {
        Submission response = submissionRespository.findById(id).orElseThrow(()-> new IdNotFoundException("Submission"));
        return convertSubmiToSubmissionResponse(response);
    }


    public SubmissionResponse convertSubmiToSubmissionResponse(Submission entity){
        SubmissionResponse response = new SubmissionResponse();
        BeanUtils.copyProperties(entity, response);
        response.setUser(convertUserToUserResponse(entity.getUser()));
        response.setAssignment(convertAssignmentToAssignmentResponse(entity.getAssignment()));
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

    public AssignmentResponse convertAssignmentToAssignmentResponse(Assignment entity){
        AssignmentResponse response = new AssignmentResponse();
        BeanUtils.copyProperties(entity, response);
        response.setLesson(convertLessonToLessonResponse(entity.getLesson()));
        return response;
    }

    public LessonResponse convertLessonToLessonResponse(Lesson entity){
        LessonResponse response = new LessonResponse();
        BeanUtils.copyProperties(entity, response);
        response.setCourse(convertCourseToCourseResponse(entity.getCourse()));        
        return response;
    }

    
    public Submission convertSubmissionRequestToEntity(SubmissionRequest request){  
        Assignment assignment = assignmentRespository.findById(request.getAssignmentId()).orElseThrow(()->new IdNotFoundException("Assignment"));
        User user =  userRespository.findById(request.getUserId()).orElseThrow(()->new IdNotFoundException("User"));
        Submission submission = new Submission();
        BeanUtils.copyProperties(request,submission);
        submission.setAssignment(assignment);
        submission.setUser(user);
        return submission;
    }
    
}
