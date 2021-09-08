package com.example.t1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView number1 = (TextView) findViewById(R.id.number1TextView);
                TextView number2 = (TextView) findViewById(R.id.number2TextView);

                TextView result = (TextView) findViewById(R.id.resultView);
                if (number1.getText() != null && number2.getText() != null) {
                    int sumOfNumbers = Integer.parseInt(number1.getText().toString()) + Integer.parseInt(number2.getText().toString());
                    result.setText(String.valueOf(sumOfNumbers));
                }
            }
        });
    }

}