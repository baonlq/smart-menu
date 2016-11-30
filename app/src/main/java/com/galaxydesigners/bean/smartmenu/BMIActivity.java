package com.galaxydesigners.bean.smartmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class BMIActivity extends AppCompatActivity {

    // Init control
    private EditText etAge;
    private EditText etHeight;
    private EditText etWeight;
    private RadioGroup rdgSex;
    private RadioButton rdbMale;
    private RadioButton rdbFemale;
    private TextView tvResult;
    private TextView tvNotification;
    private Button btnCalculate;
    /*private EditText ;*/
    private void IntializeControl(){
        etAge = (EditText)findViewById(R.id.etAge);
        etHeight = (EditText)findViewById(R.id.etHeight);
        etWeight= (EditText)findViewById(R.id.etWeight);
        rdbFemale= (RadioButton) findViewById(R.id.rdbFemale);
        rdbMale= (RadioButton) findViewById(R.id.rdbMale);
        rdgSex= (RadioGroup) findViewById(R.id.rdgSex);
        btnCalculate= (Button) findViewById(R.id.btnCalculate);
        tvResult = (TextView)findViewById(R.id.tvResult);
        tvNotification = (TextView)findViewById(R.id.tvNotification);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        IntializeControl();
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int age = Integer.parseInt(etAge.getText().toString());
                double height = Double.parseDouble(etHeight.getText().toString());
                double weight = Double.parseDouble(etWeight.getText().toString());
                boolean isMale = true;
                // Group
                int sexID = rdgSex.getCheckedRadioButtonId();
                if(sexID == R.id.rdbFemale)
                    isMale = false;
                else if(sexID == rdgSex.getCheckedRadioButtonId())
                    isMale = true;
                BmiCaculate(age,height,weight,isMale);

            }
        });
    }
    public void BmiCaculate(int age, double height, double weight,boolean isMale){
         /*Convert to standar unit
         * height: meter
         * weight: kg
         * */
        String result = "";
        height = height/100; // ==> cm -> m
        double bmi = (weight)/Math.pow(height,2);
        tvNotification.setText(String.valueOf(bmi));

            // Con trai
            if(isMale){
                if(bmi<20)
                    tvResult.setText("Nhẹ cân");
                else if(bmi >=20 && bmi < 25){
                    tvResult.setText("Bình thường");
                } else if(bmi >=25 && bmi <= 30){
                    tvResult.setText("Người quá cân");
                } else if(bmi>30){
                    tvResult.setText("Người béo phì");
                }
            } else // Con gai
            {
                if(bmi<18)
                    tvResult.setText("Người dưới cân");
                else if(bmi >=18 && bmi < 23){
                    tvResult.setText("Bình thường");
                } else if(bmi >=23 && bmi <= 30){
                    tvResult.setText("Người quá cân");
                } else if(bmi>30){
                    tvResult.setText("Người béo phì");
                }
            }

    }

}
