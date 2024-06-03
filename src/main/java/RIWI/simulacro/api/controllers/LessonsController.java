package RIWI.simulacro.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import RIWI.simulacro.api.dtos.reqest.LessonRequest;
import RIWI.simulacro.api.dtos.response.LessonResponse;
import RIWI.simulacro.infraestructure.abstractServices.ILessonService;
import lombok.AllArgsConstructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping(path = "/lessons")
@AllArgsConstructor
public class LessonsController {

    @Autowired
    private final ILessonService lessonService;

     @GetMapping
    public ResponseEntity<Page<LessonResponse>> getAllUsers(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(this.lessonService.getAll(page - 1, size));
    }

    @PostMapping("/new")
    public ResponseEntity<LessonResponse> postMethodName(@RequestBody @Validated LessonRequest request) {
        LessonResponse response = lessonService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("getBy/{id}")
    public ResponseEntity<LessonResponse> getMethodById(@PathVariable int id) {
        LessonResponse response = lessonService.getById(id);
        return ResponseEntity.ok(response);
        
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteMethod(@PathVariable int id){
        lessonService.delete(id);
        return ResponseEntity.ok("Lesson deleted");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<LessonResponse> putMethodName(@PathVariable int id, @RequestBody @Validated LessonRequest request) {
        LessonResponse response = lessonService.update(request, id);
        return ResponseEntity.ok(response);
    }


}
