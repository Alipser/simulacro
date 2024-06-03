package RIWI.simulacro.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import RIWI.simulacro.api.dtos.reqest.SubmissionRequest;
import RIWI.simulacro.api.dtos.response.SubmissionResponse;
import RIWI.simulacro.infraestructure.abstractServices.ISubmissionService;
import lombok.AllArgsConstructor;
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

@RestController
@RequestMapping(path = "/submission")
@AllArgsConstructor
public class SubmissionController {

    @Autowired
    private final ISubmissionService submissionService;

    @PostMapping("/new")
    public ResponseEntity<SubmissionResponse> postMethodName(@RequestBody @Validated SubmissionRequest request) {
        SubmissionResponse response = submissionService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("getBy/{id}")
    public ResponseEntity<SubmissionResponse> getMethodById(@PathVariable int id) {
        SubmissionResponse response = submissionService.getById(id);
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteMethod(@PathVariable int id) {
        submissionService.delete(id);
        return ResponseEntity.ok("Lesson deleted");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<SubmissionResponse> putMethodName(@PathVariable int id,
            @RequestBody @Validated SubmissionRequest request) {
        SubmissionResponse response = submissionService.update(request, id);
        return ResponseEntity.ok(response);
    }

}
