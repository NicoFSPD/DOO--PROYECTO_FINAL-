package TarFinal.logica;


public class Materia {
    private String nombre:
    private int cupoMaximo;

    public Materia(String nombre, int cupoMaximo){
        this.nombre= nombre;
        this.cupoMaximo= cupoMaximo;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre= nombre;
    }

    public int getCupoMaximo(){
        return cupoMaximo;
    }
    public void setCupoMaximo(int cupoMaximo){
        this.cupoMaximo = cupoMaximo;
    }
}
