package TarFinal.logica;

/**
 * Clase que se utiliza para representar una materia que un tutor enseña.
 * @author Eduardo Riveros
 * @version v1.0 - 24 de junio de 2026
 */
public class Materia {
    private String nombre;
    private int cupoMaximo;

    /// Constructor.
    public Materia(String nombre, int cupoMaximo){
        this.nombre= nombre;
        this.cupoMaximo= cupoMaximo;
    }

    /// Getters y setters.
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
