package testapp.belenkov.lynxmedia.ru.lynxmediatestapp;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.Objects;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import testapp.belenkov.lynxmedia.ru.lynxmediatestapp.helpers.ItemsApiClient;
import testapp.belenkov.lynxmedia.ru.lynxmediatestapp.implementation.EventItemsRecyclerAdapter;
import testapp.belenkov.lynxmedia.ru.myapplication.R;

public class MainActivity extends AppCompatActivity {
    EventItemsRecyclerAdapter adapter = new EventItemsRecyclerAdapter(this);
    RecyclerView recyclerView;

    @SuppressLint("CheckResult")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        recyclerView = findViewById(R.id.eventsItemsView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        initCategory("cybersport");

        getButtonEvent(findViewById(R.id.cyber))
                .mergeWith(getButtonEvent(findViewById(R.id.football)))
                .mergeWith(getButtonEvent(findViewById(R.id.hockey)))
                .mergeWith(getButtonEvent(findViewById(R.id.basket)))
                .mergeWith(getButtonEvent(findViewById(R.id.voley)))
                .mergeWith(getButtonEvent(findViewById(R.id.tennis)))
                .subscribe(category -> {
                            Toast.makeText(this, category, Toast.LENGTH_SHORT).show();
                            initCategory(category);
                        }
                );
    }

    @SuppressLint("CheckResult")
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initCategory(String category) {
        ItemsApiClient client = ItemsApiClient.getClient();
        client.getItems(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        it -> adapter.setEventsItems(it.getEvents()),
                        throwable -> Toast.makeText(this, throwable.getLocalizedMessage(),
                                Toast.LENGTH_SHORT).show());
    }

    private Single<String> getButtonEvent(View view) {
        return Single.create(
                subscriber -> view.setOnClickListener(_view -> {
                    ImageButton navigateButton = (ImageButton) _view;
                    subscriber.onSuccess(navigateButton.getTransitionName());
                })
        );
    }
}