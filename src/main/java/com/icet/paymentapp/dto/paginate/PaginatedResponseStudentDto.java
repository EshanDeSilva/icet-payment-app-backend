package com.icet.paymentapp.dto.paginate;

import com.icet.paymentapp.dto.response.ResponseStudentDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaginatedResponseStudentDto {
    private long count;
    private List<ResponseStudentDto> studentList;
}
