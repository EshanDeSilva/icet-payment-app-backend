package com.icet.paymentapp.util;

import com.icet.paymentapp.repo.StudentRepo;
import org.springframework.stereotype.Component;

@Component
public class IdManager {
    public String generate(String prefix, String lastId){
        //prefix = ICM105,ICP100
        int num = Integer.parseInt(lastId.split("[-]")[1]); //ICM105-000001
        num++;
        return prefix+"-"+String.format("%06d",num);
    }
}
