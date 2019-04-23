package com.example.currency_exchange_rate;

import com.google.gson.JsonObject;

public class exchange {
    public double calculate(JsonObject json, String outCurrency, double inputNumber) {
        try {
            double rate = json.getAsJsonObject("rates").getAsJsonObject(outCurrency).getAsDouble();
            return rate * inputNumber;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
