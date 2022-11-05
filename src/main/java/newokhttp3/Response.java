package newokhttp3;

public class Response implements AutoCloseable {

    okhttp3.Response internal;

    Response(okhttp3.Response internal) { this.internal = internal; }

    public ResponseBody body() {
        okhttp3.ResponseBody r = internal.body();
        return new ResponseBody(r);
    }

    public void close() {
        internal.close();

    }

}