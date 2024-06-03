package RIWI.simulacro.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import RIWI.simulacro.api.dtos.reqest.AssignmentRequest;
import RIWI.simulacro.api.dtos.response.AssginmenteResponseFull;
import RIWI.simulacro.api.dtos.response.AssignmentResponse;
import RIWI.simulacro.infraestructure.abstractServices.IAssignmentService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/assignments")
@AllArgsConstructor
public class AssignmentController {

    @Autowired
    private final IAssignmentService assignmentService;


    @PostMapping("/new")
    public ResponseEntity<AssignmentResponse> postMethodName(@RequestBody @Validated AssignmentRequest request) {
        AssignmentResponse response = assignmentService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("getBy/{id}")
    public ResponseEntity<AssignmentResponse> getMethodById(@PathVariable int id) {
        AssignmentResponse response = assignmentService.getById(id);
        return ResponseEntity.ok(response);
        
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteMethod(@PathVariable int id){
        assignmentService.delete(id);
        return ResponseEntity.ok("Assignment deleted");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<AssignmentResponse> putMethodName(@PathVariable int id, @RequestBody @Validated AssignmentRequest request) {
        AssignmentResponse response = assignmentService.update(request, id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("getBy/{id}/submissions")
    public ResponseEntity<AssginmenteResponseFull> getMethodByIdSubmission(@PathVariable int id) {
        AssginmenteResponseFull response = assignmentService.getByIdSubmission(id);
        return ResponseEntity.ok(response);
        
    }


}
