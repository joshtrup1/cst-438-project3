package com.example.cst_438_project3;





import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import androidx.core.content.ContextCompat;
import java.text.NumberFormat;
import android.widget.Button;
import android.widget.Toast;
//import com.google.android.gms.auth.api.signin.internal.
import android.view.View;







public class StepCounter extends AppCompatActivity implements View.OnClickListener {


    final private int REQUEST_CODE_ASK_PERMISSION = 124;
    public static final String TAG = "StepCounter";
    private static final int REQUEST_OAUTH_REQUEST_CODE = 0x1001;
    GoogleSignInClient mmClient;
    private static int RC_SIGN_IN = 100;


    public ArcProgress mRunningProgress;
    public TextView mRunningCalories, mRunningActive, mRunningDistance;

    GoogleSignInAccount mClient;
    // Configure sign-in to request the user's ID, email address, and basic
    // profile. ID and basic profile are included in DEFAULT_SIGN_IN.




    //mClient = GoogleSignIn.getClient(this, gso);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);
        // This method sets up our custom logger, which will print all log messages to the device
        // screen, as well as to adb logcat.

        mRunningProgress = (ArcProgress)findViewById(R.id.runningProgress);
        mRunningCalories = (TextView)findViewById(R.id.runningCaloriesTextView);
        mRunningActive = (TextView)findViewById(R.id.runningActiveTextView);
        mRunningDistance = (TextView)findViewById(R.id.runningDistanceTextView);
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        findViewById(R.id.sign_in_button).setOnClickListener(this);





        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mmClient = GoogleSignIn.getClient(this, gso);





        //Request fine location permission



        FitnessOptions fitnessOptions =
                FitnessOptions.builder()
                        .addDataType(DataType.TYPE_STEP_COUNT_CUMULATIVE, FitnessOptions.ACCESS_READ)
                        .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                        .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                        .addDataType(DataType.TYPE_ACTIVITY_SEGMENT, FitnessOptions.ACCESS_READ)
                        .addDataType(DataType.TYPE_CALORIES_EXPENDED, FitnessOptions.ACCESS_READ)
                        .addDataType(DataType.AGGREGATE_CALORIES_EXPENDED, FitnessOptions.ACCESS_READ)
                        .addDataType(DataType.TYPE_DISTANCE_DELTA, FitnessOptions.ACCESS_READ)
                        .addDataType(DataType.AGGREGATE_DISTANCE_DELTA, FitnessOptions.ACCESS_READ)
                        .addDataType(DataType.TYPE_HEART_RATE_BPM, FitnessOptions.ACCESS_READ)
                        .build();
        if (!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(this), fitnessOptions)) {
            GoogleSignIn.requestPermissions(
                    this,
                    REQUEST_OAUTH_REQUEST_CODE,
                    GoogleSignIn.getLastSignedInAccount(this),
                    fitnessOptions);
        } else {
            subscribeStepCount();
            subscribeDistance();
            subscribeCalorie();
            subscribeActiveTime();

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_OAUTH_REQUEST_CODE) {
                subscribeStepCount();
                subscribeDistance();
                subscribeCalorie();
                subscribeActiveTime();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            // ...
        }
    }

    private void signIn() {
        Intent signInIntent = mmClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }




    /** Records step data by requesting a subscription to background step data. */
    public void subscribeStepCount() {
        // To create a subscription, invoke the Recording API. As soon as the subscription is
        // active, fitness data will start recording.
        Fitness.getRecordingClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .subscribe(DataType.TYPE_STEP_COUNT_CUMULATIVE)

                .addOnCompleteListener(
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.i(TAG, "Successfully subscribed!");
                                    final Handler handler = new Handler();
                                    final int delay = 500; //milliseconds


                                    handler.postDelayed(new Runnable(){
                                        public void run(){
                                            readStepData();
                                            handler.postDelayed(this, delay);
                                        }
                                    }, delay);



                                } else {
                                    Log.w(TAG, "There was a problem subscribing to step count data.", task.getException());
                                }
                            }
                        });
    }






    public void subscribeDistance() {
        // To create a subscription, invoke the Recording API. As soon as the subscription is
        // active, fitness data will start recording.
        Fitness.getRecordingClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .subscribe(DataType.TYPE_DISTANCE_DELTA)

                .addOnCompleteListener(
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.i(TAG, "Successfully subscribed!");
                                    final Handler handler = new Handler();
                                    final int delay = 500; //milliseconds

                                    handler.postDelayed(new Runnable(){
                                        public void run(){
                                            readDistance();
                                            handler.postDelayed(this, delay);
                                        }
                                    }, delay);

                                } else {
                                    Log.w(TAG, "There was a problem subscribing to distance.", task.getException());
                                }
                            }
                        });
    }

    public void subscribeCalorie() {
        // To create a subscription, invoke the Recording API. As soon as the subscription is
        // active, fitness data will start recording.
        Fitness.getRecordingClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .subscribe(DataType.TYPE_CALORIES_EXPENDED)

                .addOnCompleteListener(
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.i(TAG, "Successfully subscribed!");
                                    final Handler handler = new Handler();
                                    final int delay = 500; //milliseconds

                                    handler.postDelayed(new Runnable(){
                                        public void run(){
                                            readCalorieData();
                                            handler.postDelayed(this, delay);
                                        }
                                    }, delay);

                                } else {
                                    Log.w(TAG, "There was a problem subscribing to calorie.", task.getException());
                                }
                            }
                        });
    }

    public void subscribeActiveTime() {
        // To create a subscription, invoke the Recording API. As soon as the subscription is
        // active, fitness data will start recording.
        Fitness.getRecordingClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .subscribe(DataType.TYPE_ACTIVITY_SEGMENT)

                .addOnCompleteListener(
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.i(TAG, "Successfully subscribed!");
                                    final Handler handler = new Handler();
                                    final int delay = 500; //milliseconds

                                    handler.postDelayed(new Runnable(){
                                        public void run(){
                                            readActiveTime();
                                            handler.postDelayed(this, delay);
                                        }
                                    }, delay);

                                } else {
                                    Log.w(TAG, "There was a problem subscribing to distance.", task.getException());
                                }
                            }
                        });
    }


    private void readActiveTime() {
        Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .readDailyTotal(DataType.TYPE_ACTIVITY_SEGMENT)
                .addOnSuccessListener(
                        new OnSuccessListener<DataSet>() {
                            @Override
                            public void onSuccess(DataSet dataSet) {
                                int total =
                                        dataSet.isEmpty()
                                                ? 0
                                                : dataSet.getDataPoints().get(0).getValue(Field.FIELD_ACTIVITY).asInt();
                                mRunningActive.setText(String.valueOf(total)+"\nmins");
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(StepCounter.this, "There was a problem getting the active time.", Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    private void readDistance() {
        Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .readDailyTotal(DataType.TYPE_DISTANCE_DELTA)
                .addOnSuccessListener(
                        new OnSuccessListener<DataSet>() {
                            @Override
                            public void onSuccess(DataSet dataSet) {
                                float total =
                                        dataSet.isEmpty()
                                                ? 0
                                                : dataSet.getDataPoints().get(0).getValue(Field.FIELD_DISTANCE).asFloat();
                                mRunningDistance.setText(String.valueOf(total)+"\nmi");
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                //Toast.makeText(RunningActivityTest.this, "There was a problem getting the distance.", Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    /**
     * Reads the current daily step total, computed from midnight of the current day on the device's
     * current timezone.
     */
    private void readStepData() {
        Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .readDailyTotalFromLocalDevice(DataType.TYPE_STEP_COUNT_DELTA)
                .addOnSuccessListener(
                        new OnSuccessListener<DataSet>() {
                            @Override
                            public void onSuccess(DataSet dataSet) {
                                int total =
                                        dataSet.isEmpty()
                                                ? 0
                                                : dataSet.getDataPoints().get(0).getValue(Field.FIELD_STEPS).asInt();
                                mRunningProgress.setMax(5000);
                                mRunningProgress.setTextColor(ContextCompat.getColor(StepCounter.this, R.color.white));
                                mRunningProgress.setBottomText("Goal: 5000");
                                mRunningProgress.setProgress(total);
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(StepCounter.this, "There was a problem getting the step count.", Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    private void readCalorieData() {
        Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .readDailyTotal(DataType.TYPE_CALORIES_EXPENDED)
                .addOnSuccessListener(
                        new OnSuccessListener<DataSet>() {
                            @Override
                            public void onSuccess(DataSet dataSet) {
                                float total =
                                        dataSet.isEmpty()
                                                ? 0
                                                : dataSet.getDataPoints().get(0).getValue(Field.FIELD_CALORIES).asFloat();
                                int total1 = (int)total;
                                mRunningCalories.setText(String.valueOf(total1)+"\ncalories");
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(StepCounter.this, "There was a problem getting the calorie count.", Toast.LENGTH_SHORT).show();
                            }
                        });


    }





}