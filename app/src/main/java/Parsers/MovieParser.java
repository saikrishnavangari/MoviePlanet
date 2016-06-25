package Parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import model.MoviesData;

/**
 * Created by krrish on 5/12/2015.
 */
public class MovieParser {
    public static ArrayList<MoviesData> Upcoming_movies(String JsonCOntent) {
        ArrayList<MoviesData> Upcoming_movies_list=new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(JsonCOntent);
            JSONArray u_movies_array = jsonObject.getJSONArray("results");
            for (int i = 0; i < u_movies_array.length(); i++) {
                MoviesData movies = new MoviesData();
                JSONObject upcomingMovie = u_movies_array.getJSONObject(i);
                movies.setId(upcomingMovie.getInt("id"));
                movies.setVote_average(upcomingMovie.getInt("vote_average"));
                movies.setVote_count(upcomingMovie.getInt("vote_count"));
                movies.setMovie_Title(upcomingMovie.getString("original_title"));
                movies.setOverview(upcomingMovie.getString("overview"));
                movies.setRelease_date(upcomingMovie.getString("release_date"));
                movies.setPoster_path(upcomingMovie.getString("poster_path"));
                movies.setBackdrop_path(upcomingMovie.getString("backdrop_path"));
                movies.setVideo(upcomingMovie.getString("video"));
                Upcoming_movies_list.add(movies);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Upcoming_movies_list;
    }

}
