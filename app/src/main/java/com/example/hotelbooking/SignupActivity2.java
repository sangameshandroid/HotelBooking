package com.example.hotelbooking;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SignupActivity2 extends AppCompatActivity {
    EditText edit_firtname, edit_lastname, edit_email,edit_password, edit_address, edit_mobile, edit_city, edit_country, edit_docnum, edit_upload, edit_conpassword;
    Spinner spinner_gender, spinner_document;
    Button btn_signup, btn_upload;
    private ActivityResultLauncher<Intent> take_picture_camera;
    private ActivityResultLauncher<Intent> choose_from_gallery;
    private Uri mImageUri;
    String filename=null;
    private FirebaseAuth mAuth;
   private File capturedImage;

   ProgressBar progressBar;
   boolean passVisible;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        edit_firtname = findViewById(R.id.edit_firstname);
        edit_lastname = findViewById(R.id.edit_lastname);
        edit_email = findViewById(R.id.edit_email);
        edit_upload = findViewById(R.id.edit_upload);
        edit_address = findViewById(R.id.edit_address);
        edit_mobile = findViewById(R.id.edit_mobile);
        edit_city = findViewById(R.id.edit_city);
        edit_country = findViewById(R.id.edit_country);
        edit_conpassword = findViewById(R.id.edit_conpassword);
        edit_password = findViewById(R.id.edit_password);
        edit_docnum = findViewById(R.id.edit_docnum);
        btn_signup = findViewById(R.id.btn_signup);
        btn_upload = findViewById(R.id.btn_upload);
        progressBar = findViewById(R.id.progressBar);
        spinner_gender = findViewById(R.id.spinner_gender);
        spinner_document = findViewById(R.id.spinner_document);


        StorageReference storageref = FirebaseStorage.getInstance().getReference("DocumentImage");
        progressBar.setMax(100);

        edit_conpassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int right = 2;
                if(event.getAction()== MotionEvent.ACTION_UP){
                    if(event.getRawX()>=edit_conpassword.getRight()-edit_password.getCompoundDrawables()[right].getBounds().width()){
                        int selection = edit_conpassword.getSelectionEnd();
                        if(passVisible){
                            edit_conpassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0);
                            edit_conpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passVisible=false;
                        }
                        else{
                            edit_conpassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0);
                            edit_conpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passVisible = true;
                        }
                        edit_conpassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });


        edit_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int right = 2;
                if(event.getAction()== MotionEvent.ACTION_UP){
                    if(event.getRawX()>=edit_password.getRight()-edit_password.getCompoundDrawables()[right].getBounds().width()){
                        int selection = edit_password.getSelectionEnd();
                        if(passVisible){
                            edit_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0);
                            edit_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passVisible=false;
                        }
                        else{
                            edit_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0);
                            edit_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passVisible = true;
                        }
                        edit_password.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });




      ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.gender_spinner, new String[]{"Male", "Female", "Others","Not Selected"});
      adapter1.setDropDownViewResource(R.layout.dropdown_items);
      spinner_gender.setAdapter(adapter1);
      spinner_gender.setSelection(3);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.gender_spinner, new String[]{"Adhaar card", "Pan card", "DL","Voter ID","Not Selected"});
        adapter2.setDropDownViewResource(R.layout.dropdown_items);
        spinner_document.setAdapter(adapter2);
        spinner_document.setSelection(4);



        take_picture_camera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
            if(result.getResultCode()==RESULT_OK && result.getData()!=null){


                if(capturedImage!=null && capturedImage.exists()) {
                    filename = capturedImage.getName();
                    edit_upload.setText(filename);
                }else{
                    Toast.makeText(this, "Error taking the picture", Toast.LENGTH_SHORT).show();
                }
            }


                });

        choose_from_gallery = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                        mImageUri = result.getData().getData();
                        filename = getFileNameFromUri(mImageUri);
                        edit_upload.setText(filename);
                    }


                });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String firstname = edit_firtname.getText().toString();
                String lastname = edit_lastname.getText().toString();
                String email = edit_email.getText().toString();
                String country = edit_country.getText().toString();
                String contact = edit_mobile.getText().toString();
                String city = edit_city.getText().toString();
                String password = edit_password.getText().toString();
                String conpassword = edit_conpassword.getText().toString();
                String address = edit_address.getText().toString();
                String gender = spinner_gender.getSelectedItem().toString();
                String documenttype = spinner_document.getSelectedItem().toString();
                String imagename = edit_upload.getText().toString();



                if(!password.equals(conpassword)){
                    Toast.makeText(SignupActivity2.this, "Password Do Not Match ", Toast.LENGTH_SHORT).show();
                }

                mAuth = FirebaseAuth.getInstance();








                            StorageReference storageref = FirebaseStorage.getInstance().getReference().child("images/" + imagename);

                            Uri imageuri = Uri.fromFile(new File(edit_upload.getText().toString()));
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#C9D6CF")));
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    int progress = 0;
                    @Override
                    public void run() {
                        progressBar.setProgress(progress);
                        progress+=10;
                        if(progress<progressBar.getMax()){
                            handler.postDelayed(this, 100);
                        }

                    }
                };
                handler.postDelayed(runnable, 100);
                            storageref.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    storageref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                           mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                               @Override
                                               public void onComplete(@NonNull Task<AuthResult> task) {
                                                   progressBar.setVisibility(View.GONE);
                                                   if(task.isSuccessful()) {
                                                       FirebaseUser firebaseUser = task.getResult().getUser();
                                                       String Uid = firebaseUser.getUid();
                                                       User user = new User(firstname, lastname, gender, contact, address, city, country, documenttype, uri.toString());
                                                       FirebaseDatabase.getInstance().getReference().child("Users").child(Uid).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                           @Override
                                                           public void onSuccess(Void unused) {
                                                               Toast.makeText(SignupActivity2.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                                               startActivity(new Intent(SignupActivity2.this, LoginActivity2.class));
                                                               finish();
                                                           }
                                                       }).addOnFailureListener(new OnFailureListener() {
                                                           @Override
                                                           public void onFailure(@NonNull Exception e) {
                                                               Toast.makeText(SignupActivity2.this, "Failed to Register User", Toast.LENGTH_SHORT).show();
                                                           }
                                                       });
                                                   }
                                                   else {
                                                       // Show an error message if the user registration fails
                                                       Toast.makeText(SignupActivity2.this, "Failed to register user", Toast.LENGTH_SHORT).show();
                                                   }
                                               }
                                           });
                                        }
                                    });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignupActivity2.this, "Failed to Upload Image", Toast.LENGTH_SHORT).show();
                                }
                            });



                    }


                });












        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity2.this);
                builder.setTitle("Select Image");
                builder.setItems(new CharSequence[]{"Take Photo", "Choose from Gallery"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){
                            case 0:
                                Intent takepictureintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                File photofile = null;

                                    try {
                                        photofile = createImageFile();
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                    if (photofile != null && takepictureintent.resolveActivity(SignupActivity2.this.getPackageManager())!=null) {
                                        Uri photouri = FileProvider.getUriForFile(SignupActivity2.this, "com.example.hotelbooking.fileprovider", photofile);
                                        takepictureintent.putExtra(MediaStore.EXTRA_OUTPUT, photouri);

                                        take_picture_camera.launch(takepictureintent);
                                    }

                                break;
                            case 1:
                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                intent.setType("image/*");
                                choose_from_gallery.launch( Intent.createChooser(intent, "Select Picture"));
                                break;

                        }

                    }
                });
                builder.show();
            }
        });


    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        capturedImage = image;
        String mCurrentPhotoPath = image.getAbsolutePath();
         String name = image.getName();
        return image;
    }



    @SuppressLint("Range")
    private String getFileNameFromUri(Uri uri) {
        String result = null;
        if (uri != null) {
            if (uri.getScheme().equals("file")) {
                result = uri.getLastPathSegment();
            } else {
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                try {
                    if (cursor != null && cursor.moveToFirst()) {
                        result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } finally {
                    cursor.close();
                }
            }
        }
        return result;
    }

    private String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getMimeTypeFromExtension(cr.getType(uri));
    }




}