package com.app.workmanagertest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import static androidx.work.PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS;

public class MainActivity extends AppCompatActivity {

    TextView idStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idStatus = findViewById(R.id.idStatus);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WorkManager mWorkManager = WorkManager.getInstance(this);
            //OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(AppWorker.class).build();
            PeriodicWorkRequest mRequest = new PeriodicWorkRequest.
                    Builder(AppWorker.class, MIN_PERIODIC_FLEX_MILLIS, TimeUnit.MILLISECONDS).build();
            mWorkManager.getWorkInfoByIdLiveData(mRequest.getId()).observe(this, new Observer<WorkInfo>() {
                @Override
                public void onChanged(@Nullable WorkInfo workInfo) {
                    if (workInfo != null) {
                        WorkInfo.State state = workInfo.getState();
                        idStatus.append(state.toString() + "\n");
                    }
                }
            });
            mWorkManager.enqueue(mRequest);
        }
    }
}
