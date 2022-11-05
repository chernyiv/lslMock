package newokhttp3;

import java.io.IOException;

public class Call {

    okhttp3.Call internal;

    Call(okhttp3.Call internal) { this.internal = internal; }

    public newokhttp3.Response execute() throws IOException {
        okhttp3.Response r = internal.execute();
        return new newokhttp3.Response(r);
    }

}