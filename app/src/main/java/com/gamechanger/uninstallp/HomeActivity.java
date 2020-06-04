package com.gamechanger.uninstallp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.gamechanger.uninstallp.MenusItem.ReportActivity;

public class HomeActivity extends AppCompatActivity {
    Button button;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_services,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        button=findViewById(R.id.btn_scan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.report:
                startActivity(new Intent(getApplicationContext(), ReportActivity.class));
//                Toast.makeText(getApplicationContext(),"Report bug",Toast.LENGTH_SHORT).show();
                return true;
//            case R.id.contact:
//                Toast.makeText(getApplicationContext(),"Contack",Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.Share:
//                Toast.makeText(getApplicationContext(),"Share",Toast.LENGTH_SHORT).show();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
