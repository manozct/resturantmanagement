package rms.manozct.resturantmanagement.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import rms.manozct.resturantmanagement.R;

public class EmployeeActivity extends AppCompatActivity {
    private EditText nameTxt;
    private Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        nameTxt = (EditText) findViewById(R.id.nameText);
        submitBtn = (Button) findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameTxt.getText().toString();
                Toast.makeText(EmployeeActivity.this, "Hello "+name, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
