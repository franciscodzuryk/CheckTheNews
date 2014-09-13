package capturaweb.com.android.model;

public class FavoriteBean implements java.io.Serializable {

    private int id;
    private String url;

    public FavoriteBean() {
    }

    public FavoriteBean(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId(int anInt) {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl(String string) {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}