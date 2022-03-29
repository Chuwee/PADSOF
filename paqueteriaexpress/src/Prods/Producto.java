package Prods;

public class Producto {
    private String descripcion;
    private int unidades;
    private double largo;
    private double ancho;
    private double alto;

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

    /* private ArrayList<Integer> getVariant(double[] arr) {
        int counter = 0;
        ArrayList<Integer> array = new ArrayList<Integer>(arr.length);
        for (int i = 0; i < arr.length; i++){
            if(arr[i] != -1)
            {    
                array.add(i);
            }
        }
        return (counter==0)?null:array;
    } */

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String desc) {
        this.descripcion = desc;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double[] getDimensions() {
        double dimensions[] = {largo, ancho, alto};
        return dimensions;
    }

   /*  public void setDimensions(double largo, double ancho, double alto) {
        double[] dims = {largo, ancho, alto};
        ArrayList<Integer> variants = getVariant(dims);
        for(Integer i : variants) {
            
        }
    } */




}