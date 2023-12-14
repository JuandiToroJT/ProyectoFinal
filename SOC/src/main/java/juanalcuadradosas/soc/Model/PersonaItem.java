package juanalcuadradosas.soc.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonaItem {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("identificacion")
    private Long identificacion = null;

    @JsonProperty("nombre")
    private String nombre = null;

    @JsonProperty("correo")
    private String correo = null;

    @JsonProperty("area")
    private Integer area = null;

    @JsonProperty("nombreArea")
    private String nombreArea = null;

    public PersonaItem (){}

    public PersonaItem(String id, Long identificacion, String nombre, String correo, Integer area, String nombreArea) {
        this.id = id;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.correo = correo;
        this.area = area;
        this.nombreArea = nombreArea;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Long identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }
}
