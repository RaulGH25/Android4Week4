package gh25.raul.raulghweek3;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class EnviarComentario extends AppCompatActivity {

    // Email address which will receive the message
    private String emailReceiverDirection;

    private String emailSenderDirection;
    private String emailSenderPassword;
    private String emailSubject;
    private String emailBody;

    private Toolbar toolbar;
    private Button buttonMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_comentario);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_footprint);

        Intent intent = getIntent();
        emailReceiverDirection = intent.getStringExtra("emailReceiverDirection");

        final TextView textSenderEmailAddress = (TextView) findViewById(R.id.editEmail);

        final TextView textSenderEmailPassword = (TextView) findViewById(R.id.editPassword);

        final TextView textSenderName = (TextView) findViewById(R.id.editTextName);

        final TextView textEmailBody = (TextView) findViewById(R.id.editTextComment);


        // Action Button

        buttonMain = (Button) findViewById(R.id.buttonMain);

        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailSenderDirection = textSenderEmailAddress.getText().toString();
                emailSenderPassword = textSenderEmailPassword.getText().toString();
                emailSubject = textSenderName.getText().toString();
                emailBody = textEmailBody.getText().toString();

                sendEmail(emailSenderDirection, emailSenderPassword, emailReceiverDirection, emailSubject, emailBody);

            }
        });



    }



    private void sendEmail(String emailSenderDirection, String emailSenderPassword, String emailReceiverDirection,
                           String  emailSubject, String emailBody){
        GMailSender m = new GMailSender(emailSenderDirection, emailSenderPassword);

        String[] toArr = {emailReceiverDirection, emailReceiverDirection};
        m.setTo(toArr);
        m.setFrom(emailSenderDirection);
        m.setSubject(emailSubject);
        m.setBody(emailBody);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            //m.addAttachment("/sdcard/filelocation");

            if(m.send()) {
                Toast.makeText(getApplicationContext(), "Email was sent successfully.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Email was not sent.", Toast.LENGTH_LONG).show();
            }
        } catch(Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("MailApp", "Could not send email", e);

        }
    }







}
