package Transporte;

public abstract class Camion {
    private double pesoMaximo;
    private String matricula;
    private EstadoCamion estado;

    public double getPesoMaximo() {
        return this.pesoMaximo;
    }

    public void setPesoMaximo(double pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public EstadoCamion getEstado() {
        return this.estado;
    }

    public void setEstado(EstadoCamion estado) {
        this.estado = estado;
    }
    public Camion(double pesoMaximo, String matricula, EstadoCamion estado){
        this.pesoMaximo=pesoMaximo; 
        this.matricula=matricula; 
        this.estado=estado; 
    }


}
