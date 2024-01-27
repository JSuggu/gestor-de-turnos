package com.example.gestordeturnos.api;

import com.example.gestordeturnos.api.controller.TurnController;

public abstract class Api {
    public static TurnController getTurnController(){
        return new TurnController();
    }
}
