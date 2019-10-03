package com.klsoftware.TemperatureConversion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.text.TextUtils.isEmpty;


public class MainActivity extends AppCompatActivity {

    private Button mConvertButton;

    private EditText editText;
    private TextView textView;

    private RadioGroup radioSrcGroup;
    private RadioGroup radioTgtGroup;

    private RadioButton radioSrcButton;
    private RadioButton radioTgtButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        mConvertButton = (Button) findViewById(R.id.convert_button);
        mConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radioSrcGroup = (RadioGroup) findViewById(R.id.rbtngrpSrc);
                radioTgtGroup = (RadioGroup) findViewById(R.id.rbtngrpTgt);

                // get selected radio button from radioGroup
                int selectedSrcId = radioSrcGroup.getCheckedRadioButtonId();
                int selectedTgtId = radioTgtGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioSrcButton = (RadioButton) findViewById(selectedSrcId);
                radioTgtButton = (RadioButton) findViewById(selectedTgtId);

                editText = (EditText) findViewById(R.id.enteredValue);
                textView = (TextView) findViewById(R.id.resultValue);

                if (radioSrcButton.getText() == radioTgtButton.getText() || TextUtils.isEmpty(editText.getText())) {
                    Toast.makeText(MainActivity.this,"ERROR! No Conversion Requested.", Toast.LENGTH_SHORT).show();
                    double temperatureValue = Double.parseDouble(editText.getText().toString());
                    java.text.DecimalFormat decimalFormatter = new java.text.DecimalFormat("0.##");
                    textView.setText(decimalFormatter.format(temperatureValue));
                } else {
                    double temperatureValue = Double.parseDouble(editText.getText().toString());
                    double conversion = 0.0;

                    java.lang.CharSequence sSource = radioSrcButton.getText();
                    sSource = String.valueOf(sSource);
                    java.lang.CharSequence sTarget = radioTgtButton.getText();
                    sTarget = String.valueOf(sTarget);

                    if (sSource.equals("C") && sTarget.equals("F")) {
                        conversion = ConvertTemp.c2f(temperatureValue);
                    } else if ((sSource.equals("C") && sTarget.equals("K"))) {
                        conversion = ConvertTemp.c2k(temperatureValue);
                    } else if ((sSource.equals("F") && sTarget.equals("C"))) {
                        conversion = ConvertTemp.f2c(temperatureValue);
                    } else if ((sSource.equals("F") && sTarget.equals("K"))) {
                        conversion = ConvertTemp.f2k(temperatureValue);
                    } else if ((sSource.equals("K") && sTarget.equals("C"))) {
                        conversion = ConvertTemp.k2c(temperatureValue);
                    } else if ((sSource.equals("K") && sTarget.equals("F"))) {
                        conversion = ConvertTemp.k2f(temperatureValue);
                    }

                    //log.d(Double.toString(conversion));
                    // Push converstion to textView
                    java.text.DecimalFormat decimalFormatter = new java.text.DecimalFormat("0.##");
                    textView.setText(decimalFormatter.format(conversion));

                    /* Debugging by showing values with toast */
                    // Toast.makeText(MainActivity.this, editText.getText(), Toast.LENGTH_SHORT).show();
                    // Toast.makeText(MainActivity.this, radioSrcButton.getText(), Toast.LENGTH_SHORT).show();
                    // Toast.makeText(MainActivity.this, radioTgtButton.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
