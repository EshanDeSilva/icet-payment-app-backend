package com.icet.paymentapp.dto.paginate;

import com.icet.paymentapp.dto.response.ResponsePaymentDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaginatedResponsePaymentDto {
    private long count;
    private List<ResponsePaymentDto> paymentList;
}
