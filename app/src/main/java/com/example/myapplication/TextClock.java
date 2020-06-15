package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.widget.RemoteViews;
import android.widget.Toast;

import static com.example.myapplication.TextTime.currentTimeInWords;

/**
 * Implementation of App Widget functionality.
 */
public class TextClock extends AppWidgetProvider {
    private PendingIntent service;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.text_clock);
        String timeInWords[] = TextTime.currentTimeInWords();
        views.setTextViewText(R.id.widgethour, timeInWords[0]);
        views.setTextViewText(R.id.widgetminute, timeInWords[1]);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        final Intent i = new Intent(context, UpdaterService.class);

        if (service == null) {
            service = PendingIntent.getService(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
        }
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), 60000, service);
        /*for (int appWidgetId : appWidgetIds) {
            //updateAppWidget(context, appWidgetManager, appWidgetId);
        }*/
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.text_clock);
        String timeInWords[] = TextTime.currentTimeInWords();
        remoteViews.setTextViewText(R.id.widgethour, timeInWords[0]);
        remoteViews.setTextViewText(R.id.widgetminute, timeInWords[1]);
        ComponentName thiswidget = new ComponentName(context, TextClock.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(thiswidget, remoteViews);

/*        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        //alarm set when minute changes and for every sixty seconds
        am.setRepeating(AlarmManager.ELAPSED_REALTIME, +SystemClock.elapsedRealtime(), 60000 , pi);*/
    }

    @Override
    public void onDisabled(Context context) {
        Toast.makeText(context, "onDisabled():last widget instance removed", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
        super.onDisabled(context);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        Toast.makeText(context, "TimeWidgetRemoved id(s):"+appWidgetIds, Toast.LENGTH_SHORT).show();
        super.onDeleted(context, appWidgetIds);
    }
}

