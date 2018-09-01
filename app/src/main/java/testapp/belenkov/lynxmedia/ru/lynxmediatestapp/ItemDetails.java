package testapp.belenkov.lynxmedia.ru.lynxmediatestapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import testapp.belenkov.lynxmedia.ru.lynxmediatestapp.helpers.ItemsApiClient;
import testapp.belenkov.lynxmedia.ru.myapplication.R;

public class ItemDetails extends AppCompatActivity {

    @SuppressLint({"CheckResult", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        Objects.requireNonNull(getSupportActionBar()).hide();

        ItemsApiClient client = ItemsApiClient.getClient();
        client.getArticle(getIntent().getStringExtra("article"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            TextView team1 = findViewById(R.id.detailsTeam1);
                            TextView team2 = findViewById(R.id.detailsTeam2);
                            TextView detailsTime = findViewById(R.id.detailsTime);
                            TextView detailsTournament = findViewById(R.id.detailsTournament);
                            TextView detailsArticleText = findViewById(R.id.detailsArticleText);
                            ScrollView detailsPrediction = findViewById(R.id.detailsPrediction);
                            TextView predictionTextView = new TextView(this);
                            predictionTextView.setText(getString(R.string.prediction).concat(response.getPrediction()));

                            team1.setText(getString(R.string.team1).concat(response.getTeam1()));
                            team2.setText(getString(R.string.team2).concat(response.getTeam2()));
                            detailsTime.setText(getString(R.string.time).concat(response.getTime()));
                            detailsTournament.setText(getString(R.string.tournament).concat(response.getTournament()));
                            detailsPrediction.addView(predictionTextView);
                            detailsArticleText.setText(getString(R.string.articleText).concat(response.getArticle().get(0).getText()));
                        },
                        throwable -> Toast.makeText(this, throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show());
    }
}
