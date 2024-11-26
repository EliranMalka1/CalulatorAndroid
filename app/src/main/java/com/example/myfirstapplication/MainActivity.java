package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Global variable for the main TextView
    TextView result;

    //Variable to save the input from the user and the ans
    double num1=Double.NaN,num2;
    double ans;

    //Variable to save the action that the user want to do
    String act;

    //Flag for Math Error Case a/0=!
    boolean errorFlag = false;

    //Flag to mark when the app already calculate something
    boolean equalflag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.TextResult);
        result.setText("");
    }

    //This func start when the user press on number button and write the number in the TextView
    public void numFunc(View view) {
        if(!equalflag) {
            checkMathError();
            Button button = (Button) view;
            result.append(button.getText().toString());
            errorFlag = false;
        }
    }


    //This func start when the user press on action button(exept C and =)
    //We save the number on the TextView to num1 and save the action as chat to act
    public void actFunc(View view) {
        if(!errorFlag) {
            Button button = (Button) view;
            act = button.getText().toString();
            if ((!result.getText().toString().trim().isEmpty()) || (!result.getText().toString().trim().equals(""))) {
                num1 = Double.parseDouble(result.getText().toString());
                result.setText("");
                changeActColorBack();
                button.setBackgroundResource(R.drawable.clickactbotton);
                equalflag=false;
            }
        }

    }

    //This function start when we want to restart the action colors background
    public void changeActColorBack()
    {
        Button[] b = new Button[4];
        b[0]=findViewById(R.id.buttonDivision);
        b[1]=findViewById(R.id.buttonMultiple);
        b[2]=findViewById(R.id.buttonPlus);
        b[3]=findViewById(R.id.buttonSub);
        for(int i=0;i<4;i++)
            b[i].setBackgroundResource(R.drawable.actionsdesign);
    }

    //This func start when the C button press and clear all
    public void ClearFunc(View view) {
        num1=Double.NaN;
        num2=Double.NaN;
        act="";
        result.setText("");
        changeActColorBack();
        equalflag=false;
        errorFlag=false;
    }

    //This func start when I want to clear all the data
    public void ClearFunc() {
        num1=Double.NaN;
        act="";
        result.setText("");
        changeActColorBack();
        equalflag=false;
    }

    //This function start when we want to check if the TextView display Math Error
    public void checkMathError()
    {
        if(result.getText().toString().trim().equals("Math Error")) {
            result.setText("");
            ClearFunc();
            equalflag=false;
        }

    }

    //This func start when the user press the = button
    //Save on num2 the number on the TextView, Calculate the answer and save on answer to ans
    //Display ans(or Math Error) on the TextView
    public void EqualFunc(View view) {
        checkMathError();
        if(!Double.isNaN(num1)) {
            if (!result.getText().toString().trim().isEmpty()) {
                num2 = Double.parseDouble(result.getText().toString());
                switch (act) {
                    case "+":
                        ans = num1 + num2;
                        break;
                    case "-":
                        ans = num1 - num2;
                        break;
                    case "×":
                        ans = num1 * num2;
                        break;
                    case "÷": {
                        if (num2 == 0) {
                            result.setText("Math Error");
                            changeActColorBack();
                            errorFlag = true;
                            return;
                        } else
                            ans = num1 / num2;
                        break;
                    }
                    default:
                        throw new IllegalStateException("Unexpected value: " + act);
                }

                result.setText(String.valueOf(ans));
                changeActColorBack();
                equalflag=true;
            }
        }
    }
}