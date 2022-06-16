import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Arrays;


public class okhttpGetRequest {

    static String url = "https://www.google.com/";

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

    public static void sendGetRequest(String url) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (newOkhttp.Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*try {
            newOkhttp.Response.response = newOkhttp.OkHttpClient$Builder.newCall(request).execute();
            String responseBody = newOkhttp.Response.response.body().string();
            System.out.println(responseBody);
            newOkhttp.Response.response.close();
        } catch (Exception e) {

        } */
    }

}
