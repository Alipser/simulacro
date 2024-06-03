package RIWI.simulacro.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import RIWI.simulacro.api.dtos.reqest.CourseRequest;
import RIWI.simulacro.api.dtos.response.CourseResponse;
import RIWI.simulacro.infraestructure.abstractServices.ICourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping(path = "/courses")
@AllArgsConstructor
public class CourseController {

    @Autowired
    private final ICourseService courseService;

     @GetMapping
    public ResponseEntity<Page<CourseResponse>> getAllUsers(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(this.courseService.getAll(page - 1, size));
    }

    @PostMapping("/new")
    public ResponseEntity<CourseResponse> postMethodName(@RequestBody @Validated CourseRequest request) {
        CourseResponse response = courseService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("getBy/{id}")
    public ResponseEntity<CourseResponse> getMethodById(@PathVariable int id) {
        CourseResponse response = courseService.getById(id);
        return ResponseEntity.ok(response);
        
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteMethod(@PathVariable int id){
        courseService.delete(id);
        return ResponseEntity.ok("Course deleted");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<CourseResponse> putMethodName(@PathVariable int id, @RequestBody @Validated CourseRequest request) {
        CourseResponse response = courseService.update(request, id);
        return ResponseEntity.ok(response);
    }
    
    
    
}
