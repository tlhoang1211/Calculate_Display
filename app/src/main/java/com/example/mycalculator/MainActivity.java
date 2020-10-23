package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private Button zero, one, two, three, four, five, six, seven, eight, nine, mul, div, add, sub, equal, dot, lunisolar, ce, c, bs;
    private String input, answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = findViewById(R.id.screen);
        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        mul = findViewById(R.id.mul);
        div = findViewById(R.id.div);
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        equal = findViewById(R.id.equal);
        dot = findViewById(R.id.dot);
        lunisolar = findViewById(R.id.lunisolar);
        ce = findViewById(R.id.ce);
        c = findViewById(R.id.c);
        bs = findViewById(R.id.bs);
    }

    public void ButtonClick(View view) {
        Button button = (Button) view;
        String data = button.getText().toString();
            switch (data) {
                case "CE":
                    if (isNumeric(input.charAt(input.length() - 1))) {
                        input = input.substring(0, input.length() - 1);
                    } else {
                        input = input + "";
                    }
                    break;
                case "C":
                    input = "";
                    break;
                case "BS":
                    input = input.substring(0, input.length() - 1);
                    break;
                case "x":
                    Solve();
                    input += "*";
                    break;
                case "=":
                    Solve();
                    answer = input;
                    break;
                case "+/-":
                    if (input.startsWith("-")) {
                        input = input.replace(input.substring(0, 1), "");
                    } else {
                        input = "-" + input;
                    }
                    break;
                default:
                    if (input == null) {
                        input = "";
                    }
                    if (data.equals("+") || data.equals("-") || data.equals("/")) {
                        Solve();
                    }
                    input += data;
            }

            screen.setText(input);
    }

    public void Solve() {
            if (input.split("\\*").length == 2) {
                String[] number = input.split("\\*");
                try {
                    double mul = Double.parseDouble(number[0]) * Double.parseDouble(number[1]);
                    input = mul + "";
                } catch (Exception e) {
                    //Toggle Error
                }
            } else if (input.split("/").length == 2) {
                String[] number = input.split("/");
                try {
                    double div = Double.parseDouble(number[0]) / Double.parseDouble(number[1]);
                    input = div + "";
                } catch (Exception e) {
                    //Toggle Error
                }
            } else if (input.split("\\+").length == 2) {
                String[] number = input.split("\\+");
                try {
                    double add = Double.parseDouble(number[0]) + Double.parseDouble(number[1]);
                    input = add + "";
                } catch (Exception e) {
                    //Toggle Error
                }
            } else if (input.split("-").length > 1) {
                String[] number = input.split("-");
                if (number[0].equals("") && number.length == 2) {
                    number[0] = 0 + "";
                }
                try {
                    double sub = 0;
                    if (number.length == 2) {
                        sub = Double.parseDouble(number[0]) - Double.parseDouble(number[1]);
                    } else if (number.length == 3) {
                        sub = -Double.parseDouble(number[1]) - Double.parseDouble(number[2]);
                    }
                    input = sub + "";
                } catch (Exception e) {
                    //Toggle Error
                }
            }

            String[] n = input.split("\\.");
            if (n.length > 1) {
                if (n[1].equals("0")) {
                    input = n[0];
                }
            }
            screen.setText(input);
    }

    public static boolean isNumeric(Character character) {
        try {
            Double.parseDouble(String.valueOf(character));
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}