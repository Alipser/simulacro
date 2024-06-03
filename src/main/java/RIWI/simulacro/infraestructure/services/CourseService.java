package RIWI.simulacro.infraestructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import RIWI.simulacro.api.dtos.reqest.CourseRequest;

import RIWI.simulacro.api.dtos.response.CourseResponse;
import RIWI.simulacro.api.dtos.response.CourseResponseFull;
import RIWI.simulacro.api.dtos.response.LessonResponse;
import RIWI.simulacro.api.dtos.response.UserResponse;
import RIWI.simulacro.domain.entities.Course;
import RIWI.simulacro.domain.entities.Lesson;
import RIWI.simulacro.domain.entities.User;
import RIWI.simulacro.domain.repositories.CourseRespository;
import RIWI.simulacro.infraestructure.abstractServices.ICourseService;
import RIWI.simulacro.utils.exceptcions.IdNotFoundException;
import RIWI.simulacro.utils.exceptcions.IdNotMatchedException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@Service
@Transactional
@AllArgsConstructor
public class CourseService implements ICourseService {

    @Autowired
    private final CourseRespository courseRespository;

    @Override
    public Page<CourseResponse> getAll(int page, int size) {
        PageRequest pagination = PageRequest.of(page, size);
        Page<CourseResponse> response = courseRespository.findAll(pagination).map(this::convertCourseToCourseResponse);
        return response;
    }

    @Override
    public CourseResponse create(CourseRequest request) {
        Course entityforsaving = convertCourseRequestToEntity(request);
        CourseResponse response = convertCourseToCourseResponse(courseRespository.save(entityforsaving));
        return response;
    }

    @Override
    public CourseResponse update(CourseRequest request, Integer id) {
        courseRespository.findById(id).orElseThrow(()->new IdNotFoundException("Course"));
        if (request.getId() != id ){
            throw new IdNotMatchedException("Course");
        }
        Course entityforsaving = convertCourseRequestToEntity(request);
        CourseResponse response = convertCourseToCourseResponse(courseRespository.save(entityforsaving));
        return response;
    }

    @Override
    public void delete(Integer id) {
        courseRespository.findById(id).orElseThrow(()->new IdNotFoundException("Course"));
        courseRespository.deleteById(id);
    }

    @Override
    public CourseResponse getById(Integer id) {
        Course response = courseRespository.findById(id).orElseThrow(()->new IdNotFoundException("Course"));
        return convertCourseToCourseResponse(response);
    }

    @Override
    public CourseResponseFull getByIdLessons(Integer id) {
        Course response = courseRespository.findById(id).orElseThrow(()->new IdNotFoundException("Course"));
        return convertCourseToCourseResponseFull(response);
    }


    public CourseResponse convertCourseToCourseResponse(Course entity){
        CourseResponse response = new CourseResponse();
        BeanUtils.copyProperties(entity, response);
        response.setInstructor(convertUserToUserResponse(entity.getInstructor()));
        return response;
    }

    public CourseResponseFull convertCourseToCourseResponseFull(Course entity){
        CourseResponseFull response = new CourseResponseFull();
        BeanUtils.copyProperties(entity, response);
        response.setInstructor(convertUserToUserResponse(entity.getInstructor()));
        response.setLessons(entity.getLessons().stream().map(lesson -> convertLessonToLessonResponse(lesson)).toList());
        return response;
    }
    public LessonResponse convertLessonToLessonResponse(Lesson entity){
        LessonResponse response = new LessonResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

   
    public UserResponse convertUserToUserResponse(User entity){
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public Course convertCourseRequestToEntity(CourseRequest request){
        Course course = new Course();
        User instructor = new User();
        instructor.setId(request.getInstructorId());
        BeanUtils.copyProperties(request,course);
        course.setInstructor(instructor);
        return course;
    }
    

  

}

