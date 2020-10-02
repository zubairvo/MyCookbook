package mycookbook.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mycookbook.com.utils.UserDatabaseHelper;

public class RegistrationActivity extends AppCompatActivity {

    EditText rEmail,rPassword,rName,rMobile;
    Button buttonRegister;
    UserDatabaseHelper dbHelper;
    String email,password,name,mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        rEmail=findViewById(R.id.rEmail);
        rPassword=findViewById(R.id.rPassword);
        buttonRegister=findViewById(R.id.button_register);
        rMobile=findViewById(R.id.rMobile);
        rName = findViewById(R.id.rName);

        dbHelper= new UserDatabaseHelper(this);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = rEmail.getText().toString();
                password = rPassword.getText().toString();
                name = rName.getText().toString();
                mobile = rMobile.getText().toString();

                if (email.equals(null) && password.equals(null)&&name.equals(null)&&mobile.equals(null)) {
                    Toast.makeText(getApplicationContext(),"Please Complete Registration Form",Toast.LENGTH_LONG).show();
                } else {
                    boolean status = dbHelper.addUser(name,email,mobile,password);
                    if (status) {
                        Toast.makeText(getApplicationContext(), "Registration Successful!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Please Register your Account", Toast.LENGTH_LONG).show();

                    }
                }

            }
        });
    }
}