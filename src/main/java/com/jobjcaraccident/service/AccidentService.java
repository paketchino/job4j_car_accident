package com.jobjcaraccident.service;

import com.jobjcaraccident.persistance.AccidentDB;

public class AccidentService {

    private final AccidentDB accidentDB;

    public AccidentService(AccidentDB accidentDB) {
        this.accidentDB = accidentDB;
    }
}
