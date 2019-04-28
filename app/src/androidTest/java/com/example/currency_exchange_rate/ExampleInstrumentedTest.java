package com.example.currency_exchange_rate;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.currency_exchange_rate", appContext.getPackageName());
    }
    public void testUrl() {
        assertNotNull(MainActivity.getJson("https://api.exchangeratesapi.io/latest?base=USD"));
        String u = "{\"base\":\"USD\",\"rates\":{\"BGN\":1.7567591844,\"NZD\":1.5035480104,\"ILS\":3.633971077," +
                "\"RUB\":64.7741848558,\"CAD\":1.3491421899,\"USD\":1.0,\"PHP\":52.1638372406,\"CHF\":1.0214677086," +
                "\"AUD\":1.421359921,\"JPY\":111.7847839756,\"TRY\":5.950058385,\"HKD\":7.8448755951,\"MYR\":4.1389562562," +
                "\"HRK\":6.661726399,\"CZK\":23.0890146412,\"IDR\":14196.0028743376,\"DKK\":6.7056498698,\"NOK\":8.6816671158," +
                "\"HUF\":289.329021827,\"GBP\":0.7755322016,\"MXN\":19.0613491422,\"THB\":31.9895805264,\"ISK\":122.1593460882," +
                "\"ZAR\":14.4148926615,\"BRL\":3.9642504267,\"SGD\":1.3630647624,\"PLN\":3.8514326776,\"INR\":70.016617264," +
                "\"KRW\":1160.9269738615,\"RON\":4.2753076439,\"CNY\":6.7359202371,\"SEK\":9.4977095123,\"EUR\":0.8982304859}," +
                "\"date\":\"2019-04-26\"}";
        assertEquals(u, MainActivity.getJson("https://api.exchangeratesapi.io/latest?base=USD").toString());
    }
}
