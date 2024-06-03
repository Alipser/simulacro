package RIWI.simulacro.infraestructure.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import RIWI.simulacro.api.dtos.reqest.MessageRequest;
import RIWI.simulacro.api.dtos.response.CourseResponse;
import RIWI.simulacro.api.dtos.response.MessageResponse;
import RIWI.simulacro.api.dtos.response.UserResponse;
import RIWI.simulacro.domain.entities.Course;
import RIWI.simulacro.domain.entities.Message;
import RIWI.simulacro.domain.entities.User;
import RIWI.simulacro.domain.repositories.CourseRespository;
import RIWI.simulacro.domain.repositories.MessageRespository;
import RIWI.simulacro.domain.repositories.UserRespository;
import RIWI.simulacro.infraestructure.abstractServices.IMessageService;
import RIWI.simulacro.utils.exceptcions.IdNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class MessageService implements IMessageService {

    @Autowired
    private final UserRespository userRespository;

    @Autowired
    private final MessageRespository messageRespository;

    @Autowired
    private final CourseRespository courseRespository;

    @Override
    public Page<MessageResponse> getAll(int page, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public MessageResponse create(MessageRequest request) {
        Message saved = messageRespository.save(convertMessageRequestToEntity(request));
        return convertMessageToMessageResponse(saved);
    }

    @Override
    public MessageResponse update(MessageRequest request, Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public MessageResponse getById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public List<MessageResponse> getBetweenUsers(int sender_id, int revicer_id) {
        User reciver =  userRespository.findById(revicer_id).orElseThrow(()->new IdNotFoundException("User Reciver"));
        User sender =  userRespository.findById(sender_id).orElseThrow(()->new IdNotFoundException("User Sender"));
        
        List<Message> mensajes = messageRespository.findBySenderAndReciver(sender, reciver);
        return mensajes.stream().map(this::convertMessageToMessageResponse).toList();
    }
    
    public Message convertMessageRequestToEntity(MessageRequest request){
        Message message = new Message();
        User reciver =  userRespository.findById(request.getReciverId()).orElseThrow(()->new IdNotFoundException("User Reciver"));
        User sender =  userRespository.findById(request.getSenderId()).orElseThrow(()->new IdNotFoundException("User Sender"));
        Course course = courseRespository.findById(request.getCourseId()).orElseThrow(()->new IdNotFoundException("Course"));
        BeanUtils.copyProperties(request, message);
        message.setReciver(reciver);
        message.setSender(sender);
        message.setCourse(course);
        message.setMessageContent(request.getMessageContent());
        message.setSendDate(LocalDate.now());
        return message;
    }

    public MessageResponse convertMessageToMessageResponse(Message entity){
        MessageResponse response = new MessageResponse();
        BeanUtils.copyProperties(entity, response);
        response.setCourse(convertCourseToCourseResponse(entity.getCourse()));
        response.setReciver(convertUserToUserResponse(entity.getReciver()));
        response.setSender(convertUserToUserResponse(entity.getSender()));
        return response;
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
