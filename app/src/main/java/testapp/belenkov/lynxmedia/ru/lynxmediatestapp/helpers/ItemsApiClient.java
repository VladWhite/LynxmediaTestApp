package testapp.belenkov.lynxmedia.ru.lynxmediatestapp.helpers;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import testapp.belenkov.lynxmedia.ru.lynxmediatestapp.interfaces.ItemsApi;
import testapp.belenkov.lynxmedia.ru.lynxmediatestapp.model.Article;
import testapp.belenkov.lynxmedia.ru.lynxmediatestapp.model.Item;

public class ItemsApiClient {
    private static final String BASE_URL = "http://mikonatoruri.win/";
    private static ItemsApiClient instance;
    private ItemsApi api;

    private ItemsApiClient() {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        api = retrofit.create(ItemsApi.class);
    }

    public static ItemsApiClient getClient() {
        if (instance == null) {
            instance = new ItemsApiClient();
        }
        return instance;
    }

    public Single<Item> getItems(String category) {
        return api.getItems(category);
    }
    public Single<Article> getArticle(String article) {
        return api.getArticle(article);
    }
}
