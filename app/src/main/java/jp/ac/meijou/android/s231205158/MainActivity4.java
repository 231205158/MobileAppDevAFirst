package jp.ac.meijou.android.s231205158;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import jp.ac.meijou.android.s231205158.databinding.ActivityMain4Binding;

public class MainActivity4 extends AppCompatActivity {
    private ActivityMain4Binding binding;
    private PrefDataStore prefDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String intentText = getIntent().getStringExtra("nameIntent");
        binding.textGot.setText(intentText);

        binding.okButton.setOnClickListener(view -> {
            var intent = new Intent();
            intent.putExtra("ret", true);
            setResult(RESULT_OK, intent);
            finish();
        });

        binding.cancelButton.setOnClickListener(view -> {
            var intent = new Intent();
            intent.putExtra("ret", false);
            setResult(RESULT_CANCELED, intent);
            finish();
        });
    }

    protected void onStart() {
        super.onStart();
    }

}