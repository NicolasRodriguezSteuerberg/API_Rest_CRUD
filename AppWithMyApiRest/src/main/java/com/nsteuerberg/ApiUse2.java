package com.nsteuerberg;

import com.nsteuerberg.controller.ApiUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiUse2 {
    @Autowired
    private ApiUse apiUse;

    public void getDato(){
        System.out.println(apiUse.getDato());
    }

    public void changeDato(){
        apiUse.setDato("Hola buenas tardes");
    }
}
