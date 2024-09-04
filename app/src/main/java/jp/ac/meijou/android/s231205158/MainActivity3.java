package jp.ac.meijou.android.s231205158;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Optional;

import jp.ac.meijou.android.s231205158.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {
    private ActivityMain3Binding binding;
    private PrefDataStore prefDataStore;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonSend.setOnClickListener(view -> {
             text = binding.nameForm.getText().toString();
        });

        binding.specific.setOnClickListener(view -> {
            var intent = new Intent(this, MainActivity4.class);
            if (text != null) {
                intent.putExtra("nameIntent", text);
            }
            getActivityResult.launch(intent);
        });

        binding.implicit.setOnClickListener(view -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp"));
            startActivity(intent);
        });
    }

    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch (result.getResultCode()) {
                    case RESULT_OK:
                        Optional.ofNullable(result.getData())
                                .map(data -> data.getBooleanExtra("ret", false))
                                .map(text -> "Result: " + text)
                                .ifPresent(text -> binding.resultText.setText(text));
                        break;
                    case RESULT_CANCELED:
                        binding.resultText.setText("Result: Canceled");
                        break;
                    default:
                        binding.resultText.setText("Result: Unknown(" + result.getResultCode() + ")");
                }
            }
    );
}