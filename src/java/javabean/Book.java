package javabean;


public class Book {
    
private int ID;
private String Book_Name;
private String Author;
private int Price;
private String Press;
private int Category_ID;
private String Description;
private int Loyalty_Point;
private String Img_File_Name;

    public String getImg_File_Name() {
        return Img_File_Name;
    }

    public void setImg_File_Name(String Img_File_Name) {
        this.Img_File_Name = Img_File_Name;
    }


    public int getID() {
        return ID;
    }

    public void setID(int aID) {
        this.ID = aID;
    }
        public String getBook_Name() {
        return Book_Name;
    }

    public void setBook_Name(String Book_Name) {
        this.Book_Name = Book_Name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public String getPress() {
        return Press;
    }

    public void setPress(String Press) {
        this.Press = Press;
    }

    public int getCategory_ID() {
        return Category_ID;
    }

    public void setCategory_ID(int Category_ID) {
        this.Category_ID = Category_ID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getLoyalty_Point() {
        return Loyalty_Point;
    }

    public void setLoyalty_Point(int Loyalty_Point) {
        this.Loyalty_Point = Loyalty_Point;
    }
}
