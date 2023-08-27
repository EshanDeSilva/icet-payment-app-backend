package com.icet.paymentapp.util;

import org.springframework.stereotype.Component;

@Component
public class IdManager {
    public String generateStudentId(String prefix, String lastId){
        //prefix = ICM105,ICP100
        int num = Integer.parseInt(lastId.split("[-]")[1]); //ICM105-000001
        num++;
        return prefix+"-"+String.format("%06d",num);
    }

    public String generatePaymentId(String studentId, String lastId) {
        //studentId = ICM105-000001
        String prefix = studentId.split("[-]")[0]; //ICM105
        int num = Integer.parseInt(lastId.split("[-]")[1]);
        num++;
        return prefix+"RCPT-"+String.format("%08d",num);
    }
}
