package juanalcuadradosas.soc.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParametroItem {
    @JsonProperty("codigo")
    private Integer codigo = null;

    @JsonProperty("descripcion")
    private String descripcion = null;

    public ParametroItem(Integer codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
