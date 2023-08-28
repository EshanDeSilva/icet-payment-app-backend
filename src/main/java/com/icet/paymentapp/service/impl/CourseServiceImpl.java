package com.icet.paymentapp.service.impl;

import com.icet.paymentapp.dto.request.RequestCourseDto;
import com.icet.paymentapp.dto.response.ResponseCourseDto;
import com.icet.paymentapp.entity.Course;
import com.icet.paymentapp.repo.CourseRepo;
import com.icet.paymentapp.service.CourseService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepo courseRepo;

    public CourseServiceImpl(CourseRepo courseRepo){
        this.courseRepo = courseRepo;
    }

    @Override
    public ResponseCourseDto saveCourse(RequestCourseDto dto) {
        try{
            Course course = courseRepo.save(new Course(
                    dto.getCourse() + dto.getBatch(),
                    dto.getCourse(),
                    dto.getBatch(),
                    dto.getStartDate(),
                    dto.getCourseFee()
            ));

            return new ResponseCourseDto(
                    course.getCourseId(),
                    course.getCourse(),
                    course.getBatch(),
                    course.getStartDate(),
                    course.getCourseFee()
            );
        }catch (DataIntegrityViolationException ex){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseCourseDto findCourse(String courseId) {
        Optional<Course> course = courseRepo.findById(courseId);
        if (course.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseCourseDto(
                course.get().getCourseId(),
                course.get().getCourse(),
                course.get().getBatch(),
                course.get().getStartDate(),
                course.get().getCourseFee()
        );
    }
}
