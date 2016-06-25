package connection;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by krrish on 25/11/2015.
 */
public class GetJson {

    public static String connect(String params) throws IOException {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        URL url = null;


        try {
            url = new URL(params);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream input = urlConnection.getInputStream();
            if (input == null) {
                return null;
            }
            StringBuffer buffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (urlConnection == null) {
                Log.e("url connection", "connection failed");
                urlConnection.disconnect();
            }
            if (reader != null) {
                reader.close();
            }
        }
    }
}
