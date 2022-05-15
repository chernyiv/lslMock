import okhttp3.Response;

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

    public static void sendGetRequest(String url) {

        newOkhttp.OkHttpClient client = new newOkhttp.OkHttpClient();

        newOkhttp.Request request = new newOkhttp.Request.Builder()
                .url(url)
                .build();

        try (newOkhttp.Response response = client.newCall(request).execute()) {
            System.out.println(response.body());
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
