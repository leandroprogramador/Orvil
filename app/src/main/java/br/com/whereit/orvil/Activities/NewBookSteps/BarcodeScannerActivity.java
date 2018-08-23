package br.com.whereit.orvil.Activities.NewBookSteps;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.List;

import br.com.whereit.orvil.R;

public class BarcodeScannerActivity extends AppCompatActivity implements DecoratedBarcodeView.TorchListener {

    private CaptureManager captureManager;
    private DecoratedBarcodeView decoratedBarcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);
        decoratedBarcodeView = findViewById(R.id.zxing_barcode_scanner);

        decoratedBarcodeView.setTorchListener(this);

        captureManager = new CaptureManager(this, decoratedBarcodeView);
        captureManager.initializeFromIntent(getIntent(), savedInstanceState);
        captureManager.decode();

        decoratedBarcodeView.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("isbn",result.getText());
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {

            }
        });


    }

    @Override
    public void onTorchOn() {

    }

    @Override
    public void onTorchOff() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        captureManager.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return decoratedBarcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }
}
