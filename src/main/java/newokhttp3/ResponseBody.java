package newokhttp3;
import org.jeasy.random.EasyRandom;

import java.io.IOException;

public class ResponseBody {

    EasyRandom g = new EasyRandom();
    okhttp3.ResponseBody internal;

    ResponseBody(okhttp3.ResponseBody internal) { this.internal = internal; }

    public String string() throws IOException {
        String s = internal.string();
        //s = g.nextObject(String.class);
        return s;
    }

}
