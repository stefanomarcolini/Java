package ***.***.***.logbook;

//import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;

public class MainActivity extends AppCompatActivity {

    //private TextView mAppName;

    private EditText mEmail;
    private EditText mPwd;

    private FirebaseAuth mAuth;

    private final String TAG = "> logbook: ";

    //======================================================//
    //  ON-CREATE:                                          //
    //======================================================//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // mAppName = findViewById(R.id.txt_app_name);
        mEmail = findViewById(R.id.edt_email);
        mPwd = findViewById(R.id.edt_pwd);

        Button mSignUp = findViewById(R.id.b_signup);
        Button mLogin = findViewById(R.id.b_login);
        Button mLogout = findViewById(R.id.b_logout);

        mAuth = FirebaseAuth.getInstance();

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn();
            }
        });

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });

    }

    //======================================================//
    //  ON-START:                                           //
    //======================================================//

    @Override
    public void onStart() {
        super.onStart();
        /*  Check if user is signed in
         *  (not null) and update UI accordingly
         */
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    //======================================================//
    //  CLICK EVENTS:                                       //
    //======================================================//

    private void signUp() {
        String email = mEmail.getText().toString();
        String password = mPwd.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
             .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                        //  Sign in success, updateUI with signed-in user's information
                        Log.d(TAG, "createUserWithEmail: success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        //  if sign-in fails, display a message to the user
                        Log.w(TAG, "cretaeUserWithEmail: failure", task.getException());
                        Toast.makeText(MainActivity.this, "Authentication failed",
                                        Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
            }
        });
    }

    private void logIn() {
        String email = mEmail.getText().toString();
        String password = mPwd.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
             .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        //  login success, update UI with signed-in user's information
                        Log.d(TAG, "signInWithEmail: success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        //  if login fails, display a message to the user
                        Log.w(TAG, "signInWithEmail: failure", task.getException());
                        Toast.makeText(MainActivity.this, "Authentication failed",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
            }
        });
    }

    private void logOut() {
        mAuth.signOut();
        Log.d(TAG, userInfo());
        Toast.makeText(MainActivity.this, userInfo(), Toast.LENGTH_SHORT).show();
    }

    //======================================================//
    //  HELPER METHODS:                                     //
    //======================================================//

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            System.out.println(userInfo());
        }
    }

    private String userInfo() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            //  Name, email address, and profile photo url
            String name = user.getDisplayName();
            String email = user.getEmail();
            //Uri photoUrl = user.getPhotoUrl();

            //  check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            /*
            * the user' ID, unique to the Firebase project. Do NOT use this value
            * to authenticate with your backend server, if you have one. Use
            * FirebaseUser.getToken() instead
            * */
            String uid = user.getUid();

            return "User name: " + name +
                    "\nID: " + uid +
                    "\nemail: " + email +
                    "\nverified email: " + emailVerified +
                    "\nis logged in!";
        } else {
            return "Logged out!";
        }
    }
}
