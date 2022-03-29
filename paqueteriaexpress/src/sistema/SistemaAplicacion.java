package sistema;
import java.util.ArrayList;
import java.util.List;
public class SistemaAplicacion {
    private List<Camion> camiones;
    private List<UsuarioIdentificado> usuarios;
    private List<Pedido> pedidos;

    public List<Camion> getCamiones() {
        return this.camiones;
    }

    public void setCamiones(List<Camion> camiones) {
        this.camiones = camiones;
    }

    public List<UsuarioIdentificado> getUsuarios() {
        return this.usuarios;
    }

    public void setUsuarios(List<UsuarioIdentificado> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Pedido> getPedidos() {
        return this.pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    private double pesoMaximo;
    private double largo;
    private double ancho;
    private double alto;
    private int maxDirecciones;
    private int maxIntentos;


    public double getPesoMaximo() {
        return this.pesoMaximo;
    }

    public void setPesoMaximo(double pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
    }

    public double getLargo() {
        return this.largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getAncho() {
        return this.ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getAlto() {
        return this.alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public int getMaxDirecciones() {
        return this.maxDirecciones;
    }

    public void setMaxDirecciones(int maxDirecciones) {
        this.maxDirecciones = maxDirecciones;
    }

    public int getMaxIntentos() {
        return this.maxIntentos;
    }

    public void setMaxIntentos(int maxIntentos) {
        this.maxIntentos = maxIntentos;
    }

}
