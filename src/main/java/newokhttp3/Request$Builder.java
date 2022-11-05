package newokhttp3;

public class Request$Builder {

    okhttp3.Request.Builder internal = new okhttp3.Request.Builder();

    public Request$Builder() {
    }

    public Request$Builder url(String u) {
        internal.url(u);
        return this;
    }

    public Request build() {
        okhttp3.Request r = internal.build();
        return new Request(r);
    }

}