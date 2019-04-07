package com.example.mq1;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChangeSettingsProfile extends AppCompatActivity implements View.OnClickListener {

    private FirebaseDatabase database;
    private DatabaseReference myRefer;
    FirebaseUser userw;
    private FirebaseAuth firebaseAuth;
    private EditText NickName;
    private Button buttonSavenickname;
    private String strNick;
    private DatabaseReference myRefer1;
    private CircleImageView AvatarView;
    private Uri imageUri;
    private String imageURI;


    StorageReference storageReference;
    private static final int IMAGE_REQUEST = 1;

    private StorageTask uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_settings_profile);
        database = FirebaseDatabase.getInstance();
        myRefer = database.getReference();
        myRefer1 = database.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        userw = firebaseAuth.getCurrentUser();
        NickName = (EditText)findViewById(R.id.NicknameCh);
        buttonSavenickname = (Button)findViewById(R.id.buttonSavenicknameCh);
        AvatarView = (CircleImageView)findViewById(R.id.avatarViewCh);
        AvatarView.setOnClickListener(this);
        buttonSavenickname.setOnClickListener(this);

        myRefer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserС user = new UserС(dataSnapshot.child("Users").child(userw.getUid()).child("imageURL").getValue().toString());
                if(user.getImageURL().equals("default")){
                    AvatarView.setImageResource(R.mipmap.ic_launcher);
                }
                else{
                    Glide.with(ChangeSettingsProfile.this).load(user.getImageURL()).into(AvatarView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == buttonSavenickname){
            strNick = NickName.getText().toString();
            myRefer1.child("Users").child(userw.getUid()).child("NickName").setValue(strNick);
            Intent intent = new Intent(this, MainLenta.class);
            startActivity(intent);

        }
        if(v == AvatarView){
            openImage();
        }
    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }
    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getApplicationContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    private void uploadImage(){
        final ProgressDialog pd = new ProgressDialog(getApplicationContext());
        pd.setMessage("Загрузка");
        // pd.show();

        if(imageUri!=null){
            final StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    +"."+getFileExtension(imageUri));
            uploadTask = fileReference.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }

                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri downloadUri = task.getResult();
                        String mUri = downloadUri.toString();

                        myRefer = FirebaseDatabase.getInstance().getReference().child("Users").child(userw.getUid());
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("imageURL",mUri);
                        myRefer.updateChildren(map);

                        pd.dismiss();
                    } else{
                        Toast.makeText(getApplicationContext(),"Ошибка",Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            });
        }
        else{
            Toast.makeText(getApplicationContext(),"Картинка не выбрана",Toast.LENGTH_SHORT).show();
            pd.dismiss();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();

            if(uploadTask!=null && uploadTask.isInProgress()){
                Toast.makeText(getApplicationContext(),"Происходит загрузка", Toast.LENGTH_SHORT).show();

            }
            else{
                uploadImage();
            }
        }

    }
}


