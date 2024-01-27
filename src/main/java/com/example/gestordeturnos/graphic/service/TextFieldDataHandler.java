package com.example.gestordeturnos.graphic.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class TextFieldDataHandler {

    public static Boolean isDni(String fieldData){
        String regexpForDni = "[0-9]{8}";
        Pattern pattern = Pattern.compile(regexpForDni);
        Matcher matcher = pattern.matcher(fieldData);
        return matcher.matches();
    }

    public static Boolean isName(String fieldData){
        String regexpForName = "[a-zA-Zñ ]+";
        Pattern pattern = Pattern.compile(regexpForName);
        Matcher matcher = pattern.matcher(fieldData);
        return matcher.matches();
    }
}
