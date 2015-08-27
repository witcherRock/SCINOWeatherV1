package com.ru.witcher_rock.scinoweather;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;
import com.google.gson.Gson;
import com.ru.witcher_rock.scinoweather.model.WeatherData;
import com.ru.witcher_rock.scinoweather.network.NetworkUtils;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class WeatherWidget extends AppWidgetProvider {

    final String LOG_TAG = "myLogs";

    private Context mContext;
    private AppWidgetManager mManager;
    private int[] mAddIds;

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d(LOG_TAG, "onEnabled");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d(LOG_TAG, "onUpdate " + Arrays.toString(appWidgetIds));

        mContext = context;
        mManager = appWidgetManager;
        mAddIds = appWidgetIds;

        if (NetworkUtils.isNetworkConnectedOrConnecting(context)) {
            RemoteViews widgetView = new RemoteViews(context.getPackageName(),
                    R.layout.widget);
            for(int widgetID : appWidgetIds)
                appWidgetManager.updateAppWidget(widgetID, widgetView);

            new WeatherTask().execute("Taganrog");
        }
        else
        {
            RemoteViews widgetView = new RemoteViews(context.getPackageName(),
                    R.layout.widget_no_internet);
            for(int widgetID : appWidgetIds)
                appWidgetManager.updateAppWidget(widgetID, widgetView);
        }

    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d(LOG_TAG, "onDeleted " + Arrays.toString(appWidgetIds));
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(LOG_TAG, "onDisabled");
    }

    private class WeatherTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            BufferedReader reader = null;
            HttpURLConnection urlConnection = null;

            try {
                URL url = new URL(GlobalSettings.WEATHER_API + "?q=" + params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(20000);
                urlConnection.connect();

                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                StringBuilder buf = new StringBuilder();
                String nextLine = "";

                while ((nextLine = reader.readLine()) != null) {
                    buf.append(nextLine);
                }

                return buf.toString();
            } catch (IOException e) {
                Log.e(LOG_TAG, e.toString());
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e(LOG_TAG, e.toString());
                    }
                }

                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (!TextUtils.isEmpty(result)) {
                Gson gson = new Gson();
                WeatherData weatherData = gson.fromJson(result, WeatherData.class);

                Log.d(LOG_TAG, weatherData.toString());

                RemoteViews widgetView = new RemoteViews(mContext.getPackageName(),
                        R.layout.widget);
                widgetView.setTextViewText(R.id.cityText, "Taganrog");
                widgetView.setTextViewText(R.id.cond, weatherData.getWeather().get(0).getWeatherMain());
                widgetView.setTextViewText(R.id.condDescr, " " + weatherData.getWeather().get(0).getDescription());
                widgetView.setTextViewText(R.id.temp, " " + Math.round(weatherData.getWeatherMain().getTemperature() - 273.15) + "°C");
                widgetView.setTextViewText(R.id.press, " " + weatherData.getWeatherMain().getPressure() + " hpa");
                widgetView.setTextViewText(R.id.hum, " " + weatherData.getWeatherMain().getHumidity() + "%");
                widgetView.setTextViewText(R.id.windSpeed, " " + weatherData.getWeatherWind().getSpeedWind() + " mps");
                widgetView.setTextViewText(R.id.windDeg, " " + weatherData.getWeatherWind().getDegWind() + " `");
                for(int widgetID : mAddIds)
                    mManager.updateAppWidget(widgetID, widgetView);

                new ImageTask().execute(weatherData.getWeather().get(0).getIcon() + ".png");
            }
        }
    }

    private class ImageTask extends AsyncTask<String, Void, byte[]> {

        @Override
        protected byte[] doInBackground(String... params) {
            HttpURLConnection con = null;
            InputStream is = null;
            try {
                con = (HttpURLConnection) (new URL(GlobalSettings.IMG_URL + params[0])).openConnection();
                con.setRequestMethod("GET");
                con.setDoInput(true);
                con.setDoOutput(true);
                con.connect();

                // Let's read the response
                is = con.getInputStream();
                byte[] buffer = new byte[1024];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                while (is.read(buffer) != -1)
                    baos.write(buffer);

                return baos.toByteArray();
            } catch (Throwable t) {
                t.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (Throwable t) {
                }
                try {
                    con.disconnect();
                } catch (Throwable t) {
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(byte[] result) {
            if (result != null && result.length > 0) {
                Bitmap img = BitmapFactory.decodeByteArray(result, 0, result.length);

                RemoteViews widgetView = new RemoteViews(mContext.getPackageName(),
                R.layout.widget);
                widgetView.setImageViewBitmap(R.id.condIcon, img);

                for(int widgetID : mAddIds)
                    mManager.updateAppWidget(widgetID, widgetView);
            }
        }
    }
}