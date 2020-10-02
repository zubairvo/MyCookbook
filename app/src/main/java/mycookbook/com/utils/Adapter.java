package mycookbook.com.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


import mycookbook.com.MainActivity;
import mycookbook.com.R;
import mycookbook.com.UpdateRecipeActivity;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private Context context;
    private ArrayList<RecipeModel> RecipeArray;
    DatabaseHelper dbHelper;

    public Adapter(Context context, ArrayList<RecipeModel> recipeArray) {
        this.context = context;
        this.RecipeArray = recipeArray;

        dbHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {

        RecipeModel model = RecipeArray.get(position);
        final String id = model.getId();
        final String image = model.getRecImage();
        final String name = model.getRecName();
        final String ingredients = model.getRecIngredients();
        final String directions = model.getRecDirections();
        final String created = model.getRecCreated();
        final String modified = model.getRecModified();

        holder.recImage.setImageURI(Uri.parse(image));
        holder.recName.setText(name);


        holder.editRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                editDialog(
                    ""+position,
                    ""+id,
                    ""+image,
                    ""+name,
                    ""+ingredients,
                    ""+directions,
                    ""+created,
                    ""+modified
                );
            }
        });

        holder.deleteRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                deleteDialog(
                        ""+id
                );
            }
        });

//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//                deleteDialog(
//                        ""+id
//                );
//
//                return false;
//            }
//        });


    }

    private void deleteDialog(final String id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete Recipe");
        builder.setMessage("Are you sure you want to delete this recipe?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_baseline_delete_forever_24);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dbHelper.deleteRecipe(id);
                ((MainActivity)context).onResume();
                Toast.makeText(context, "Recipe has been deleted!", Toast.LENGTH_SHORT).show();

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    private void editDialog(String position, final String id, final String image, final String name, final String ingredients, final String directions, final String created, final String modified) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update");
        builder.setMessage("Are you sure you want to Update this Recipe?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_baseline_edit_24);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                Intent intent = new Intent(context, UpdateRecipeActivity.class);
                intent.putExtra("ID", id);
                intent.putExtra("RECIPE_NAME", name);
                intent.putExtra("IMAGE", image);
                intent.putExtra("INGREDIENTS", ingredients);
                intent.putExtra("DIRECTIONS", directions);
                intent.putExtra("CREATED", created);
                intent.putExtra("MODIFIED", modified);
                intent.putExtra("EditRecipe", true);
                context.startActivity(intent);

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

            }
        });
        builder.create().show();

    }


    @Override
    public int getItemCount() {
        return RecipeArray.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView recImage;
        TextView recName;
        ImageButton editRecipe;
        ImageButton deleteRecipe;

        public Holder(@NonNull View itemView) {
            super(itemView);

            recImage = itemView.findViewById(R.id.RecImage);
            recName = itemView.findViewById(R.id.RecName);
            editRecipe = itemView.findViewById(R.id.editRecipeBtn);
            deleteRecipe = itemView.findViewById(R.id.deleteRecipeBtn);
        }
    }
}
