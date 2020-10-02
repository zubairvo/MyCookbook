package mycookbook.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewRecipe extends AppCompatActivity {

//    private String id, rName, rImage, rIngredients,rDirections, rCreated, rModified;
//    private ImageView rImageView;
//    private TextView recipeName, recipeIngredients, recipeDirections;
//    private boolean viewMode = false;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_recipe);
//
//        rImageView = findViewById(R.id.recipeImage);
//        recipeName = findViewById(R.id.recipeName);
//        recipeIngredients = findViewById(R.id.ingredients);
//        recipeDirections = findViewById(R.id.recipeDirections);
//
//        Intent intent = getIntent();
//        viewMode = intent.getBooleanExtra("ViewRecipe", viewMode);
//        id = intent.getStringExtra("ID");
//        rName = intent.getStringExtra("RECIPE_NAME");
//        rImage = intent.getStringExtra("IMAGE");
//        rIngredients = intent.getStringExtra("INGREDIENTS");
//        rDirections = intent.getStringExtra("DIRECTIONS");
//        rCreated = intent.getStringExtra("CREATED");
//        rModified = intent.getStringExtra("MODIFIED");
//
//        if(viewMode){
//            //actionBar.setTitle("Update Recipe");
//
//            viewMode = intent.getBooleanExtra("ViewRecipe", viewMode);
//            id = intent.getStringExtra("ID");
//            rName = intent.getStringExtra("RECIPE_NAME");
//            rImage = intent.getStringExtra("IMAGE");
//            rIngredients = intent.getStringExtra("INGREDIENTS");
//            rDirections = intent.getStringExtra("DIRECTIONS");
//            rCreated = intent.getStringExtra("CREATED");
//            rModified = intent.getStringExtra("MODIFIED");
//
//            recipeName.setText(rName);
//            recipeIngredients.setText(rIngredients);
//            recipeDirections.setText(rDirections);
//
//            if(rImage.toString().equals("null")){
//                rImageView.setImageResource(R.drawable.ic_baseline_add_a_photo_24);
//            }
//            else {
//                //rImageView.setImageURI(imageURL);
//            }
//
//
//        }
//        else {
//            //actionBar.setTitle("Add New Recipe");
//        }
//    }
}