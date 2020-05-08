package com.example.cst_438_project3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cst_438_project3.DB.AppDatabase;
import com.example.cst_438_project3.DB.UserDAO;
import com.example.cst_438_project3.DB.WeightLogDAO;
import com.example.cst_438_project3.Objects.User;
import com.example.cst_438_project3.Objects.WeightLog;

import java.util.concurrent.atomic.AtomicInteger;

public class WeightLogActivity extends AppCompatActivity {

    EditText weight;
    Button submit;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_log);
        weight = findViewById(R.id.currWeight);
        submit = findViewById(R.id.submit);

        //Get extras from Login
        final AtomicInteger user_id = new AtomicInteger(-1);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                user_id.set(extras.getInt("user_id"));
            }
        } else {
            user_id.set((int) savedInstanceState.getSerializable("user_id"));
        }

        //Get current logged in user
        user = AppDatabase.getAppDatabase(WeightLogActivity.this).
                userDAO().getUserByID(user_id.get());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmInfo();
            }
        });
    }

    public void confirmInfo(){
        float currWeight = Float.valueOf(weight.getText().toString());
        WeightLogDAO weight_log_dao = AppDatabase.getAppDatabase(WeightLogActivity.this).weightLogDAO();
        WeightLog newWeightLog = new WeightLog (currWeight, user.getUserID());
        //insert into activity
        weight_log_dao.insert(newWeightLog);

        // inform user that the weight has been saved
        AlertDialog.Builder builder = new AlertDialog.Builder(WeightLogActivity.this);
        builder.setTitle("Entry successfully saved.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
