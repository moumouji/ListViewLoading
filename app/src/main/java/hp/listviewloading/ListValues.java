package hp.listviewloading;

/**
 * Created by hp on 2015/7/15.
 */
public class ListValues {
    private int image;
    private String txt;

    public ListValues(int image, String txt) {
        this.image = image;
        this.txt = txt;
    }

    public void setTxt(String txt) {

        this.txt = txt;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage() {

        return image;
    }

    public String getTxt() {
        return txt;
    }

    @Override
    public String toString() {
        return "ListValues{" +
                "image=" + image +
                ", txt='" + txt + '\'' +
                '}';
    }
}
