package capturaweb.com.android.model;

public class ImageBean implements java.io.Serializable {

    private int id;
    private String ruta;
    private String detalle;

    public ImageBean() {
    }

    public ImageBean(int id, String ruta, String detalle) {
        this.id = id;
        this.ruta = ruta;
        this.detalle = detalle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}