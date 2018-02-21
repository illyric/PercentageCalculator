package com.ilirkosumi.percentcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    private EditText percentageTxtIn;
    private EditText numberToCalcTxtIn;
    private TextView totalTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        totalTxtView = findViewById(R.id.totalTxtView);
        percentageTxtIn = findViewById(R.id.percentInputTxt);
        numberToCalcTxtIn = findViewById(R.id.numberToCalcTxtInput);


        findViewById(R.id.calcBtn).setOnClickListener(view -> {
                    Double percentage;

                    if (!isValidUserInput(percentageTxtIn)) {
                        displayToast("Please enter a valid Percentage!");
                        return;
                    } else {
                        percentage = Double.parseDouble(percentageTxtIn.getText().toString());
                        if (percentage > 100d) {
                            percentage = 100d;
                            percentageTxtIn.setText(String.valueOf(100d));
                            displayToast("Percentage can not be bigger than 100!");
                        } else if (percentage < 0d) {
                            percentage = 0d;
                            percentageTxtIn.setText(String.valueOf(0d));
                            displayToast("Percentage can not be negative!");
                        }
                    }

                    if (!isValidUserInput(numberToCalcTxtIn)) {
                        displayToast("Please enter a valid number to calculate!");
                        return;
                    } else {
                        Double numberToCalc = Double.parseDouble(numberToCalcTxtIn.getText().toString());
                        Double resultUnformatted = (percentage / 100) * numberToCalc;
                        BigDecimal finalResult = new BigDecimal(resultUnformatted).setScale(2, RoundingMode.HALF_EVEN);
                        totalTxtView.setText(String.valueOf(finalResult));
                        scrollToTop();
                    }
                }
        );

        findViewById(R.id.clearBtn).setOnClickListener(view -> {
            percentageTxtIn.setText("");
            numberToCalcTxtIn.setText("");
            totalTxtView.setText("0");
            scrollToTop();
        });
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public boolean isValidUserInput(EditText et) {
        return !et.getText().toString().isEmpty();
    }

    public void scrollToTop() {
        ScrollView sv = findViewById(R.id.scrollView);
        sv.fullScroll(ScrollView.FOCUS_UP);
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
}
