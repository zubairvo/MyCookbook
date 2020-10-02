package mycookbook.com.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Constants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }

    public long insertValues(String recipeName, String image, String ingredients, String directions, String dateCreated, String dateModified){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.C_RECIPE_NAME, recipeName);
        values.put(Constants.C_IMAGE, image);
        values.put(Constants.C_INGREDIENTS, ingredients);
        values.put(Constants.C_DIRECTIONS, directions);
        values.put(Constants.C_CREATED, dateCreated);
        values.put(Constants.C_MODIFIED, dateModified);

        long id = db.insert(Constants.TABLE_NAME, null, values);
        db.close();
        return id;

    }

    public void updateValues(String id, String recipeName, String image, String ingredients, String directions, String dateCreated, String dateModified){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.C_RECIPE_NAME, recipeName);
        values.put(Constants.C_IMAGE, image);
        values.put(Constants.C_INGREDIENTS, ingredients);
        values.put(Constants.C_DIRECTIONS, directions);
        values.put(Constants.C_CREATED, dateCreated);
        values.put(Constants.C_MODIFIED, dateModified);

        db.update(Constants.TABLE_NAME, values, Constants.C_ID + " = ?", new String[]{id});
        db.close();

    }

    public void deleteRecipe(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.C_ID + " = ?", new String[]{id});
        db.close();

    }

    public ArrayList<RecipeModel> getAllData(String orderBy){
        ArrayList<RecipeModel> arrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " ORDER BY " + orderBy;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToNext()){
            do{
                RecipeModel model = new RecipeModel(
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_IMAGE)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_RECIPE_NAME)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_INGREDIENTS)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_DIRECTIONS)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_CREATED)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_MODIFIED))

                );

                arrayList.add(model);

            } while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }
}