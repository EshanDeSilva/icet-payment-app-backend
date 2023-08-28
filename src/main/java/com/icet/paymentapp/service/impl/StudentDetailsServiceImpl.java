package com.icet.paymentapp.service.impl;

import com.icet.paymentapp.dto.paginate.PaginatedResponseStudentDetailsDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
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
        try{
            Student student = studentRepo.findById(studentDetailsKey.getStudent_id()).get();
            Course course = courseRepo.findById(studentDetailsKey.getCourse_id()).get();
            StudentDetails details = studentDetailsRepo.save(new StudentDetails(studentDetailsKey, student, course));
            return new ResponseStudentDetailsDto(
                    details.getStudent().getStudentId(),
                    details.getCourse().getCourseId()
            );
        }catch (NoSuchElementException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
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

    @Override
    public PaginatedResponseStudentDetailsDto findAllDetails(int page, int size) {
        Page<StudentDetails> all = studentDetailsRepo.findAll(PageRequest.of(page, size));
        long count = studentDetailsRepo.count();
        if (count<=0){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        List<ResponseStudentDetailsDto> list = new ArrayList<>();

        for (StudentDetails details:all) {
            list.add(new ResponseStudentDetailsDto(
                    details.getStudent().getStudentId(),
                    details.getCourse().getCourseId()
            ));
        }

        return new PaginatedResponseStudentDetailsDto(count,list);
    }
}
