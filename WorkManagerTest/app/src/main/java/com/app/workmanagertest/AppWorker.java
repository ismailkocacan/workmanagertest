package com.app.workmanagertest;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class AppWorker extends Worker {

    public AppWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        Log.i(AppConst.TAG_DEBUG,"AppWorker.newinstance ("+getId().toString()+")");
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.i(AppConst.TAG_DEBUG,"AppWorker.doWork ("+getId().toString()+")");
        return Result.retry();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.i(AppConst.TAG_DEBUG,"AppWorker.finalize ("+getId().toString()+")");
    }
}
