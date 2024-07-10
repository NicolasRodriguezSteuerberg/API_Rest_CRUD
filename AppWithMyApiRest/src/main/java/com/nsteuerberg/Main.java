package com.nsteuerberg;

import com.nsteuerberg.controller.ApiUse;
import com.nsteuerberg.model.Users;

class Main {
    public static void main(String[] args) {
        ApiUse prueba = new ApiUse();
        prueba.getUsers();
        System.out.println("----------------------------------------------------");
        //prueba.postUsers(new Users("Javier", "Calle 3","javier@gmail.com"));
        //prueba.update(new Users(2, "Juan", "Calle 3","juan@gmail.com"));
        prueba.delete(4);
        System.out.println("----------------------------------------------------");
        prueba.getUsers();

    }
}