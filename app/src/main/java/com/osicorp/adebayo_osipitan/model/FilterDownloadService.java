package com.osicorp.adebayo_osipitan.model;

import android.app.Activity;
import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FilterDownloadService extends IntentService {

    private SharedPreferences.Editor editor;
    private SharedPreferences appPref;
    private static final String TAG = "FilterDownloadService";
    public static final String RESULT = "result";
    private int result = Activity.RESULT_CANCELED;

    public FilterDownloadService() {
        super(null);
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public FilterDownloadService(String name) {
        super("FilterDownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        downloadJson();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        GenUtilities.message("Service Created");

        appPref = GenUtilities.getAppPref();
        editor = appPref.edit();
    }

    private void downloadJson(){
        File outputFile =null;
        HttpURLConnection connection = null;
        FileOutputStream fos = null;
        String fileName = "filter.json";

        try {
            URL url = new URL("https://ven10.co/assessment/filter.json");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.connect();

            String PATH = Environment.getExternalStorageDirectory()
                    + "/Venten/";
            Log.d(TAG, "PATH: " + PATH);

            File file = new File(PATH);
            if(!file.exists()) {
                file.mkdirs();
            }

            outputFile = new File(file, fileName);
            if (outputFile.exists()) {
                outputFile.delete();
            }
            fos = new FileOutputStream(outputFile);
            InputStream is = connection.getInputStream();

            byte[] buffer = new byte[1024];
            int len1;

            while ((len1 = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
            }
            result = Activity.RESULT_OK;
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
                if(outputFile!=null && result == Activity.RESULT_OK)
                    editor.putString(Constants.FILE_KEY, outputFile.getAbsolutePath()).commit();
            }
            try {
                if (fos != null) {
                    fos.flush();
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        publishResults(outputFile.getAbsolutePath(),result);
    }

    private void publishResults(String outputPath, int result) {
        Intent intent = new Intent(Constants.DOWNLOAD_FINISHED);
        intent.putExtra(Constants.FILE_KEY, outputPath);
        intent.putExtra(RESULT, result);
        sendBroadcast(intent);
    }
}
