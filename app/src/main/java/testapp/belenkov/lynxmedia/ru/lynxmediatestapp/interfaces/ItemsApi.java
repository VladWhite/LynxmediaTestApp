package testapp.belenkov.lynxmedia.ru.lynxmediatestapp.interfaces;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import testapp.belenkov.lynxmedia.ru.lynxmediatestapp.model.Article;
import testapp.belenkov.lynxmedia.ru.lynxmediatestapp.model.Item;

public interface ItemsApi {
    @GET("list.php")
    Single<Item> getItems(@Query("category") String category);
    @GET("post.php")
    Single<Article> getArticle(@Query("article") String article);
}
