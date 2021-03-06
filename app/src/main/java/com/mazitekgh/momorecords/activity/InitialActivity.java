
package com.mazitekgh.momorecords.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.mazitekgh.momomanager.MtnMomoManager;
import com.mazitekgh.momorecords.R;
import com.mazitekgh.momorecords.databinding.ActivityInitialBinding;

import java.text.DecimalFormat;

public class InitialActivity extends AppCompatActivity {

    private static final int SMS_PERMISION_CODE = 232;
    // private ProgressBar pb;
    //private TextView progressPercent;
    private ActivityInitialBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInitialBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // pb = findViewById(R.id.progressBar);

        //  progressPercent = findViewById(R.id.progress_percent);

        //First checking if the app is already having the permission 
        if (isSmsPermissionGranted()) {
            //app has permission so show app
            showApp();
        } else {
            //app has no permission so ask for the permission
            showDialog();
        }


    }

    private void showApp() {

        binding.progressPercent.setText("20%");
        new BackgroundLoad().execute();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Checking the request code of our request
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                // Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
                showApp();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "App cannot work without sms permission",
                        Toast.LENGTH_LONG).show();
                binding.progressBar.setVisibility(View.GONE);
                binding.progressPercent.setVisibility(View.GONE);
            }
        }
    }

    private void requestSmsPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_SMS) || ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.RECEIVE_SMS)) {
            Toast.makeText(getApplicationContext(), "Sms permission Required for app to work", Toast.LENGTH_SHORT).show();
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, SMS_PERMISION_CODE);
    }

    private boolean isSmsPermissionGranted() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
        return (result == PackageManager.PERMISSION_GRANTED);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("The app needs your Permission")
                .setMessage(getString(R.string.permission_msg) +
                        "")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestSmsPermission();
                    }
                }).show();
    }


    // FIXME: 06-Nov-20 fix this
    private class BackgroundLoad extends AsyncTask<Void, Integer, Void> {
        private Intent intent;

        @Override
        protected Void doInBackground(Void[] objects) {
            DecimalFormat df = new DecimalFormat("0.00");
            MtnMomoManager mtnMomoManager = new MtnMomoManager(InitialActivity.this);
            publishProgress(50);
            String totalReceived = df.format(mtnMomoManager.getTotalReceivedAmount());
            publishProgress(65);
            String totalSent = df.format(mtnMomoManager.getTotalSentAmount());
            publishProgress(75);
            String currentBalance = df.format(mtnMomoManager.getLatestBalance());
            publishProgress(85);
            intent = new Intent(InitialActivity.this, MainActivity.class);
            intent.putExtra("totalReceived", totalReceived);
            intent.putExtra("totalSent", totalSent);
            intent.putExtra("currentBalance", currentBalance);
            publishProgress(100);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            String s = values[0] + "%";
            binding.progressPercent.setText(s);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            startActivity(intent);
            InitialActivity.this.finish();
        }
    }

}
