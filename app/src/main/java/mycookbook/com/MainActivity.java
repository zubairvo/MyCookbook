package mycookbook.com;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import mycookbook.com.utils.Adapter;
import mycookbook.com.utils.Constants;
import mycookbook.com.utils.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    ActionBar actionBar;
    RecyclerView recyclerView;
    DatabaseHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Your Recipes");

        recyclerView = findViewById(R.id.recyclerView);
        dbHelper = new DatabaseHelper(this);

        showRecipe();



        fab = findViewById(R.id.addFButton);

        fab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, AddRecipeActivity.class);
                intent.putExtra("EditRecipe", false);
                startActivity(intent);
//
//                startActivity(new Intent(MainActivity.this, AddRecipeActivity.class));
            }
        });
    }

    private void showRecipe() {

        Adapter adapter = new Adapter(MainActivity.this, dbHelper.getAllData(Constants.C_CREATED + " DESC"));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        showRecipe();
    }
}