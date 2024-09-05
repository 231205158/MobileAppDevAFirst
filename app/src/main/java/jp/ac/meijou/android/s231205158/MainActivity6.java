package jp.ac.meijou.android.s231205158;

import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.stream.Collectors;

import jp.ac.meijou.android.s231205158.databinding.ActivityMain6Binding;

public class MainActivity6 extends AppCompatActivity {
    private ActivityMain6Binding binding;
    private ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain6Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        connectivityManager = getSystemService(ConnectivityManager.class);

        var currentNetwork = connectivityManager.getActiveNetwork();

        updateTransport(currentNetwork);
        updateIpAddress(currentNetwork);

        binding.renewButton.setOnClickListener(view -> {
            connectivityManager = getSystemService(ConnectivityManager.class);

            var currentNetworkAgain = connectivityManager.getActiveNetwork();

            updateTransport(currentNetworkAgain);
            updateIpAddress(currentNetworkAgain);
        });
    }

    private void updateTransport(Network network) {
        var caps = connectivityManager.getNetworkCapabilities(network);

        if (caps != null) {
            String transport;
            if (caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                transport = "モバイル通信";
            } else if (caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                transport = "WiFi";
            } else {
                transport = "その他";
            }
            binding.transportText.setText("Transport  " + transport);
        }
    }
    private void updateIpAddress(Network network) {
        var linkProperties = connectivityManager.getLinkProperties(network);
        if (linkProperties != null) {
            var addresses = linkProperties.getLinkAddresses().stream()
                    .map(LinkAddress::toString)
                    .collect(Collectors.joining("\n"));
            binding.IPAdText.setText(addresses);
        }
    }
}