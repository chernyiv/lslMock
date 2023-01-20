package newokhttp3;

import okhttp3.MediaType;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class Response implements AutoCloseable {

    EasyRandom g = new EasyRandom();
    EasyRandomParameters parameters = new EasyRandomParameters().objectPoolSize(1000).randomizationDepth(10000)
            .scanClasspathForConcreteTypes(true);



    okhttp3.Response internal;

    Response(okhttp3.Response internal) { this.internal = internal; }

    public ResponseBody body() {
        okhttp3.ResponseBody r = internal.body();;
        return new ResponseBody(r.create(g.nextObject(MediaType.class), g.nextObject(String.class)));
    }

    public void close() {
        internal.close();

    }

}