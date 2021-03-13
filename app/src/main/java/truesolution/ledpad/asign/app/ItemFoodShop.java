package truesolution.ledpad.asign.app;


public class ItemFoodShop {
    private String cateName;
    private String shopName;
    private int image;
    private String phoneNum;
    private int infoImg;
    private boolean isCategory;

    public ItemFoodShop(String name, int image) {
        this.cateName = name;
        this.image = image;
    }

    public ItemFoodShop() {
    }

    public void setCateInfo (String name, int image){
        this.cateName = name;
        this.image = image;
        this.isCategory = true;
    }

    public void setShopInfo (String name, int image, String phoneNum, int infoImg){
        this.cateName = name;
        this.image = image;
        this.phoneNum = phoneNum;
        this.infoImg = infoImg;
        this.isCategory = false;
    }

    public String getName() {
        return cateName;
    }

    public boolean getViewStatus() {
        return isCategory;
    }

    public String getPhoneNum() {
        return phoneNum;
    }


    public void setName(String name) {
        this.cateName = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}