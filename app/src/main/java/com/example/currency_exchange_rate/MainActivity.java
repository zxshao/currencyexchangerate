package com.example.currency_exchange_rate;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.net.*;
import java.io.*;

public class MainActivity extends AppCompatActivity {

    // url for exchange rate with base EUR
    private final String eur = "https://api.exchangeratesapi.io/latest?base=EUR";
    // url for exchange rate with base USD
    private final String usd = "https://api.exchangeratesapi.io/latest?base=USD";

    public JsonObject getJson(String rate) {
        JsonObject rootobj = null;
        try {
            URL url = new URL(rate);
            URLConnection request = url.openConnection();
            request.connect();
            // Convert to a JSON object to print data
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            rootobj = root.getAsJsonObject();
            System.out.println(rootobj.getAsString());
            return rootobj;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rootobj;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button doExchange = findViewById(R.id.showExchange);
        doExchange.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                doExchange();
            }
        });


        EditText inputCurrency = (EditText) findViewById(R.id.InputCurrency);
        String input = inputCurrency.getText().toString();

        TextView outputCurrency = findViewById(R.id.OutputCurrency);
        String output = outputCurrency.getText().toString();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    void doExchange() {
        return;
    }
}
