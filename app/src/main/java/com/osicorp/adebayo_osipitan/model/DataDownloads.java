package com.osicorp.adebayo_osipitan.model;

import android.content.ContentUris;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.opencsv.CSVReader;
import com.osicorp.adebayo_osipitan.presenter.DataPresenterInterface;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataDownloads implements ModelInteractor {

    private static final String TAG = "DataDownloads";
    private int currentLine = 1;
    public static ModelInteractor getInstance(){
        return new DataDownloads();
    }
    private DataDownloads() {}

    @Override
    public JSONObject getJSonObjectfromFile(String uri) {
        try {
            File jsonfile = new File(uri);
        }catch (Exception e) {
            e.printStackTrace();
            GenUtilities.message("The specified file was not found");
        }

        return null;
    }

    @Override
    public List<Car_Owners_Data> readCsvDataFile() {
        //checkForFiles();
        List<Car_Owners_Data> dataList = new ArrayList<>();
        if (csvDoesExistInPhone()){
            try {
                File csvfile = new File(Environment.getExternalStorageDirectory() + Constants.CSV_PATH);
                CSVReader reader = new CSVReader(new FileReader(csvfile.getAbsolutePath()));
                String[] nextLine;
                int count = currentLine;
                if ((nextLine = reader.readNext()) != null ) {
                    while ((nextLine = reader.readNext()) != null && count<=(currentLine+100)) {
                        reader.skip(currentLine);
                        // nextLine[] is an array of values from the line a
                        Car_Owners_Data cod = new Car_Owners_Data(nextLine);
                        dataList.add(cod);
                        count++;
                        Log.w(TAG, "readCsvDataFile: " + cod.dataAsList().toString());
                    }
                }
                return dataList;
            } catch (Exception e) {
                e.printStackTrace();
                GenUtilities.message("The specified file was not found");
            }
        }else {
            Log.w(TAG, "readCsvDataFile: No file found" );
            GenUtilities.message("No file found");
        }
        return null;
    }

    private boolean csvDoesExistInPhone(){
        String PATH = Environment.getExternalStorageDirectory()
                + Constants.CSV_PATH;
        Log.w(TAG, "PATH: " + PATH);
        File file = new File(PATH);
        if(file.exists()) {
            return true;
        }
        return false;
    }

    private void checkForFiles(){
        File f = Environment.getExternalStorageDirectory();
        if(f.exists()) {
            File[] files = f.listFiles();
            for (File inFile : files) {
                Log.w(TAG, "checkForFiles: " + inFile.getAbsolutePath());
            /*if (inFile.isDirectory()) {
                // is directory
            }*/
            }
        }
    }

    private class JsonTask extends AsyncTask<String, String, String> {
        File file = null;

        private JSONObject convertFileToJson(){
            JSONObject obj = null;

            return  obj;
        }
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            FileOutputStream fos = null;
            String fileName = "filter.json";

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoOutput(true);
                connection.connect();

                String PATH = Environment.getExternalStorageDirectory()
                        + "/Venten/";
                Log.d(TAG, "PATH: " + PATH);

                file = new File(PATH);
                if(!file.exists()) {
                    file.mkdirs();
                }

                File outputFile = new File(file, fileName);
                fos = new FileOutputStream(outputFile);
                InputStream is = connection.getInputStream();

                byte[] buffer = new byte[1024];
                int len1;

                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);
                }

                is.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
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
            return file != null? file.getAbsolutePath(): null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            /*if (pd.isShowing()){
                pd.dismiss();
            }
            txtJson.setText(result);*/
            //presenterInterface.retrieveFilterAsJson(null);
        }
    }

}
