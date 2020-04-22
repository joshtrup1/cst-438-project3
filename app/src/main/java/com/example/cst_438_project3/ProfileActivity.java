package com.example.cst_438_project3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.cst_438_project3.DB.AppDatabase;
import com.example.cst_438_project3.Objects.User;
import java.util.concurrent.atomic.AtomicInteger;

public class ProfileActivity extends AppCompatActivity {

    TextView usernameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
        User user = AppDatabase.getAppDatabase(ProfileActivity.this).
                userDAO().getUserByID(user_id.get());

        //setText for username
        usernameTextView = findViewById(R.id.username);
        usernameTextView.setText(user.getUsername());
    }
}
