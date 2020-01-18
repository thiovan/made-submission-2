package thiovan.submission2.fragments;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import thiovan.submission2.R;
import thiovan.submission2.activities.DetailActivity;
import thiovan.submission2.adapters.MovieListAdapter;
import thiovan.submission2.models.Movie;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private View rootView;
    private RecyclerView rvMovie;
    private final ArrayList<Movie> list = new ArrayList<>();

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_movie, container, false);

        rvMovie = rootView.findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);

        list.addAll(getListMovie());
        showRecyclerView();

        return rootView;
    }

    private ArrayList<Movie> getListMovie() {
        String[] dataName = getResources().getStringArray(R.array.data_name);
        String[] dataScore = getResources().getStringArray(R.array.data_user_score);
        String[] dataRuntime = getResources().getStringArray(R.array.data_runtime);
        String[] dataDescription = getResources().getStringArray(R.array.data_description);
        TypedArray dataPhoto = getResources().obtainTypedArray(R.array.data_photo);

        ArrayList<Movie> listMovie = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++) {
            Movie movie = new Movie();
            movie.setPhoto(dataPhoto.getResourceId(i, -1));
            movie.setName(dataName[i]);
            movie.setScore(dataScore[i]);
            movie.setRuntime(dataRuntime[i]);
            movie.setDescription(dataDescription[i]);
            listMovie.add(movie);
        }

        return listMovie;
    }

    private void showRecyclerView() {
        rvMovie.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        MovieListAdapter movieListAdapter = new MovieListAdapter(list);
        rvMovie.setAdapter(movieListAdapter);

        movieListAdapter.setOnItemClickCallback(new MovieListAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_DATA_TYPE, DetailActivity.TYPE_MOVIE);
                intent.putExtra(DetailActivity.EXTRA_MOVIE_DETAIL, data);
                startActivity(intent);
            }
        });
    }

}
