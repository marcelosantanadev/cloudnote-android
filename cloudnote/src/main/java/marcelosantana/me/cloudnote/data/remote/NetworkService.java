package marcelosantana.me.cloudnote.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import marcelosantana.me.cloudnote.domain.Note;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Marcelo Santana on 13/11/16.
 * Contact Email: contact@marcelosantana.me and marcelo.oreen@gmail.com
 */

public interface NetworkService {

    String _BASE_URI = "https://cloudnote.firebaseio.com/v0/";

    @GET("/notes")
    Observable<List<Note>> getNotes();

    @GET("/notes/{noteId}")
    Observable<Note> getNoteById(@Path("noteId") String noteId);

    @POST("/notes")
    Observable<Response> postNote(@Body Note note);

    class Factory {
        private static final String URL = NetworkService._BASE_URI;

        private static Gson getGson() {
            return new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
        }

        public static NetworkService create() {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new Auth())
                    .readTimeout(15000, TimeUnit.MILLISECONDS)
                    .connectTimeout(15000, TimeUnit.MILLISECONDS)
                    .writeTimeout(15000, TimeUnit.MILLISECONDS)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            return retrofit.create(NetworkService.class);
        }
    }
}


