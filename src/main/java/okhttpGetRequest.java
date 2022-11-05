import java.io.IOException;

public class okhttpGetRequest {

    static String url = "https://www.google.com/";

    public static void main(String[] args) throws IOException {

      sendGetRequest(url);
    }


    public static void sendGetRequest(String url) {

        newokhttp3.OkHttpClient client = new newokhttp3.OkHttpClient();

        newokhttp3.Request request = new newokhttp3.Request$Builder()
                .url(url)
                .build();

        try (newokhttp3.Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
