package com.example.taxiapp.Utils.taxiapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.taxiapp.R;
import com.example.taxiapp.Utils.taxiapp.Services.ApiRestController;
import com.example.taxiapp.Utils.taxiapp.Services.RetrofitClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;


public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    SignInButton btnGoogleSignIn;

    GoogleSignInClient mGoogleSignInClient;

    private static final int RC_GET_TOKEN = 9002;

    private static final String TAG = "IdTokenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btnGoogleSignIn = findViewById(R.id.sign_in_button);
        btnGoogleSignIn.setOnClickListener(this);

        validateServerClientID();

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        // [END configure_signin]

        // Build GoogleAPIClient with the Google Sign-In API and the above options.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.sign_in_button:
                getIdToken();
                break;
            case 3:
                break;
        }
    }

    private void getIdToken() {
        // Show an account picker to let the user choose a Google account from the device.
        // If the GoogleSignInOptions only asks for IDToken and/or profile and/or email then no
        // consent screen will be shown here.
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_GET_TOKEN);
    }

    private void refreshIdToken() {
        // Attempt to silently refresh the GoogleSignInAccount. If the GoogleSignInAccount
        // already has a valid token this method may complete immediately.
        //
        // If the user has not previously signed in on this device or the sign-in has expired,
        // this asynchronous branch will attempt to sign in the user silently and get a valid
        // ID token. Cross-device single sign on will occur in this branch.
        mGoogleSignInClient.silentSignIn()
                .addOnCompleteListener(this, new OnCompleteListener<GoogleSignInAccount>() {
                    @Override
                    public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                        try {
                            handleSignInResult(task);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ApiException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    // [START handle_sign_in_result]
    private void handleSignInResult(@NonNull Task<GoogleSignInAccount> completedTask) throws IOException, ApiException {
        try {

            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String idToken = account.getIdToken();
            String email = account.getEmail();
            String code= account.getServerAuthCode();



            // TODO: send ID Token to server and validate

            updateUI(account);
        } catch (ApiException a) {
            ApiRestController ctrl = new ApiRestController(new RetrofitClient());
            String token = ctrl.login("admin", "pass");
            updateUI(null);
        }
    }
    // [END handle_sign_in_result]

    private void signOut() {
        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                updateUI(null);
            }
        });
    }

    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_GET_TOKEN) {
            // [START get_id_token]
            // This task is always completed immediately, there is no need to attach an
            // asynchronous listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                handleSignInResult(task);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ApiException e) {
                e.printStackTrace();
            }
            // [END get_id_token]
        }
    }

    private void updateUI(@Nullable GoogleSignInAccount account) {
        //if (account != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            finish();
        //} else {

        //}
    }

    /**
     * Validates that there is a reasonable server client ID in strings.xml, this is only needed
     * to make sure users of this sample follow the README.
     */
    private void validateServerClientID() {
        String serverClientId = getString(R.string.server_client_id);
        String suffix = ".apps.googleusercontent.com";
        if (!serverClientId.trim().endsWith(suffix)) {
            String message = "Invalid server client ID in strings.xml, must end with " + suffix;

            Log.w(TAG, message);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }

   }
}
