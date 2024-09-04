package jp.ac.meijou.android.s231205158;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import jp.ac.meijou.android.s231205158.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {
    private ActivityMain2Binding binding;
    private PrefDataStore prefDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//
//        binding.buttonA.setOnClickListener(view -> {
//            binding.text2.setVisibility(View.GONE);
//        });
//
//        binding.buttonB.setOnClickListener(view -> {
//            binding.text3.setVisibility(View.INVISIBLE);
//        });


    }
}