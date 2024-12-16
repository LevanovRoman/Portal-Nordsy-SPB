package com.myapp.portalnordsyspb.Temporary;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Student updateStudentCourses(Long studentId, Set<Long> courseIds) {
        // Найти студента
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        // Загрузить курсы из базы данных
        Set<Course> courses = new HashSet<>(courseRepository.findAllById(courseIds));

        // Обновить ассоциации
        student.getCourses().clear();
        for (Course course : courses) {
            student.addCourse(course);
        }

        // Сохранить изменения
        return studentRepository.save(student);
    }
}
