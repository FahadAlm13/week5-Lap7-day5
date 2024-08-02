package spring.boot.week5day5lap7.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.week5day5lap7.ApiResponse.ApiResponse;
import spring.boot.week5day5lap7.Model.Classroom;
import spring.boot.week5day5lap7.Service.ClassroomService;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/classroom")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomService classroomService;

    @GetMapping("/get")
    public ResponseEntity<ArrayList<Classroom>> getClassrooms() {
        return ResponseEntity.ok(classroomService.getClassrooms());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addClassroom(@Valid @RequestBody Classroom classroom, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        classroomService.addClassroom(classroom);
        return ResponseEntity.ok(new ApiResponse("Classroom added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateClassroom(@Valid @RequestBody Classroom classroom, @PathVariable int id, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        if (classroomService.updateClassroom(classroom, id)) {
            return ResponseEntity.ok(new ApiResponse("Classroom updated successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Classroom update failed"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteClassroom(@PathVariable int id) {
        if (classroomService.deleteClassroom(id)) {
            return ResponseEntity.ok(new ApiResponse("Classroom deleted successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Classroom delete failed"));
        }
    }

    @GetMapping("/getClassroomByName/{name}")
    public ResponseEntity getClassroomByName(@PathVariable String name) {
        Classroom classroom = classroomService.getClassroomByName(name);
        if (classroom != null) {
            return ResponseEntity.ok(classroom);
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Classroom not found"));
        }
    }

    @GetMapping("/getAvailableClassrooms")
    public ResponseEntity<?> getAvailableClassrooms() {
        ArrayList<Classroom> classrooms = classroomService.getAvailableClassrooms();
        if (!classrooms.isEmpty()) {
            return ResponseEntity.ok(classrooms);
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("No available classrooms found"));
        }
    }

    @PutMapping("/updateAvailability/{id}")
    public ResponseEntity updateClassroomAvailability(@PathVariable int id, @RequestParam boolean isAvailable) {
        if (classroomService.updateClassroomAvailability(id, isAvailable)) {
            return ResponseEntity.ok(new ApiResponse("Classroom availability updated successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Classroom not found or update failed"));
        }
    }

    @GetMapping("/getClassroomsByCapacity/{capacity}")
    public ResponseEntity<?> getClassroomsByCapacity(@PathVariable int capacity) {
        ArrayList<Classroom> classrooms = classroomService.getClassroomsByCapacity(capacity);
        if (!classrooms.isEmpty()) {
            return ResponseEntity.ok(classrooms);
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("No classrooms found with the given capacity"));
        }
    }
}
