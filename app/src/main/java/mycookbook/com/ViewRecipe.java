package mycookbook.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.awt.font.TextAttribute;

public class ViewRecipe extends AppCompatActivity {

    private ImageView rImageView;
    private TextView recipeName, recipeIngredients, recipeDirections;
    private String id, rName, rImage, rIngredients,rDirections, rCreated, rModified;
    private Uri imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        Intent intent = getIntent();
        rName = intent.getStringExtra("RECIPE_NAME");
        rImage = intent.getStringExtra("IMAGE");
        rIngredients = intent.getStringExtra("INGREDIENTS");
        rDirections = intent.getStringExtra("DIRECTIONS");
        rCreated = intent.getStringExtra("CREATED");
        rModified = intent.getStringExtra("MODIFIED");

        rImageView = findViewById(R.id.recipeImage);
        recipeName = findViewById(R.id.recipeName);
        recipeIngredients = findViewById(R.id.ingredients);
        recipeDirections = findViewById(R.id.recipeDirections);



        recipeName.setText(rName);
        recipeIngredients.setText(rIngredients);
        recipeDirections.setText(rDirections);
        rImageView.setImageURI(Uri.parse(rImage));


        }



    }
