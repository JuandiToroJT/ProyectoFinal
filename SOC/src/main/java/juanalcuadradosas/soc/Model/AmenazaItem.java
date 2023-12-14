package juanalcuadradosas.soc.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AmenazaItem extends DatosAmenaza {
    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("usuario")
    private String usuario = null;

    public AmenazaItem(){
        super();
    }

    public AmenazaItem(String nombre, String descripcion, Integer id, String usuario) {
        super(nombre, descripcion);
        this.id = id;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
