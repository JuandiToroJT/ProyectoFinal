package juanalcuadradosas.soc.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatosLogin {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("clave")
    private String clave = null;

    public DatosLogin(String id, String clave) {
        this.id = id;
        this.clave = clave;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
