package com.icet.paymentapp.dto.paginate;

import com.icet.paymentapp.dto.response.ResponseCourseDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaginatedResponseCourseDto {
    private long count;
    private List<ResponseCourseDto> courseList;
}
