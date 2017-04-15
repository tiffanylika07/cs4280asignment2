package javabean;

public class Category {
    
    private int ID;
    private String name;
    private String imgSrc;

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getCategoryName() {
        return name;
    }

    public void setCategoryName(String aName) {
        this.name = aName;
    }


    public int getID() {
        return ID;
    }

    public void setID(int aID) {
        this.ID = aID;
    }
}
