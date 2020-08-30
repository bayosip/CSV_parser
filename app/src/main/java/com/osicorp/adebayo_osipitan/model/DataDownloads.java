package com.osicorp.adebayo_osipitan.model;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

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
import java.util.List;

public class DataDownloads implements ModelInteractor {

    private static final String TAG = "DataDownloads";
    private DataPresenterInterface presenterInterface;

    public static ModelInteractor getInstance(DataPresenterInterface presenterInterface){
        return new DataDownloads(presenterInterface);
    }

    private DataDownloads(DataPresenterInterface presenterInterface) {
        this.presenterInterface = presenterInterface;
    }

    @Override
    public void getFilterJsonFile(String url) {

    }

    @Override
    public List<String[]> readCsvDataFile() {
        try {
            File csvfile = new File(Environment.getExternalStorageDirectory() + Constants.CSV_PATH);
            CSVReader reader = new CSVReader(new FileReader(csvfile.getAbsolutePath()));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                System.out.println(nextLine[0] + nextLine[1] + "etc...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            GenUtilities.message("The specified file was not found");
        }
        return null;
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
                        + "/download/";
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
            presenterInterface.retrieveFilterAsJson(null);
        }
    }

}
