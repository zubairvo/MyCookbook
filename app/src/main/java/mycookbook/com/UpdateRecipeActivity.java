package mycookbook.com;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import mycookbook.com.utils.DatabaseHelper;

public class UpdateRecipeActivity extends AppCompatActivity {

    private ImageView rImageView;
    private EditText recipeName, recipeIngredients, recipeDirections;
    Button saveRecipeBtn;
    ActionBar actionBar;

    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;

    private static final int IMAGE_PICK_CAMERA_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE = 103;

    private String[] cameraPermissions;
    private String[] storagePermissions;

    private Uri imageURL;

    private String id, rName, rImage, rIngredients,rDirections, rCreated, rModified;
    private boolean editMode = false;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_recipe);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Update Recipe");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        rImageView = findViewById(R.id.recipeImage);
        recipeName = findViewById(R.id.recipeName);
        recipeIngredients = findViewById(R.id.ingredients);
        recipeDirections = findViewById(R.id.recipeDirections);

        saveRecipeBtn = findViewById(R.id.AddRecipeBtn);

        Intent intent = getIntent();
        editMode = intent.getBooleanExtra("EditRecipe", editMode);
        id = intent.getStringExtra("ID");
        rName = intent.getStringExtra("RECIPE_NAME");
        rImage = intent.getStringExtra("IMAGE");
        rIngredients = intent.getStringExtra("INGREDIENTS");
        rDirections = intent.getStringExtra("DIRECTIONS");
        rCreated = intent.getStringExtra("CREATED");
        rModified = intent.getStringExtra("MODIFIED");

        if(editMode){
            actionBar.setTitle("Update Recipe");

            editMode = intent.getBooleanExtra("EditRecipe", editMode);
            id = intent.getStringExtra("ID");
            rName = intent.getStringExtra("RECIPE_NAME");
            rImage = intent.getStringExtra("IMAGE");
            rIngredients = intent.getStringExtra("INGREDIENTS");
            rDirections = intent.getStringExtra("DIRECTIONS");
            rCreated = intent.getStringExtra("CREATED");
            rModified = intent.getStringExtra("MODIFIED");

            recipeName.setText(rName);
            recipeIngredients.setText(rIngredients);
            recipeDirections.setText(rDirections);

            if(rImage.toString().equals("null")){
                rImageView.setImageResource(R.drawable.ic_baseline_add_a_photo_24);
            }
            else {
                rImageView.setImageURI(Uri.parse(rImage));
            }


        }
        else {
            actionBar.setTitle("Add New Recipe");
        }


        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE};

        dbHelper = new DatabaseHelper(this);

        rImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                selectImage();
            }
        });

        saveRecipeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                getData();
                startActivity(new Intent(UpdateRecipeActivity.this, MainActivity.class));
                Toast.makeText(UpdateRecipeActivity.this, "RECIPE UPDATED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getData() {

        rName = "" + recipeName.getText().toString().trim();
        rIngredients = "" + recipeIngredients.getText().toString().trim();
        rDirections = "" + recipeDirections.getText().toString().trim();

        if(editMode){
            String ModifiedTime = ""+System.currentTimeMillis();

            dbHelper.updateValues(
                    ""+id,
                    ""+rName,
                    ""+imageURL,
                    ""+rIngredients,
                    ""+rDirections,
                    ""+rCreated,
                    ""+rModified

            );
        }
        else {

            String timeStamp = "" + System.currentTimeMillis();

             dbHelper.insertValues(
                    ""+rName,
                    ""+imageURL,
                    ""+rIngredients,
                    ""+rDirections,
                    ""+timeStamp,
                    ""+timeStamp
            );
        }
    }

    private void selectImage() {
        String[] options = {"Camera", "Gallery"};

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Image options");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which == 0){
                    if(!allowedCameraPermission()){
                        requestCameraAccess();
                    }
                    else{
                        pickFromCamera();
                    }

                }

                else if(which == 1){
                    if(!allowedStoragePermission()){
                        requestStorageAccess();
                    }
                    else {
                        pickFromGallery();
                    }
                }
            }
        });
        builder.create().show();

    }

    private void pickFromCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Image Title" );
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image Description" );

        imageURL = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageURL);
        startActivityForResult(cameraIntent,IMAGE_PICK_CAMERA_CODE);


    }

    private void pickFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, IMAGE_PICK_GALLERY_CODE);
    }

    private boolean allowedStoragePermission(){
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);

        return result;
    }

    private void requestStorageAccess(){

        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQUEST_CODE);
    }

    private boolean allowedCameraPermission(){
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == (PackageManager.PERMISSION_GRANTED);

        boolean resultS = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);

        return result && resultS;
    }

    private void requestCameraAccess(){

        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){

            case CAMERA_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAccepted && storageAccepted){
                        pickFromCamera();
                    }

                    else {
                        Toast.makeText(this, "Camera Access Required", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;

            case STORAGE_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if(storageAccepted){
                        pickFromGallery();
                    }
                    else {
                        Toast.makeText(this, "Storage Access Required", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == IMAGE_PICK_GALLERY_CODE){

                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(this);
            }
            else if(requestCode == IMAGE_PICK_CAMERA_CODE){
                CropImage.activity(imageURL)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(this);
            }
            else if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if(resultCode == RESULT_OK){
                    Uri resultURI = result.getUri();
                    imageURL = resultURI;
                    rImageView.setImageURI(resultURI);
                }
                else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){

                    Exception error = result.getError();
                    Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}