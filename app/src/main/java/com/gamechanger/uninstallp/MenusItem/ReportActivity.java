package com.gamechanger.uninstallp.MenusItem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.gamechanger.uninstallp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ReportActivity extends AppCompatActivity {
    Button report_btn;
    TextInputLayout textInputLayout;
    TextInputEditText textInputEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        report_btn=findViewById(R.id.report_btn);
        textInputEditText=findViewById(R.id.Mail);
        textInputLayout=findViewById(R.id.AiseHi);
        final String EmialAddress="goingswadeshi@gmail.com";
        final String subject="Bug Caught up or feedback";
        report_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg=textInputEditText.getText().toString().trim();
                if(!msg.isEmpty())
                {
                    Intent intent=new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_EMAIL,new String[]{EmialAddress});
                    intent.putExtra(Intent.EXTRA_SUBJECT,subject);
                    intent.putExtra(Intent.EXTRA_TEXT,msg);
                    intent.setType("message/rfc822");
                    startActivity(Intent.createChooser(intent,"Choose..."));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Field can not be empty",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
