package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Example18_ServiceDataTransferActivity extends AppCompatActivity {
    TextView dataFromServiceTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example18_service_data_transfer);

        // Get Component References
        dataFromServiceTv = (TextView)findViewById(R.id.dataFromServiceTv);
        final EditText dataToServiceEt = (EditText)findViewById(R.id.dataToServiceEt);
        Button dataToServiceBtn = (Button) findViewById(R.id.dataToServiceBtn);

        // Button Click Event Processing (using Anonymous Inner Class)
        dataToServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Service with the data form an EditText
                Intent i = new Intent(
                        getApplicationContext(),
                        Example18Sub_Service.class
                );
                // To transmit the data to Service,
                // Use Intent !
                // attach the data as [key : value] map pattern
                i.putExtra("DATA",dataToServiceEt.getText().toString());
                startService(i);
            }
        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        // receive resultData from the Service
        String result = intent.getExtras().getString("RESULT");
        // set the extracted data to TextView
        dataFromServiceTv.setText(result);
        super.onNewIntent(intent);
    }
}
