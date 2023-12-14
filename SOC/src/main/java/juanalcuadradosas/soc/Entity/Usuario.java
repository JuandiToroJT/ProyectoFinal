package juanalcuadradosas.soc.Entity;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @Column(name = "idUsuario", length = 10, nullable = false, unique = true)
    private String idUsuario;

    @Column(name = "cedula", nullable = false)
    private Long cedula;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "correo")
    private String correo;

    @ManyToOne
    @JoinColumn(name = "area", nullable = false)
    private Area area;

    @Column(name = "isAdmin", nullable = false)
    private int isAdmin;

    @Column(name = "clave", nullable = false)
    private String clave;

    public Usuario() {};
    public Usuario(String idUsuario, Long cedula, String nombre, String correo, Area area, int isAdmin, String clave) {
        this.idUsuario = idUsuario;
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.area = area;
        this.isAdmin = isAdmin;
        this.clave = clave;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
