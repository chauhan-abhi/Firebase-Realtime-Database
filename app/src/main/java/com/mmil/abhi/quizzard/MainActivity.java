package com.mmil.abhi.quizzard;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mmil.abhi.quizzard.model.User;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {

  private MaterialEditText edtNewUserName, edtNewEmail, edtNewPassword; // for signup
  private MaterialEditText edtUserName, edtPassword; //for login

  private Button btnSignUp, btnSignIn;

  private FirebaseDatabase database;
  private DatabaseReference users;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    database = FirebaseDatabase.getInstance();
    users = database.getReference("Users");

    edtUserName = findViewById(R.id.edtUserName);
    edtPassword = findViewById(R.id.edtPassword);

    btnSignIn = findViewById(R.id.btn_sign_in);
    btnSignUp = findViewById(R.id.btn_sign_up);
    btnSignUp.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        showSignUpDialog();
      }
    });
    btnSignIn.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        signIn(edtUserName.getText().toString(), edtPassword.getText().toString());
      }
    });
  }

  private void signIn(final String user, final String pwd) {
    users.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override public void onDataChange(DataSnapshot dataSnapshot) {
        if (dataSnapshot.child(user).exists()) {
          if (!user.isEmpty()) {
            User login = dataSnapshot.child(user).getValue(User.class);
            if (login.getPassword().equals(pwd)) {
              Intent intent = new Intent(MainActivity.this, HomeScreenActivity.class);
              startActivity(intent);
              finish();
            } else {
              Toast.makeText(MainActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
            }
          } else {
            Toast.makeText(MainActivity.this, "Please enter correct user name", Toast.LENGTH_SHORT)
                .show();
          }
        } else {
          Toast.makeText(MainActivity.this, "User does not exists !", Toast.LENGTH_SHORT).show();
        }
      }

      @Override public void onCancelled(DatabaseError databaseError) {

      }
    });
  }

  private void showSignUpDialog() {
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
    alertDialog.setTitle("Sign Up");
    alertDialog.setMessage("Please fill full information");

    LayoutInflater inflater = this.getLayoutInflater();
    View sign_up_layout = inflater.inflate(R.layout.sign_up_layout, null);

    edtNewUserName = sign_up_layout.findViewById(R.id.edtNewUserName);
    edtNewEmail = sign_up_layout.findViewById(R.id.edtNewEmail);
    edtNewPassword = sign_up_layout.findViewById(R.id.edtNewPassword);

    alertDialog.setView(sign_up_layout);
    alertDialog.setIcon(R.drawable.ic_account_circle_black_24dp);

    alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
      }
    });

    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialogInterface, int i) {
        final User user =
            new User(edtNewUserName.getText().toString(), edtNewEmail.getText().toString(),
                edtNewPassword.getText().toString());

        users.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.child(user.getUserName()).exists()) {
              Toast.makeText(MainActivity.this, "User already exists !", Toast.LENGTH_SHORT).show();
            } else {
              users.child(user.getUserName()).setValue(user);
              Toast.makeText(MainActivity.this, "User registration success !", Toast.LENGTH_SHORT)
                  .show();
            }
          }

          @Override public void onCancelled(DatabaseError databaseError) {

          }
        });
        dialogInterface.dismiss();
      }
    });
    alertDialog.show();
  }
}
