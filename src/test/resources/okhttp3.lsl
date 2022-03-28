libsl "1.0.0";
library okhttp3 version "3.14.2" url "https://github.com/square/okhttp";

include java.util.concurrent.TimeUnit;
include java.io.File;

types {
    Request$Builder (okhttp3.Request$Builder);
    Request (okhttp3.Request);
    OkHttpClient (okhttp3.OkHttpClient);
    OkHttpClient$Builder (okhttp3.OkHttpClient$Builder);
    Call (okhttp3.OkHttpClient.Call);
    Response (okhttp3.Call.Response);
    ResponseBody (okhttp3.Response.ResponseBody);
    RequestBody (okhttp3.RequestBody);
    RealCall (okhttp3.RealCall);
    RealCall$newRealCall (okhttp3.RealCall$newRealCall);
    MediaType (okhttp3.MediaType);
    File (java.io.File);
    String (string);
    Long (int64);
    TimeUnit (java.util.concurrent.TimeUnit);
}

automaton okhttp3.RequestBody : RequestBody {
    initstate Created;

    fun create(contentType: MediaType, file: File): RequestBody;
}

automaton okhttp3.Request$Builder : Request$Builder {
    initstate Created;
    state URLSet;

    shift Created->URLSet(`url`);
    shift URLSet->self(build);

    fun `url`(`url`: String): Request$Builder
        requires urlIsNotEmpty: `url` != "";

    fun method(method: String, body: RequestBody)
        requires isMethodKnown: method = "GET" | method = "POST" | method = "HEAD" | method = "PUT" | method = "DELETE" | method = "PATCH";

    fun build(): Request;
}

/*automaton okhttp3.OkHttpClient : OkHttpClient {
    initstate Created;

    fun newCall(request: Request) : RealCall$newRealCall;
}*/

automaton okhttp3.OkHttpClient$Builder : OkHttpClient$Builder {
    initstate Created;

    fun connectTimeout(timeout: Long, unit: TimeUnit)
            requires timeoutIsPositive: timeout > 0;

    fun callTimeout(timeout: Long, unit: TimeUnit)
        requires timeoutIsPositive: timeout > 0;

    fun readTimeout(timeout: Long, unit: TimeUnit)
        requires timeoutIsPositive: timeout > 0;
}

automaton okhttp3.Call : Call {
    initstate Created;

    fun execute(): Response;
    fun cancel();
}