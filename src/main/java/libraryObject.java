import org.jetbrains.research.libsl.LibSL;
import okhttp3.*;
import java.io.IOException;

public class libraryObject {

    static String url = "https://www.google.ru/";

    public static void main(String[] args) throws IOException {

       sendGetRequest(url);
    }

    /*public static void sendGetRequest(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = okhttp3.Request$Builder.url(url).build();
        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            System.out.println(responseBody);
        }
    }*/

    public static void sendGetRequest(String url) throws IOException {

        //RequestBody body = okhttp.RequestBody.create(null, null);

        OkHttpClient client = new OkHttpClient();
        Request request = okhttp.Request$Builder.url(url).build();
        try (Response response = okhttp.OkHttpClient$Builder.newCall(request).execute()) {
            String responseBody = response.body().string();
            System.out.println(responseBody);
        }
    }

}
