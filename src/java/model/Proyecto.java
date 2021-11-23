package model;

public class Proyecto {

    String id;
    String cod;
    String detalle;
    String usuario_id;

    public Proyecto(String id, String cod, String usuario_id, String detalle) {
        this.id = id;
        this.cod = cod;
        this.detalle = detalle;
        this.usuario_id = usuario_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

}
