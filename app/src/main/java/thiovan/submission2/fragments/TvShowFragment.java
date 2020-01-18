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
import thiovan.submission2.adapters.TvShowListAdapter;
import thiovan.submission2.models.TvShow;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {

    private View rootView;
    private RecyclerView rvTvShow;
    private final ArrayList<TvShow> list = new ArrayList<>();

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tv_show, container, false);

        rvTvShow = rootView.findViewById(R.id.rv_tv_show);
        rvTvShow.setHasFixedSize(true);

        list.addAll(getListTvShow());
        showRecyclerView();

        return rootView;
    }

    private ArrayList<TvShow> getListTvShow() {
        String[] dataName = getResources().getStringArray(R.array.data_tv_name);
        String[] dataScore = getResources().getStringArray(R.array.data_tv_user_score);
        String[] dataRuntime = getResources().getStringArray(R.array.data_tv_runtime);
        String[] dataDescription = getResources().getStringArray(R.array.data_tv_description);
        TypedArray dataPhoto = getResources().obtainTypedArray(R.array.data_tv_photo);

        ArrayList<TvShow> listTvShow = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++) {
            TvShow tvShow = new TvShow();
            tvShow.setPhoto(dataPhoto.getResourceId(i, -1));
            tvShow.setName(dataName[i]);
            tvShow.setScore(dataScore[i]);
            tvShow.setRuntime(dataRuntime[i]);
            tvShow.setDescription(dataDescription[i]);
            listTvShow.add(tvShow);
        }

        return listTvShow;
    }

    private void showRecyclerView() {
        rvTvShow.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        TvShowListAdapter tvShowListAdapter = new TvShowListAdapter(list);
        rvTvShow.setAdapter(tvShowListAdapter);

        tvShowListAdapter.setOnItemClickCallback(new TvShowListAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TvShow data) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_DATA_TYPE, DetailActivity.TYPE_TV_SHOW);
                intent.putExtra(DetailActivity.EXTRA_MOVIE_DETAIL, data);
                startActivity(intent);
            }
        });
    }
}
