package jp.ac.meijou.android.s231205158;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import jp.ac.meijou.android.s231205158.databinding.ActivityMain5Binding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity5 extends AppCompatActivity {
    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);
    private ActivityMain5Binding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        var request = new Request.Builder()
//                .url("https://mura.github.io/meijou-android-sample/gist.json")
//                .build();
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                var gist = gistJsonAdapter.fromJson(response.body().source());
//
//                Optional.ofNullable(gist)
//                        .map(g -> g.files.get("OkHttp.txt"))
//                        .ifPresent(gistFile -> {
//                            runOnUiThread(() ->binding.AAText.setText(gistFile.content));
//                        });
//            }
//        });

        binding.getButton.setOnClickListener(view -> {
            var text = binding.inImageText.getText().toString();

            var url = Uri.parse("https://placehold.jp/0000dd/ffff60/500x500.png")
                    .buildUpon()
                    .appendQueryParameter("text", text)
                    .build()
                    .toString();

            getImage(url);
        });

//        var request = new Request.Builder()
//                .url("https://placehold.jp/0000dd/ffff60/" + ".png")
//                .build();
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                var bitmap = BitmapFactory.decodeStream(response.body().byteStream());
//
//                Optional.ofNullable(bitmap)
//                        .ifPresent(image -> {
//                            runOnUiThread(() ->binding.imageGot.setImageBitmap(bitmap));
//                        });
//            }
//        });
    }
    private void getImage(String url) {
        var request = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                var bitmap = BitmapFactory.decodeStream(response.body().byteStream());

                runOnUiThread(() ->binding.imageGot.setImageBitmap(bitmap));
            }
        });
    }
}