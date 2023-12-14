package juanalcuadradosas.soc.Entity;


import javax.persistence.*;

@Entity
@Table(name = "areas")
public class Area {
    @Id
    @Column(name = "codigo", nullable = false, unique = true)
    private int codigo;

    @Column(name = "descripcion", length = 100, nullable = false)
    private String descripcion;

    public Area(){}

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
