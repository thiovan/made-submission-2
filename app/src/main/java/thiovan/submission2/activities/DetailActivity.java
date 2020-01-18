package thiovan.submission2.activities;

import androidx.appcompat.app.AppCompatActivity;
import thiovan.submission2.R;
import thiovan.submission2.models.Movie;
import thiovan.submission2.models.TvShow;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_DATA_TYPE = "extra_data_type";
    public static final String EXTRA_MOVIE_DETAIL = "extra_movie_detail";
    public static final String TYPE_MOVIE = "movie";
    public static final String TYPE_TV_SHOW = "tv_show";

    private ImageView mPhoto;
    private TextView mName, mScore, mRuntime, mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initView();

        switch (getIntent().getStringExtra(EXTRA_DATA_TYPE)) {
            case TYPE_MOVIE:
                Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE_DETAIL);
                setValue(
                        movie.getPhoto(),
                        movie.getName(),
                        movie.getScore(),
                        movie.getRuntime(),
                        movie.getDescription()
                );
                break;
            case TYPE_TV_SHOW:
                TvShow tvShow = getIntent().getParcelableExtra(EXTRA_MOVIE_DETAIL);
                setValue(
                        tvShow.getPhoto(),
                        tvShow.getName(),
                        tvShow.getScore(),
                        tvShow.getRuntime(),
                        tvShow.getDescription()
                );
                break;
            default:
                finish();
        }

    }

    private void setValue(int photo, String name, String score, String runtime, String description) {
        mPhoto.setImageResource(photo);
        mName.setText(name);
        mScore.setText(String.format("%s%%", score));
        mRuntime.setText(runtime);
        mDescription.setText(description);
    }

    private void initView() {
        mPhoto = findViewById(R.id.img_photo);
        mName = findViewById(R.id.txt_name);
        mScore = findViewById(R.id.txt_user_score);
        mRuntime = findViewById(R.id.txt_runtime);
        mDescription = findViewById(R.id.txt_description);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
