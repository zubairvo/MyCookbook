package mycookbook.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mycookbook.com.utils.UserDatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    EditText LEmail,LPassword;
    Button buttonLogin;
    UserDatabaseHelper dbHelper;
    String email,password;
    TextView registerLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LEmail = findViewById(R.id.LEmail);
        LPassword = findViewById(R.id.LPassword);
        buttonLogin = findViewById(R.id.button_login);
        registerLink = findViewById(R.id.register);

        dbHelper= new UserDatabaseHelper(this);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = LEmail.getText().toString();
                password = LPassword.getText().toString();
                if (email.equals("") && password.equals("")) {
                    Toast.makeText(getApplicationContext(),"Please Insert Login Details",Toast.LENGTH_LONG).show();
                } else {
                    int status =Integer.parseInt( dbHelper.getLoginData(email, password));
                    if (status>0) {
                        //Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Please Register your Account", Toast.LENGTH_LONG).show();

                    }
                }

            }
        });
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(i);

            }
        });
    }
}