import okhttp3.*;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import java.io.File;

import java.io.*;

import static java.nio.charset.Charset.forName;
import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.TypePredicates.named;
import static org.jeasy.random.TypePredicates.ofType;

public class newOkhttp {

    static public class OkHttpClient {
        okhttp3.OkHttpClient internal = new okhttp3.OkHttpClient();
        public Call newCall(Request r) {
            okhttp3.Call c = internal.newCall(r.internal);
            return new Call(c);
        }
    }

    static public class Call {
        okhttp3.Call internal;
        Call(okhttp3.Call internal) {
            this.internal = internal;
        }

        public Response execute() throws IOException {
            okhttp3.Response response = internal.execute();
            System.out.println("---------------------------");
            System.out.println(response.body().toString());
            System.out.println("---------------------------");
            //System.out.println(response.body().source().buffer().);

            EasyRandomParameters parameters = new EasyRandomParameters();
            //parameters.randomize()
            parameters.setIgnoreRandomizationErrors(true);
            parameters.scanClasspathForConcreteTypes(true);
            parameters.overrideDefaultInitialization(true);
            //parameters.excludeField(FieldPredicates.named("contentTypeString"));
            //parameters.excludeField(FieldPredicates.named("contentLength"));
            //parameters.excludeField(FieldPredicates.ofType(okhttp3.ResponseBody.class));

            EasyRandom g = new EasyRandom(parameters);

            try {
                Field field = response.getClass().getDeclaredField("body");
                field.setAccessible(true);
                okhttp3.ResponseBody responseBody = g.nextObject(okhttp3.ResponseBody.class);


                Field origContentTypeStringField = field.get(response).getClass().getDeclaredField("contentTypeString");
                origContentTypeStringField.setAccessible(true);

                //System.out.println(field.get(response).getClass());

                //Field modifiersField = Field.class.getDeclaredField("modifiers");
                //modifiersField.setAccessible(true);
                Field origContentLengthField = field.get(response).getClass().getDeclaredField("contentLength");
                origContentLengthField.setAccessible(true);
                //modifiersField.setInt(origContentTypeStringField, origContentTypeStringField.getModifiers() & ~Modifier.FINAL);
                //modifiersField.setInt(origContentLengthField, origContentLengthField.getModifiers() & ~Modifier.FINAL);
                //Field origSourceField = field.get(response).getClass().getDeclaredField("source");
                //origSourceField.setAccessible(true);

                Field contentTypeStringField = responseBody.getClass().getDeclaredField("contentTypeString");
                Field contentLengthField = responseBody.getClass().getDeclaredField("contentLength");
                //Field sourceField = responseBody.getClass().getDeclaredField("source");
                contentLengthField.setAccessible(true);
                contentTypeStringField.setAccessible(true);
                //sourceField.setAccessible(true);

                System.out.println(responseBody);
                System.out.println(responseBody.getClass());

                System.out.println("----------------- " +
                        origContentLengthField.get(field.get(response)) +
                " ----------------------------------");
                System.out.println(contentLengthField.get(responseBody));
                System.out.println(origContentTypeStringField.get(field.get(response)));
                System.out.println(contentTypeStringField.get(responseBody));

                System.out.println(responseBody);

                contentTypeStringField.set(responseBody, origContentTypeStringField.get(field.get(response)));
                contentLengthField.set(responseBody, origContentLengthField.get(field.get(response)));
                //sourceField(responseBody, origSourceField.get(field.get(response)));

                System.out.println("RESULTS: ");
                System.out.println(contentTypeStringField.get(responseBody));
                System.out.println(contentLengthField.get(responseBody));

                field.set(response, responseBody);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return new Response(response);
        }
    }

    static public class Response implements AutoCloseable {
        okhttp3.Response internal;

        Response(okhttp3.Response internal) {
            this.internal = internal;
        }

        public ResponseBody body() {
            okhttp3.ResponseBody body = internal.body();
            return new ResponseBody(body);
        }

        @Override
        public void close() {
            internal.close();
        }
    }

    static public class ResponseBody {
        okhttp3.ResponseBody internal;

        ResponseBody(okhttp3.ResponseBody internal) {
            this.internal = internal;
        }

        public String string() throws IOException {
            String r = internal.string();
            return r;
        }
    }

    static public class Request {
        okhttp3.Request internal;

        Request(okhttp3.Request internal) {
            this.internal = internal;
        }

        static public class Builder {
            okhttp3.Request.Builder internal = new okhttp3.Request.Builder();

            public Request.Builder url(String u) {
                internal.url(u);
                return this;
            }

            public Request build() {
                okhttp3.Request r = internal.build();
                return new Request(r);
            }
        }
    }
}
