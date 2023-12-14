package juanalcuadradosas.soc.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import juanalcuadradosas.soc.Util.ReglasExcepcion;
import org.springframework.validation.annotation.Validated;

@Validated
public class DatosPersona extends PersonaItem {
    @JsonProperty("administrador")
    private Boolean administrador = null;

    @JsonProperty("clave")
    private String clave = null;

    public DatosPersona(){
        super();
    }

    public DatosPersona(String id, Long identificacion, String nombre, String correo, Integer area, String nombreArea, Boolean administrador, String clave) {
        super(id, identificacion, nombre, correo, area, nombreArea);
        this.administrador = administrador;
        this.clave = clave;
    }

    public Boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void validarIntegridad(){
        if (getId() == null || getId() == "")
            throw new ReglasExcepcion("El id es obligatorio");
        if (getIdentificacion() == null || getIdentificacion() == 0)
            throw new ReglasExcepcion("La cédula es obligatoria");
        if (getNombre() == null || getNombre() == "")
            throw new ReglasExcepcion("El nombre es obligatorio");
        if (getCorreo() == null || getCorreo() == "")
            setCorreo(null);
        if (getArea() == null || getArea() == 0)
            throw new ReglasExcepcion("El área es obligatoria");
        if (getAdministrador() == null)
            setAdministrador(false);
        if (getClave() == null || getClave() == "")
            throw new ReglasExcepcion("La clave es obligatoria");

        if (getId().length() > 10)
            throw new ReglasExcepcion("La longitud del campo id no puede superar los 10 caracteres");
        if (getNombre().length() > 50)
            throw new ReglasExcepcion("La longitud del campo nombre no puede superar los 50 caracteres");
        if (getCorreo() != null && getCorreo().length() > 255)
            throw new ReglasExcepcion("La longitud del campo correo no puede superar los 255 caracteres");
        if (getClave().length() > 255)
            throw new ReglasExcepcion("La longitud del campo clave no puede superar los 255 caracteres");
    }
}

