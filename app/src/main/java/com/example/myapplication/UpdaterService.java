package com.example.myapplication;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class UpdaterService extends Service {
    public UpdaterService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String timeInWords[] = TextTime.currentTimeInWords();

        RemoteViews view = new RemoteViews(getPackageName(), R.layout.text_clock);
        view.setTextViewText(R.id.widgethour, timeInWords[0]);
        view.setTextViewText(R.id.widgetminute, timeInWords[1]);
        ComponentName theWidget = new ComponentName(this, TextClock.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        manager.updateAppWidget(theWidget, view);
        return START_STICKY;
    }
}
