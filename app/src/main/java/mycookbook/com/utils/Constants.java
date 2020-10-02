package mycookbook.com.utils;

public class Constants {

    public static final String DB_NAME = "RECIPE_DB";

    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "RECIPE_DETAILS";

    public static final String C_ID = "ID";
    public static final String C_RECIPE_NAME = "RECIPE_NAME";
    public static final String C_IMAGE = "IMAGE";
    public static final String C_INGREDIENTS = "RECIPE_INGREDIENTS";
    public static final String C_DIRECTIONS = "RECIPE_DIRECTIONS";
    public static final String C_CREATED = "DATE_CREATED";
    public static final String C_MODIFIED = "DATE_MODIFIED";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_RECIPE_NAME + " TEXT,"
            + C_IMAGE + " TEXT,"
            + C_INGREDIENTS + " TEXT,"
            + C_DIRECTIONS + " TEXT,"
            + C_CREATED + " TEXT,"
            + C_MODIFIED + " TEXT"
            + ");";


}
