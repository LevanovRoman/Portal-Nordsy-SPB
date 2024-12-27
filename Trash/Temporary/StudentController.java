package com.myapp.portalnordsyspb.Temporary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PutMapping("/{id}/courses")
    public ResponseEntity<Student> updateStudentCourses(
            @PathVariable Long id, @RequestBody Set<Long> courseIds) {
        Student updatedStudent = studentService.updateStudentCourses(id, courseIds);
        return ResponseEntity.ok(updatedStudent);
    }
}

