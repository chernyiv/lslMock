/*import okhttp3.*;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import java.io.File;

public class okhttp {

	public static class RequestBody {

		public static okhttp3.RequestBody create(@Nullable MediaType contentType, @Nullable File file) {
			return okhttp3.RequestBody.create(file, contentType);
		}

	} 

	public static class Request$Builder {

		static Request.Builder request = new Request.Builder();

		public static Request.Builder url(String url) {
			return request.url(url);
		}

		public static void method(String method, okhttp3.RequestBody body) {
			request.method(method, body);
		}

		public static Request build() { 
			return request.build();
		}

	} 

	public static class OkHttpClient$Builder { 

		static OkHttpClient.Builder client = new OkHttpClient.Builder();

		public static okhttp3.Call newCall(Request request) {
			OkHttpClient client = new OkHttpClient();
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

	public static class Response {

		public okhttp3.Response body() {
			 return okhttp3.Response.body();
		}

	}


} */