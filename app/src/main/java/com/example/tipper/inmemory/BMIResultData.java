package com.example.tipper.inmemory;

import com.example.tipper.BMIActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//Autor Karol Kuchnio s21912
public class BMIResultData {
    private static BMIResultData instance;
    private List<BMIActivity.BMIResult> bmiResults = new ArrayList<>();

    private BMIResultData() {}

    public static BMIResultData getInstance() {
        if (instance == null) {
            instance = new BMIResultData();
        }
        return instance;
    }

    public List<BMIActivity.BMIResult> getBMIResults() {
        return bmiResults;
    }

    public void addBMIResult(double bmi) {
        BMIActivity.BMIResult result = new BMIActivity.BMIResult(bmi, new Date());
        bmiResults.add(result);
    }
}