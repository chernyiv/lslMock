package newokhttp3;

public class OkHttpClient {

    okhttp3.OkHttpClient internal = new okhttp3.OkHttpClient();

    public newokhttp3.Call newCall(newokhttp3.Request r) {
        okhttp3.Call c = internal.newCall(r.internal);
        return new newokhttp3.Call(c);
    }

}