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

import android.os.StrictMode;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.*;
import java.io.*;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // url for exchange rate with base something.
    private final String url = "https://api.exchangeratesapi.io/latest?base=";

    public static String getJson(String rate) {
        try {
            URL url = new URL(rate);
            URLConnection request = url.openConnection();
            request.connect();
            // Convert to a JSON object to print data
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            String rootobj = root.getAsJsonObject().toString();
            return rootobj;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText inputCurrency = (EditText) findViewById(R.id.InputCurrency);
// This is the error preventing code
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // two spinners.
        final Spinner spn1 = findViewById(R.id.spnSex);
        final Spinner spn2 = findViewById(R.id.spnSex2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.currencyname, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn1.setAdapter(adapter);
        spn1.setOnItemSelectedListener(this);
        spn2.setAdapter(adapter);
        spn2.setOnItemSelectedListener(this);

        final Button doExchange = findViewById(R.id.showExchange);
        doExchange.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                String input = inputCurrency.getText().toString();
                if (input.equals("")) {
                    return;
                }
                String inPut = spn1.getSelectedItem().toString();
                String outPut = spn2.getSelectedItem().toString();
                if (inPut.equals(outPut)) {
                    ((TextView) findViewById(R.id.OutputCurrency)).setText(input);
                    return;
                }
                String json = getJson(url + inPut);
                double inputNumber = Double.valueOf(input);
                double result = calculate(json, outPut, inputNumber);
                ((TextView) findViewById(R.id.OutputCurrency)).setText(String.valueOf(result));
            }
        });
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



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public double calculate(String json, String outCurrency, double inputNumber) {
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        double rate = result.get("rates").getAsJsonObject().get(outCurrency).getAsDouble();
        return rate * inputNumber;
    }
}
