package juanalcuadradosas.soc.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import juanalcuadradosas.soc.Util.ReglasExcepcion;

public class DatosAmenaza {
    @JsonProperty("nombre")
    private String nombre = null;

    @JsonProperty("descripcion")
    private String descripcion = null;

    public DatosAmenaza(){}

    public DatosAmenaza(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void validarIntegridad(){
        if (getNombre() == null || getNombre() == "")
            throw new ReglasExcepcion("El nombre es obligatorio");
        if (getDescripcion() == null || getDescripcion() == "")
            throw new ReglasExcepcion("La descripción es obligatoria");

        if (getNombre().length() > 100)
            throw new ReglasExcepcion("La longitud del campo nombre no puede superar los 100 caracteres");
        if (getDescripcion().length() > 255)
            throw new ReglasExcepcion("La longitud del campo descripcion no puede superar los 255 caracteres");
    }
}
