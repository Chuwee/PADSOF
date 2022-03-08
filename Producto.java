public class Producto {
    private String descripcion;
    private int unidades;
    private double largo, ancho, alto;

    private int[] getInvariant(double[] arr) {
        int counter = 0;
        int array[] = new int(3);
        for (int i = 0; i < arr.length; i++){
            if(arr[i] == -1)
            {    
                array[counter] = i;
                counter++;
            }
        }
        return (counter==0)?null:array;
    }

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

    public void setDimensions(double largo, double ancho, double alto) {
        
    }


}