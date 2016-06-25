package saikrishna.android.com.movieinfo;


import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.io.IOException;

import Parsers.MovieParser;
import connection.GetJson;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private Imagedapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataCollect datacollect=new dataCollect();
        datacollect.execute();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_blank, container, false);
        GridView gridView= (GridView) view.findViewById(R.id.gridview);
        adapter=new Imagedapter(getActivity());
        gridView.setAdapter(adapter);
        return view;
    }
    public class dataCollect extends AsyncTask<Void,String,String> {

        @Override
        protected String doInBackground(Void... params) {
            final String FORECAST_BASE_URL =
                    "http://api.openweathermap.org/data/2.5/forecast/daily?";
            final String upcoming="upcoming";
            final String popular="popular";
            final String api = "api_key";
            final String DAYS_PARAM = "cnt";
            final String APPID_PARAM = "APPID";

            Uri builtUri = Uri.parse(MainActivity.BaseMovieUrl).buildUpon()
                    .appendPath(upcoming)
                    .appendQueryParameter(api, MainActivity.APIKEY)
                    .build();
            Log.d("uri",builtUri.toString());
            try {
                String Json_Data= GetJson.connect(builtUri.toString());
                MovieParser.Upcoming_movies(Json_Data);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }



}
