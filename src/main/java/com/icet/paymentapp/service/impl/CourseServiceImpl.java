package com.icet.paymentapp.service.impl;

import com.icet.paymentapp.dto.paginate.PaginatedResponseCourseDto;
import com.icet.paymentapp.dto.request.RequestCourseDto;
import com.icet.paymentapp.dto.response.ResponseCourseDto;
import com.icet.paymentapp.entity.Course;
import com.icet.paymentapp.repo.CourseRepo;
import com.icet.paymentapp.service.CourseService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public void deleteCourse(String courseId) {
        try {
            courseRepo.deleteById(courseId);
        }catch (EmptyResultDataAccessException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseCourseDto updateCourse(String courseId, RequestCourseDto dto) {
        Optional<Course> optionalCourse = courseRepo.findById(courseId);

        if (optionalCourse.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        optionalCourse.get().setCourse(dto.getCourse());
        optionalCourse.get().setBatch(dto.getBatch());
        optionalCourse.get().setCourseFee(dto.getCourseFee());
        optionalCourse.get().setStartDate(dto.getStartDate());

        Course course = courseRepo.save(optionalCourse.get());

        return new ResponseCourseDto(
                course.getCourseId(),
                course.getCourse(),
                course.getBatch(),
                course.getStartDate(),
                course.getCourseFee()
        );

    }

    @Override
    public PaginatedResponseCourseDto searchCourse(String text, int page, int size) {
        Page<Course> courses = courseRepo.searchCourse(text, PageRequest.of(page, size));
        long count = courseRepo.count();

        if (count<=0){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        if (courses.stream().count()<=0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<ResponseCourseDto> list = new ArrayList<>();

        for (Course course:courses) {
            list.add(new ResponseCourseDto(
                    course.getCourseId(),
                    course.getCourse(),
                    course.getBatch(),
                    course.getStartDate(),
                    course.getCourseFee()
            ));
        }

        return new PaginatedResponseCourseDto(count,list);
    }

    @Override
    public PaginatedResponseCourseDto findAllCourses(int page, int size) {
        Page<Course> courses = courseRepo.findAll(PageRequest.of(page,size));
        long count = courseRepo.count();

        if (count<=0){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        List<ResponseCourseDto> list = new ArrayList<>();

        for (Course course:courses) {
            list.add(new ResponseCourseDto(
                    course.getCourseId(),
                    course.getCourse(),
                    course.getBatch(),
                    course.getStartDate(),
                    course.getCourseFee()
            ));
        }

        return new PaginatedResponseCourseDto(count,list);
    }
}
