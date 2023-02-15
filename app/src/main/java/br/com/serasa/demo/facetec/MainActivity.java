package br.com.serasa.demo.facetec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;

import serasa.idf.liveness3d.android.activities.SelfieActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_action = findViewById(R.id.btn_action);
        btn_action.setOnClickListener(v -> iniciaCaptura());
    }

    private void iniciaCaptura() {
        Intent intent = new Intent(this, SelfieActivity.class);

        intent.putExtra("chave", "");// INSERT KEY HERE

        intent.putExtra("wizard", false);
        intent.putExtra("segurancaExtraRootCheck", false);
        intent.putExtra("segurancaExtraEmulatorCheck", false);
        intent.putExtra("tentativasDeCaptura", 0);


        SelfieActivity.selfieSuccessListener = selfie -> {
            Log.d("selfieSuccessListener", "file: " + selfie.getSelfie());
            Log.d("selfieSuccessListener", "sessionId: " + selfie.getSessionId());
            Log.d("selfieSuccessListener", "faceScan: " + Arrays.toString(selfie.getFaceScan()));
            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
        };

        SelfieActivity.selfieErrorListener = error -> {
            Log.d("selfieErrorListener", "code " + error.getCodigo());
            Log.d("selfieErrorListener", "description " + error.getDescricao());
            Log.d("selfieErrorListener", "sessionId " + error.getSessionId());
            Toast.makeText(this, error.getDescricao(), Toast.LENGTH_LONG).show();

        };

        startActivityForResult(intent, SelfieActivity.ACTIVITY_RESULT_CODE);
    }
}