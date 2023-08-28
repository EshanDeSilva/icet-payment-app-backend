package com.icet.paymentapp.dto.paginate;

import com.icet.paymentapp.dto.response.ResponseStudentDetailsDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PaginatedResponseStudentDetailsDto {
    private long count;
    private List<ResponseStudentDetailsDto> studentDetailsList;
}
