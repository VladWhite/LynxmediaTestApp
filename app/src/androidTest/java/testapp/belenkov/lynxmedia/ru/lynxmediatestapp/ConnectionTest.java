package testapp.belenkov.lynxmedia.ru.lynxmediatestapp;

import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.concurrent.TimeUnit;
import testapp.belenkov.lynxmedia.ru.lynxmediatestapp.helpers.ItemsApiClient;


@RunWith(AndroidJUnit4.class)
public class ConnectionTest {

    @Test
    public void getItemTest() {
        ItemsApiClient client = ItemsApiClient.getClient();
        client.getItems("cybersport")
                .test()
                .awaitDone(5, TimeUnit.SECONDS)
                .assertValue(value -> {
                    if (value != null)
                        return true;
                    return false;
                });
    }

    @Test
    public void getArticleTest() {
        ItemsApiClient client = ItemsApiClient.getClient();
        client.getArticle("/2018/08/31/san-paulu-fluminense-prognoz-na-chempionat-brazilii-02-09-2018")
                .test()
                .awaitDone(5, TimeUnit.SECONDS)
                .assertValue(value -> {
                    if (value != null)
                        return true;
                    return false;
                });
    }
}
