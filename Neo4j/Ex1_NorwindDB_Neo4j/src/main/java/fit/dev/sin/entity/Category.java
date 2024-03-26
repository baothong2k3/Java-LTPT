package fit.dev.sin.entity;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("description")
    private String description;
    @SerializedName("categoryName")
    private String categoryName;
    @SerializedName("categoryID")
    private String categoryID;

    public Category(String description, String categoryName, String categoryID) {
        this.description = description;
        this.categoryName = categoryName;
        this.categoryID = categoryID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public String toString() {
        return "Category{" +
                "description='" + description + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryID='" + categoryID + '\'' +
                '}';
    }
}
