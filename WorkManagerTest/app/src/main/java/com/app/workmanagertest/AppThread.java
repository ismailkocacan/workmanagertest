package com.app.workmanagertest;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class AppThread extends Thread{

    boolean exit = false;

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public AppThread(){
        Log.i(AppConst.TAG_DEBUG,"Thread new alloc.");
    }

    @Override
    public void run() {
        while (true){
            if (exit) break;
            try {
                Log.i(AppConst.TAG_DEBUG,"Thread is running... ("+Long.toString(this.getId())+")");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.i(AppConst.TAG_DEBUG,"Thread end ("+Long.toString(this.getId())+")");
    }
}
