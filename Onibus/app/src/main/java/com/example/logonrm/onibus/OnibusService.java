package com.example.logonrm.onibus;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;

public class OnibusService extends Service {
    private Timer timer;
    private AvisoOnibusTask task;

    public OnibusService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("SERVICO", "SERVICO INICIADO!!!!");
        timer = new Timer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("SERVICO", "PROCESSANDO!!!");
        task = new AvisoOnibusTask();
        timer.schedule(task, 0, 5 * 1000);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        Log.i("SERVICO", "SERVICO FINALIZADO!!!!");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
