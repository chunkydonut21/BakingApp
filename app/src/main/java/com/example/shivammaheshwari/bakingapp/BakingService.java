package com.example.shivammaheshwari.bakingapp;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.util.ArrayList;


public class BakingService extends IntentService {

    public static String WIDGET_LIST ="WIDGET_LIST";


    public BakingService() {
        super("BakingService");
    }

    public static void startBakingService(Context context, ArrayList<String> ingredientsForWidget) {
        Intent intent = new Intent(context, BakingService.class);
        intent.putExtra(WIDGET_LIST, ingredientsForWidget);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            ArrayList<String> fromActivityIngredientList = intent.getExtras().getStringArrayList(WIDGET_LIST);
            handleActionUpdateBakingWidgets(fromActivityIngredientList);
        }

    }

    private void handleActionUpdateBakingWidgets(ArrayList<String> fromActivityIngredientsList) {
        Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE2");
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE2");
        intent.putExtra(WIDGET_LIST,fromActivityIngredientsList);
        sendBroadcast(intent);
    }
}