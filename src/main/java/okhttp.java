import okhttp3.*;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import java.io.File;

public class okhttp {

    public static abstract class RequestBody {

        public abstract @Nullable
        MediaType contentType();

        public static okhttp3.RequestBody create(MediaType contentType, File file) {
            return okhttp3.RequestBody.create(contentType, file);
        }

    }

    public static class Request {

        static okhttp3.Request request;

        public Request(okhttp3.Request request) {
            Request.request = request;
        }

    }

    public static class Response {

        static okhttp3.Response response;

        public Response(okhttp3.Response response) {
            Response.response = response;
        }

        public static okhttp3.ResponseBody body() {
            return response.body();
        }

    }

    public static class Request$Builder {

        static okhttp3.Request.Builder request = new okhttp3.Request.Builder();

        public static okhttp3.Request.Builder url(String url) {
            return request.url(url);
        }

        public static void method(String method, okhttp3.RequestBody body) {
            request.method(method, body);
        }

        public static okhttp3.Request build() {
            return request.build();
        }

    }

    public static class OkHttpClient$Builder {

        static okhttp3.OkHttpClient.Builder client = new okhttp3.OkHttpClient.Builder();

        public static okhttp3.Call newCall(okhttp3.Request request) {
            okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
            return client.newCall(request);
        }

        public static void connectTimeout(Long timeout, TimeUnit unit) {
            client.connectTimeout(timeout, unit);
        }

        public static void callTimeout(Long timeout, TimeUnit unit) {
            client.callTimeout(timeout, unit);
        }

        public static void readTimeout(Long timeout, TimeUnit unit) {
            client.readTimeout(timeout, unit);
        }

    }

    public static class Call {

        static okhttp3.Call call;

        public static okhttp3.Response execute() throws IOException {
            return call.execute();
        }

        public static void cancel() {
            call.cancel();
        }

    }


}