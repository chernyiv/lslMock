libsl "1.0.0";
library okhttp3 version "3.14.2" url "https://github.com/square/okhttp";

include java.util.concurrent.TimeUnit;
include java.io.File;

types {
    Request$Builder (okhttp3.Request.Builder);
    RequestBody (okhttp3.RequestBody);
    Request (okhttp3.Request);
    OkHttpClient (okhttp3.OkHttpClient);
    OkHttpClient$Builder (okhttp3.OkHttpClient$Builder);
    Call (okhttp3.OkHttpClient.Call);
    Response (okhttp3.Call.Response);
    ResponseBody (okhttp3.ResponseBody);
    File (java.io.File);
    String (string);
    Long (int64);
    TimeUnit (java.util.concurrent.TimeUnit);
}

automaton okhttp3.OkHttpClient : OkHttpClient {
    initstate Created;

    fun newCall(r: Request): Call;
}

automaton okhttp3.Call : Call {
    initstate Created;

    fun execute(): Response;
}

automaton okhttp3.Response : Response {
    initstate Created;

    fun body(): ResponseBody;
    fun close();
}

automaton okhttp3.ResponseBody : ResponseBody {
    initstate Created;

    fun string(): String;
}

automaton okhttp3.Request : Request {
    initstate Created;

}

automaton okhttp3.Request.Builder : Request$Builder {
    initstate Created;

    fun `url`(u: String): Request$Builder;
    fun build(): Request;
}



