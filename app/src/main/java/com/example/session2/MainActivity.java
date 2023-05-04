package com.example.session2;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    EditText amount;
    EditText numPax;
    ToggleButton svs;
    ToggleButton gst;
    TextView totalBill;
    TextView eachPays;
    Button split;
    Button reset;
    EditText discount;
    RadioGroup payment;
    TextView via;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.editInputAmount);
        numPax = findViewById(R.id.editInputNumPax);
        totalBill = findViewById(R.id.textTotalBill);
        eachPays = findViewById(R.id.textEachPays);
        svs = findViewById(R.id.tbSVS);
        gst = findViewById(R.id.tbGST);
        split = findViewById(R.id.bSplit);
        reset = findViewById(R.id.bReset);
        discount = findViewById(R.id.editInputDisc);
        payment = findViewById(R.id.rgpayment);
        via = findViewById(R.id.howPay);

    split.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double newAmt = 0.0;
            if (amount.getText().toString().trim().length() != 0 && numPax.getText().toString().trim().length() != 0) {
                Double newAmount = 0.0;
                if (!svs.isChecked() && !gst.isChecked()) {
                    newAmt = Double.parseDouble(amount.getText().toString());
                } else if (svs.isChecked() && !gst.isChecked()) {
                    newAmt = Double.parseDouble(amount.getText().toString()) * 1.1;
                } else if (!svs.isChecked() && gst.isChecked()) {
                    newAmt = Double.parseDouble(amount.getText().toString()) * 1.07;}


                if (discount.getText().toString().trim().length() != 0) {
                    newAmt *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                }

                totalBill.setText("Total Bill: $" + String.format("%.2f", newAmt));
                int numPerson = Integer.parseInt(numPax.getText().toString());
                if (numPerson != 1) {
                    eachPays.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson));
                } else {
                    eachPays.setText("Each Pays: $" + newAmt);
                }


                int checkedRadioId = payment.getCheckedRadioButtonId();
                String method;
                if (checkedRadioId == R.id.bCash) {
                    method = "via Cash";
                } else {
                    method = "via PayNow";
                }

                via.setText(method);


            }
        }
    });

    reset.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            amount.setText("");
            numPax.setText("");
            svs.setChecked(false);
            gst.setChecked(false);
            discount.setText("");


        }
    });



    }
}