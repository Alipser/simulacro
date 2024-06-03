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
import RIWI.simulacro.api.dtos.reqest.EnrollmentsRequest;
import RIWI.simulacro.api.dtos.response.EnrollmentsResponse;
import RIWI.simulacro.infraestructure.abstractServices.IEnrollmentService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/enrollments")
@AllArgsConstructor
public class EnrollmentController {

    @Autowired
    private final IEnrollmentService enrollmentService;

   

    @PostMapping("/new")
    public ResponseEntity<EnrollmentsResponse> postMethodName(@RequestBody @Validated EnrollmentsRequest request) {
        EnrollmentsResponse response = enrollmentService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("getBy/{id}")
    public ResponseEntity<EnrollmentsResponse> getMethodById(@PathVariable int id) {
        EnrollmentsResponse response = enrollmentService.getById(id);
        return ResponseEntity.ok(response);
        
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteMethod(@PathVariable int id){
        enrollmentService.delete(id);
        return ResponseEntity.ok("Enrollment deleted");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<EnrollmentsResponse> putMethodName(@PathVariable int id, @RequestBody @Validated EnrollmentsRequest request) {
        EnrollmentsResponse response = enrollmentService.update(request, id);
        return ResponseEntity.ok(response);
    }

}
