package com.rainbowcryptocoder.fibonacci;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import io.github.kexanie.library.MathView;

public class MainActivity extends AppCompatActivity {

    EditText et_series_length, et_input_1, et_input_2;
    TextView tv_solution;
    MathView mathView;
    Button btn_calculate;
    String tex = "This come from string. You can insert inline formula:" +
            " \\(ax^2 + bx + c = 0\\) " +
            "or displayed formula: $$\\sum_{i=0}^n i^2 = \\frac{(n^2+n)(2n+1)}{6}$$";
    int n = 7, firstTerm = 0, secondTerm = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        tv_solution = findViewById(R.id.tv_solution);
        et_series_length = findViewById(R.id.et_series_length);
        et_input_1 = findViewById(R.id.et_input_1);
        et_input_2 = findViewById(R.id.et_input_2);

        btn_calculate = findViewById(R.id.btn_calculate);

//        mathView = findViewById(R.id.mathView);

        initListener();
    }

    private void initListener() {


        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_input_1.getText().toString().length() == 0){
//                    Toast.makeText(MainActivity.this, "Please enter value 1", Toast.LENGTH_SHORT).show();
                    et_input_1.setError("Can not be empty");
                }else if (et_input_2.getText().toString().length() == 0){
//                    Toast.makeText(MainActivity.this, "Please enter value 2", Toast.LENGTH_SHORT).show();
                    et_input_2.setError("Can not be empty");
                }else if (et_series_length.getText().toString().length() == 0){
                    et_series_length.setError("Can not be empty");
                } else{

                    InputMethodManager imm = (InputMethodManager) getBaseContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                    firstTerm = Integer.parseInt(et_input_1.getText().toString());
                    secondTerm = Integer.parseInt(et_input_2.getText().toString());

                    calculateFibonachiSeries(firstTerm, secondTerm);
                }
            }
        });
    }

    private void calculateFibonachiSeries(int firstTerm, int secondTerm) {
        StringBuilder stringBuilder = new StringBuilder();
        n = Integer.parseInt(et_series_length.getText().toString());
        for (int i = 1; i <= n; ++i) {
            if (i == n){
                stringBuilder.append(firstTerm);
            }else{
                stringBuilder.append(firstTerm + ", ");
            }
            // compute the next term
            int nextTerm = firstTerm + secondTerm;
            firstTerm = secondTerm;
            secondTerm = nextTerm;
        }

        tv_solution.setText(stringBuilder.toString());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_setting:
            //settings

                Intent settings = new Intent(this, AccountActivity.class);
                startActivity(settings);
                break;

            default:
            // select an option

        }
        return super.onOptionsItemSelected(item);
    }
}
