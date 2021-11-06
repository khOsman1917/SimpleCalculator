package com.khanosman.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button oneBtn;
    private Button twoBtn;
    private Button threeBtn;
    private Button fourBtn;
    private Button fiveBtn;
    private Button sixBtn;
    private Button sevenBtn;
    private Button eightBtn;
    private Button nineBtn;
    private Button zeroBtn;

    private Button plusBtn;
    private Button minusBtn;
    private Button multBtn;
    private Button divisioBtn;
    private Button equalBtn;
    private Button clearBtn;

    private TextView display;
    private TextView sumDisplay;

    private Boolean firstDigitClicked=false;
    private Boolean isOperator=false;
    private Boolean filledNumb1=false;
    private Boolean filledNumb2=false;

    private String oparator;

    private int numb1=0;
    private int numb2=0;

    private String numb1St="";
    private String numb2St="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.numbDisplay);
        sumDisplay = findViewById(R.id.sumTxt);

        oneBtn = findViewById(R.id.btn1);
        twoBtn = findViewById(R.id.btn2);
        threeBtn = findViewById(R.id.btn3);
        fourBtn = findViewById(R.id.btn4);
        fiveBtn = findViewById(R.id.btn5);
        sixBtn = findViewById(R.id.btn6);
        sevenBtn = findViewById(R.id.btn7);
        eightBtn = findViewById(R.id.btn8);
        nineBtn = findViewById(R.id.btn9);
        zeroBtn = findViewById(R.id.btn0);

        plusBtn = findViewById(R.id.plus);
        minusBtn = findViewById(R.id.minus);
        multBtn = findViewById(R.id.multiply);
        divisioBtn = findViewById(R.id.division);
        equalBtn = findViewById(R.id.equal);
        clearBtn = findViewById(R.id.clear_text);


        onNumberBtnClick(oneBtn);
        onNumberBtnClick(twoBtn);
        onNumberBtnClick(threeBtn);
        onNumberBtnClick(fourBtn);
        onNumberBtnClick(fiveBtn);
        onNumberBtnClick(sixBtn);
        onNumberBtnClick(sevenBtn);
        onNumberBtnClick(eightBtn);
        onNumberBtnClick(nineBtn);
        onNumberBtnClick(zeroBtn);

        onOperatorBtnClick(plusBtn);
        onOperatorBtnClick(minusBtn);
        onOperatorBtnClick(multBtn);
        onOperatorBtnClick(divisioBtn);

        onEqulaBtnClick();
        onClearBtnClick();

    }

    private void onNumberBtnClick(Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numTxt = btn.getText().toString();

                String tmp = (String) display.getText();

                if(tmp=="0") tmp="";

                if(!firstDigitClicked){

                    display.setText(numTxt);
                    firstDigitClicked=true;
                }
                else{
                    tmp+=numTxt;
                    display.setText(tmp);
                }
               if(!filledNumb1) numb1St+=numTxt;
               else {
                   numb2St += numTxt;
               }
                isOperator=false;
            }
        });
    }

    private void onOperatorBtnClick(Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String op= btn.getText().toString();
               String tmp = (String) display.getText();
               if(firstDigitClicked && !isOperator){
                   if(!filledNumb1) {
                       numb1 = Integer.parseInt(numb1St);
                       filledNumb1=true;
                   }
                   oparator = op;
                   tmp+=op;
                   display.setText(tmp);
                   isOperator=true;

               }

            }
        });
    }

    private void onEqulaBtnClick(){
        equalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numb2 = Integer.parseInt(numb2St);
                filledNumb2=true;
                Log.d("btn_test","pressed "+numb1+" "+oparator+" "+numb2);

                if(filledNumb1&&!isOperator&&filledNumb2){

                    float r = operation();
                    firstDigitClicked=false;
                    filledNumb1=false;
                    filledNumb2=false;
                    numb1St="";
                    numb2St="";
                    display.setText("0");
                    sumDisplay.setText("");
                    if(oparator!="/")  sumDisplay.setText(""+r);
                    else sumDisplay.setText(""+operation());
                    Log.d("btn_test","pressed "+r);

                }
            }
        });
    }

    private void onClearBtnClick(){
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 display.setText("0");
                 firstDigitClicked=false;
            }
        });
    }


    private float operation(){
       float res=0;
        switch (oparator){
            case "+":
               res= numb1+numb2;
               break;
            case "-":
                res= numb1-numb2;
                break;
            case "*":
                res= numb1*numb2;
                break;
            case "/":
                if(numb1!=0){
                    res= (float) numb1/numb2;
                }
                else{
                    res = 0;
                }
                break;
        }

        return  res;
    }



}