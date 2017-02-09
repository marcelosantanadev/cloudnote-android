package marcelosantana.me.cloudnote.data.remote;

/**
 * Created by Marcelo Santana on 13/11/16.
 * Contact Email: contact@marcelosantana.me and marcelo.oreen@gmail.com
 */

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class Auth implements Interceptor {

    @Override
    public Response intercept(final Chain chain) throws IOException {
        Request request = chain.request();

        Headers Headers = request.headers().newBuilder().build();
        //User userEntity = CacheFactory.getInstance(AMApplication.getContext()).getUser();
//
//        if (userEntity != null && userEntity.getToken() != null) {
//            Headers = request.headers()
//                    .newBuilder()
//                    .add("authToken", userEntity.getToken())
//                    .build();
//        }


        request = request.newBuilder().headers(Headers).build();

        return chain.proceed(request);
    }
}