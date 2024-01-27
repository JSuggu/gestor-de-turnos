package com.example.gestordeturnos.api.implement;

import com.example.gestordeturnos.api.service.TurnService;

public abstract class Implements {
    public static TurnService getTurnService(){
        return new TurnService();
    }
}
