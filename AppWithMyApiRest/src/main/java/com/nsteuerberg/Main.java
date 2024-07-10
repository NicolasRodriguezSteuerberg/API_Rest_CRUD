package com.nsteuerberg;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SingletonBeanConfig.class);
        ApiUse1 prueba = context.getBean(ApiUse1.class);
        ApiUse2 prueba2 = context.getBean(ApiUse2.class);
        prueba.getDato();
        prueba.changeDato();
        prueba2.getDato();
    }
}