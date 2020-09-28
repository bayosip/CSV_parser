package com.osicorp.adebayo_osipitan.model;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.opencsv.CSVReader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataReader implements ModelInteractor {

    private static final String TAG = "DataReader";
    private int currentLine = 1;


    public static ModelInteractor getInstance(){
        return new DataReader();
    }
    private DataReader() {}

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        Log.w(TAG, "readAll: " + sb.toString() );
        return sb.toString();
    }

    @Override
    public JSONArray getJSonObjectfromFile(String uri) {
        try {
            File jsonfile = new File(uri);
            BufferedReader rd = new BufferedReader(new FileReader(jsonfile.getAbsolutePath()));
            String jsonText = readAll(rd);
            JSONArray json = new JSONArray(jsonText);
            return json;
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
                reader.skip(currentLine);
                //if((nextLine = reader.readNext() )!= null)
                {
                    while ((nextLine = reader.readNext()) != null && count <= (currentLine + 100)) {
                        // nextLine[] is an array of values from the line a
                        Car_Owners_Data cod = new Car_Owners_Data(nextLine);
                        dataList.add(cod);
                        count++;

                        Log.w(TAG, "readCsvDataFile: " + cod.dataAsList().toString());
                    }
                }
                currentLine = count;
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
}
