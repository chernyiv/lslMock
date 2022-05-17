libsl "1.0.0";
library okhttp3 version "3.14.2" url "https://github.com/square/newOkhttp";

include java.util.concurrent.TimeUnit;
include java.io.File;
include org.jeasy.random.EasyRandom;

types {
    Request$Builder (okhttp3.Request.Builder);
    RequestBody (okhttp3.RequestBody);
    Request (okhttp3.Request);
    OkHttpClient (okhttp3.OkHttpClient);
    OkHttpClient$Builder (okhttp3.OkHttpClient.Builder);
    Call (okhttp3.OkHttpClient.Call);
    Response (okhttp3.Call.Response);
    ResponseBody (okhttp3.Response.ResponseBody);
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

automaton okhttp3.Request : Request {
    initstate Created;


}

automaton okhttp3.Request$Builder : Request$Builder {
    initstate Created;

    fun `url`(@Save `url`: String): Request$Builder

    fun method(method: String, body: RequestBody)

    fun build(): Request;
}

/*automaton okhttp3.OkHttpClient : OkHttpClient {
    initstate Created;

    fun newCall(request: Request) : RealCall$newRealCall;
}*/

automaton okhttp3.OkHttpClient$Builder : OkHttpClient$Builder {
    initstate Created;

}

automaton okhttp3.Call : Call {
    initstate Created;

    fun execute(): Response;
    fun cancel();
}