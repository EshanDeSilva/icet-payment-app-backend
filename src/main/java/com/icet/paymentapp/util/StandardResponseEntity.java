package com.icet.paymentapp.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponseEntity {
    private int statusCode;
    private String message;
    private Object data;
}
