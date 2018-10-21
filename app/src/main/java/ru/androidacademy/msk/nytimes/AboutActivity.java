package ru.androidacademy.msk.nytimes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    private static final String MAIL_TO_URI = "mailto";
    private RelativeLayout mainLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        startInit();
        setEmailSending();
        setLinkViews();
    }

    private void startInit() {
        mainLayout = findViewById(R.id.about_main_layout);
        getSupportActionBar().setTitle(R.string.about_title);
    }

    private void setEmailSending() {
        EditText messageEditText = findViewById(R.id.message_edit);
        Button sendMessageButton = findViewById(R.id.message_send);
        sendMessageButton.setOnClickListener(v -> {
            String message = messageEditText.getText().toString();
            if (message.isEmpty()) {
                showErrorWhenEmailMessageIncorrect();
                return;
            }
            sendEmailMessage(message);
        });
    }

    private void showErrorWhenEmailMessageIncorrect() {
        Snackbar.make(
            mainLayout,
            R.string.about_send_email_empty_error,
            Snackbar.LENGTH_SHORT
        ).show();
    }

    private void sendEmailMessage(String message) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(MAIL_TO_URI));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.about_send_email_title);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        if (emailIntent.resolveActivity(getPackageManager()) == null) {
            Snackbar.make(
                mainLayout,
                R.string.about_send_email_no_clients_error,
                Snackbar.LENGTH_SHORT
            ).show();
            return;
        }
        startActivity(emailIntent);
    }

    private void setLinkViews() {
        findViewById(R.id.icon_github).setOnClickListener(v -> {
            openUrl(getResources().getString(R.string.github_url));
        });
        findViewById(R.id.icon_linkedin).setOnClickListener(v -> {
            openUrl(getResources().getString(R.string.linkedin_url));
        });
        findViewById(R.id.icon_telegram).setOnClickListener(v -> {
            openUrl(getResources().getString(R.string.telegram_url));
        });
    }

    private void openUrl(String url) {
        Intent urlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if (urlIntent.resolveActivity(getPackageManager()) == null) {
            Snackbar.make(
                mainLayout,
                R.string.about_send_open_app_error,
                Snackbar.LENGTH_SHORT
            ).show();
            return;
        }
        startActivity(urlIntent);
    }

}
