package jp.ac.meijou.android.s231205158;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import jp.ac.meijou.android.s231205158.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

//        binding.editTextText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                binding.text.setText(editable.toString());
//            }
//        });

        setContentView(binding.getRoot());
        binding.mainIcon.setImageResource(R.drawable.next);

        prefDataStore = PrefDataStore.getInstance(this);

        binding.saveButton.setOnClickListener(view -> {
            var text =binding.editTextText.getText().toString();
            prefDataStore.setString("name", text);
        });
    }

    protected void onStart() {
        super.onStart();
        prefDataStore.getString("name").ifPresent(name -> binding.text.setText(name));

    }

}