package com.teenpatti;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String DOWNLOAD_URL = "https://admin.rahibet.com/letscard/rahibet_1.0.apk";
    private static final long DELAY_MILLIS = 6000; // 6 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            hideStatusBar();
        }

        setContentView(R.layout.activity_main);

        // Set up a delayed runnable to start the download after 6 seconds
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create an Intent to open a web browser with the download link
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(DOWNLOAD_URL));
                startActivity(intent);
            }
        }, DELAY_MILLIS);

        // Find the "Download Now" button
        Button downloadButton = findViewById(R.id.btnDownload);

        // Set up a click listener for the button
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Trigger the download when the button is clicked
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(DOWNLOAD_URL));
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void hideStatusBar() {
        Window window = getWindow();
        View decorView = window.getDecorView();

        // Hide the status bar and make it non-interactive
        window.setDecorFitsSystemWindows(false);
        decorView.setOnApplyWindowInsetsListener((v, insets) -> {
            insets.consumeSystemWindowInsets();
            return insets;
        });
        decorView.setOnSystemUiVisibilityChangeListener(visibility -> {
            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                // Ensure the app remains in full-screen mode
                hideStatusBar();
            }
        });

        // Hide the navigation bar if present (optional)
        int flags = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(flags);
    }
}
