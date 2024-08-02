package spring.boot.week5day5lap7.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.week5day5lap7.ApiResponse.ApiResponse;
import spring.boot.week5day5lap7.Model.Instructor;
import spring.boot.week5day5lap7.Service.InstructorService;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/instructor")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/get")
    public ResponseEntity<ArrayList<Instructor>> getInstructors() {
        return ResponseEntity.ok(instructorService.getInstructors());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addInstructor(@Valid @RequestBody Instructor instructor, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        instructorService.addInstructor(instructor);
        return ResponseEntity.ok(new ApiResponse("Instructor added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateInstructor(@Valid @RequestBody Instructor instructor, @PathVariable int id, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        if (instructorService.updateInstructor(instructor, id)) {
            return ResponseEntity.ok(new ApiResponse("Instructor updated successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Instructor update failed"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteInstructor(@PathVariable int id) {
        if (instructorService.deleteInstructor(id)) {
            return ResponseEntity.ok(new ApiResponse("Instructor deleted successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Instructor delete failed"));
        }
    }

    @PutMapping("/updateEmail")
    public ResponseEntity updateInstructorEmail(@RequestParam String currentEmail, @RequestParam String newEmail) {
        if (instructorService.updateInstructorEmail(currentEmail, newEmail)) {
            return ResponseEntity.ok(new ApiResponse("Instructor email updated successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Instructor not found or email update failed"));
        }
    }

    @GetMapping("/getInstructorsByExperience/{yearsOfExperience}")
    public ResponseEntity<?> getInstructorsByExperience(@PathVariable int yearsOfExperience) {
        ArrayList<Instructor> instructors = instructorService.getInstructorsByExperience(yearsOfExperience);
        if (!instructors.isEmpty()) {
            return ResponseEntity.ok(instructors);
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("No instructors found with the given years of experience"));
        }
    }

    @GetMapping("/getAllInstructorsSortedByExperience")
    public ResponseEntity getAllInstructorsSortedByExperience() {
        ArrayList<Instructor> instructors = instructorService.getAllInstructorsSortedByExperience();
        if (!instructors.isEmpty()) {
            return ResponseEntity.ok(instructors);
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("No instructors found"));
        }
    }

    @PostMapping("/updatePhoneNumber")
    public ResponseEntity updateInstructorPhoneNumber(@RequestParam String email, @RequestParam String newPhoneNumber) {
        if (instructorService.updateInstructorPhoneNumber(email, newPhoneNumber)) {
            return ResponseEntity.ok(new ApiResponse("Instructor phone number updated successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Instructor not found or phone number update failed"));
        }
    }
}
