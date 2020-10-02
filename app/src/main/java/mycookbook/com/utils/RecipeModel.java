package mycookbook.com.utils;

public class RecipeModel {
    String id, RecImage, RecName, RecIngredients, RecDirections, RecCreated, RecModified;

    public RecipeModel(String id, String recImage, String recName, String recIngredients, String recDirections, String recCreated, String recModified) {
        this.id = id;
        RecImage = recImage;
        RecName = recName;
        RecIngredients = recIngredients;
        RecDirections = recDirections;
        RecCreated = recCreated;
        RecModified = recModified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecImage() {
        return RecImage;
    }

    public void setRecImage(String recImage) {
        RecImage = recImage;
    }

    public String getRecName() {
        return RecName;
    }

    public void setRecName(String recName) {
        RecName = recName;
    }

    public String getRecIngredients() {
        return RecIngredients;
    }

    public void setRecIngredients(String recIngredients) {
        RecIngredients = recIngredients;
    }

    public String getRecDirections() {
        return RecDirections;
    }

    public void setRecDirections(String recDirections) {
        RecDirections = recDirections;
    }

    public String getRecCreated() {
        return RecCreated;
    }

    public void setRecCreated(String recCreated) {
        RecCreated = recCreated;
    }

    public String getRecModified() {
        return RecModified;
    }

    public void setRecModified(String recModified) {
        RecModified = recModified;
    }
}
