package RIWI.simulacro.infraestructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import RIWI.simulacro.api.dtos.reqest.UserRequest;
import RIWI.simulacro.api.dtos.response.CourseResponse;
import RIWI.simulacro.api.dtos.response.EnrollmentsResponse;
import RIWI.simulacro.api.dtos.response.EnrollmentsResponseInUser;
import RIWI.simulacro.api.dtos.response.InstructorResponse;
import RIWI.simulacro.api.dtos.response.MessageResponse;
import RIWI.simulacro.api.dtos.response.MessageResponseInUser;
import RIWI.simulacro.api.dtos.response.StudentResponse;
import RIWI.simulacro.api.dtos.response.SubmissionResponseInUser;
import RIWI.simulacro.api.dtos.response.UserResponse;
import RIWI.simulacro.domain.entities.Enrollment;
import RIWI.simulacro.domain.entities.Message;
import RIWI.simulacro.domain.entities.Submission;
import RIWI.simulacro.domain.entities.User;
import RIWI.simulacro.domain.repositories.UserRespository;
import RIWI.simulacro.infraestructure.abstractServices.IUserService;
import RIWI.simulacro.utils.enums.RoleUser;
import RIWI.simulacro.utils.exceptcions.IdNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private final UserRespository userRespository;

    @Override
    public Page<UserResponse> getAll(int page, int size) {
        PageRequest pagination = PageRequest.of(page, size);
        Page<UserResponse> response = userRespository.findAll(pagination).map(this::convertUserToUserResponse);
        return response;
    }

    @Override
    public UserResponse create(UserRequest request) {
        // TODO Auto-generated method stub
        User entityforsaving = convertUserRequestToEntity(request);
        UserResponse response = convertUserToUserResponse(userRespository.save(entityforsaving));
        return response;
    }

    @Override
    public UserResponse update(UserRequest request, Integer id) {
        // TODO Auto-generated method stub
        User entityforsaving = convertUserRequestToEntity(request);
        UserResponse response = convertUserToUserResponse(userRespository.save(entityforsaving));
        return response;
    }

    @Override
    public void delete(Integer id) {
        User userEntity = userRespository.findById(id).orElseThrow(()->new IdNotFoundException("User"));
        userRespository.deleteById(id);
    }

    @Override
    public UserResponse getById(Integer id) {
        User userEntity = userRespository.findById(id).orElseThrow(()->new IdNotFoundException("User"));
        if(userEntity.getRol() == RoleUser.INSTRUCTOR){
            InstructorResponse response = (InstructorResponse) convertUserToInstructorResponse(userEntity);
            return response;
        }else if(userEntity.getRol() == RoleUser.STUDENT){
            return convertUserToStudentResponse(userEntity);
        }
        return convertUserToUserResponse(userEntity);
    }


    //User To User Response

    public UserResponse convertUserToUserResponse(User entity){
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public UserResponse convertUserToStudentResponse(User entity){
       StudentResponse response = new StudentResponse();
        BeanUtils.copyProperties(entity, response);
        response.setRecived(entity.getRecived().stream().map(
            tempMessage -> convertMessageToMessageResponseInUser(tempMessage)
                ).toList());
        response.setSended(entity.getSended().stream().map(
                tempMessage ->  convertMessageToMessageResponseInUser(tempMessage)
            ).toList());
        response.setEnrollments(entity.getEnrollments().stream().map(
            tempEnroll-> convertEnrollmentsToEnrollmentsResponseInUser(tempEnroll)
            ).toList());
        response.setSubmisions(entity.getSubmisions().stream().map(
            this::convertSubmissionToSubmissionResponseInUser
        ).toList());
        return response;
    }

    public UserResponse convertUserToInstructorResponse(User entity){
        InstructorResponse response = new InstructorResponse();
        BeanUtils.copyProperties(entity, response);
        response.setInsTructingCourses(entity.getInsTructingCourses().stream().map(
                tempCourse -> {
                    CourseResponse curso = new CourseResponse();
                    BeanUtils.copyProperties(tempCourse, curso);
                    return curso;}
            ).toList());
        response.setRecived(entity.getRecived().stream().map(
            tempMessage -> convertMessageToMessageResponseInUser(tempMessage)
                ).toList());
        response.setSended(entity.getSended().stream().map(
                tempMessage ->  convertMessageToMessageResponseInUser(tempMessage)
            ).toList());
        return response;
    }

    public User convertUserRequestToEntity(UserRequest request){
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return user;
    }

    public MessageResponseInUser convertMessageToMessageResponseInUser(Message entity){
        MessageResponseInUser mensaje = new MessageResponseInUser();
        BeanUtils.copyProperties(entity, mensaje);
        mensaje.setReciver(entity.getReciver().getFullName());
        mensaje.setSender(entity.getSender().getFullName());
        mensaje.setCourse(entity.getCourse().getCoursName());
        return mensaje;
    }
  
    public EnrollmentsResponseInUser convertEnrollmentsToEnrollmentsResponseInUser(Enrollment entity){
        EnrollmentsResponseInUser response = new EnrollmentsResponseInUser();
        BeanUtils.copyProperties(entity, response);
        response.setCourse(entity.getCourse().getCoursName());
        return response;
    }

    public SubmissionResponseInUser convertSubmissionToSubmissionResponseInUser(Submission entity){
        SubmissionResponseInUser response = new SubmissionResponseInUser();
        BeanUtils.copyProperties(entity, response);
        response.setAssignment(entity.getAssignment().getAssignmentTitle());
        return response;
    }

}

