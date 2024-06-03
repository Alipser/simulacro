package RIWI.simulacro.infraestructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import RIWI.simulacro.api.dtos.reqest.CourseRequest;
import RIWI.simulacro.api.dtos.reqest.LessonRequest;
import RIWI.simulacro.api.dtos.response.AssignmentResponse;
import RIWI.simulacro.api.dtos.response.CourseResponse;
import RIWI.simulacro.api.dtos.response.LessonResponse;
import RIWI.simulacro.api.dtos.response.LessonResponseFull;
import RIWI.simulacro.api.dtos.response.UserResponse;
import RIWI.simulacro.domain.entities.Assignment;
import RIWI.simulacro.domain.entities.Course;
import RIWI.simulacro.domain.entities.Lesson;
import RIWI.simulacro.domain.entities.User;
import RIWI.simulacro.domain.repositories.CourseRespository;
import RIWI.simulacro.domain.repositories.LessonRespository;
import RIWI.simulacro.infraestructure.abstractServices.ILessonService;
import RIWI.simulacro.utils.exceptcions.IdNotFoundException;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class LessonService implements ILessonService {

    @Autowired
    private final LessonRespository lessonRespository;

    @Autowired
    private final CourseRespository courseRespository;

    @Override
    public Page<LessonResponse> getAll(int page, int size) {
        PageRequest pagination = PageRequest.of(page, size);
        Page<LessonResponse> response = lessonRespository.findAll(pagination).map(this::convertLessonToLessonResponse);
        return response;
    }

    @Override
    public LessonResponse create(LessonRequest request) {
        Lesson entityforsaving = convertLessonRequestToEntity(request);
        LessonResponse response = convertLessonToLessonResponse(lessonRespository.save(entityforsaving));
        return response;
    }

    @Override
    public LessonResponse update(LessonRequest request, Integer id) {
        Lesson existingLesson = lessonRespository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Lesson"));
        
        Course course = courseRespository.findById(request.getCourseId()).orElseThrow(()->new IdNotFoundException("Course"));

        existingLesson.setCourse(course);
        existingLesson.setContent(request.getContent());
        existingLesson.setLessonTitle(request.getLessonTitle());

        LessonResponse response = convertLessonToLessonResponse(lessonRespository.save(existingLesson));
        
        return response;
    }

    @Override
    public void delete(Integer id) {
        lessonRespository.findById(id).orElseThrow(()->new IdNotFoundException("Lesson"));
        lessonRespository.deleteById(id);
    }

    @Override
    public LessonResponse getById(Integer id) {
        LessonResponse response = convertLessonToLessonResponse(lessonRespository.findById(id).orElseThrow(()->new IdNotFoundException("Lesson")));
        return response;
    }

    
    @Override
    public LessonResponseFull getByIdAssignments(Integer id) {
        LessonResponseFull response = convertLessonToLessonResponseFull(lessonRespository.findById(id).orElseThrow(()->new IdNotFoundException("Lesson")));
        return response;
    }



    



    public LessonResponse convertLessonToLessonResponse(Lesson entity){
        LessonResponse response = new LessonResponse();
        BeanUtils.copyProperties(entity, response);
        response.setCourse(convertCourseToCourseResponse(entity.getCourse()));
        
        return response;
    }

    public LessonResponseFull convertLessonToLessonResponseFull(Lesson entity){
        LessonResponseFull response = new LessonResponseFull();
        BeanUtils.copyProperties(entity, response);
        response.setCourse(convertCourseToCourseResponse(entity.getCourse()));
        response.setAssignments(entity.getAssignments().stream().map(this::convertAssignmentToAssignmentResponse).toList());
        return response;
    }

    public AssignmentResponse convertAssignmentToAssignmentResponse(Assignment entity){
        AssignmentResponse response = new AssignmentResponse();
        BeanUtils.copyProperties(entity, response);
        response.setLesson(null);
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


    public Lesson convertLessonRequestToEntity(LessonRequest request){  
        Course course = courseRespository.findById(request.getCourseId()).orElseThrow(()->new IdNotFoundException("Course"));
        Lesson lesson = new Lesson();
        BeanUtils.copyProperties(request,lesson);
        lesson.setCourse(course);
        return lesson;
    }

    

}
