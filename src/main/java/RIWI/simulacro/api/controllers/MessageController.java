package RIWI.simulacro.api.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import RIWI.simulacro.api.dtos.reqest.AssignmentRequest;
import RIWI.simulacro.api.dtos.reqest.MessageRequest;
import RIWI.simulacro.api.dtos.response.AssignmentResponse;
import RIWI.simulacro.api.dtos.response.MessageResponse;
import RIWI.simulacro.infraestructure.abstractServices.IAssignmentService;
import RIWI.simulacro.infraestructure.abstractServices.IMessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "/messages")
@AllArgsConstructor
public class MessageController {

    @Autowired
    private final IMessageService messageService;

    @PostMapping("/new")
    public ResponseEntity<MessageResponse> postMethodName(@RequestBody @Validated MessageRequest request) {
       MessageResponse response  = messageService.create(request);
       return ResponseEntity.ok(response) ;
    }


    @GetMapping
    public ResponseEntity<List<MessageResponse>> getMethodName(        
        @RequestParam(defaultValue = "0") int reciver_id,
        @RequestParam(defaultValue = "0") int sender_id
    ) {
        return ResponseEntity.ok(messageService.getBetweenUsers(sender_id, reciver_id));
    }
    


}
