package com.icet.paymentapp.service.impl;

import com.icet.paymentapp.dto.response.ResponseStudentDetailsDto;
import com.icet.paymentapp.entity.Course;
import com.icet.paymentapp.entity.Student;
import com.icet.paymentapp.entity.StudentDetails;
import com.icet.paymentapp.entity.StudentDetailsKey;
import com.icet.paymentapp.repo.CourseRepo;
import com.icet.paymentapp.repo.StudentDetailsRepo;
import com.icet.paymentapp.repo.StudentRepo;
import com.icet.paymentapp.service.StudentDetailsService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@Service
public class StudentDetailsServiceImpl implements StudentDetailsService {
    private final StudentDetailsRepo studentDetailsRepo;
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;

    public StudentDetailsServiceImpl(StudentDetailsRepo studentDetailsRepo,StudentRepo studentRepo, CourseRepo courseRepo){
        this.studentDetailsRepo = studentDetailsRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public ResponseStudentDetailsDto registerStudentToCourse(StudentDetailsKey studentDetailsKey) {
        Student student = studentRepo.findById(studentDetailsKey.getStudent_id()).get();
        Course course = courseRepo.findById(studentDetailsKey.getStudent_id()).get();
        StudentDetails details = studentDetailsRepo.save(new StudentDetails(studentDetailsKey, student, course));
        return new ResponseStudentDetailsDto(
                details.getStudent().getStudentId(),
                details.getCourse().getCourseId()
        );
    }

    @Override
    public void deleteStudentDetail(String studentId, String courseId) {
        try {
            Student student = studentRepo.findById(studentId).get();
            Course course = courseRepo.findById(courseId).get();

            studentDetailsRepo.delete(new StudentDetails(
                    new StudentDetailsKey(studentId, courseId),
                    student,
                    course
            ));
        }catch (EmptyResultDataAccessException | NoSuchElementException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
