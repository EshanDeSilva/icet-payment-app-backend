package com.icet.paymentapp.service.impl;

import com.icet.paymentapp.dto.response.ResponseStudentDetailsDto;
import com.icet.paymentapp.entity.Course;
import com.icet.paymentapp.entity.Student;
import com.icet.paymentapp.entity.StudentDetails;
import com.icet.paymentapp.entity.StudentDetailsKey;
import com.icet.paymentapp.repo.StudentDetailsRepo;
import com.icet.paymentapp.service.StudentDetailsService;
import org.springframework.stereotype.Service;

@Service
public class StudentDetailsServiceImpl implements StudentDetailsService {
    private final StudentDetailsRepo studentDetailsRepo;

    public StudentDetailsServiceImpl(StudentDetailsRepo studentDetailsRepo){
        this.studentDetailsRepo = studentDetailsRepo;
    }

    @Override
    public ResponseStudentDetailsDto registerStudentToCourse(StudentDetailsKey studentDetailsKey, Student student, Course course) {
        StudentDetails details = studentDetailsRepo.save(new StudentDetails(studentDetailsKey, student, course));
        return new ResponseStudentDetailsDto(
                details.getStudent().getStudentId(),
                details.getCourse().getCourseId()
        );
    }
}
