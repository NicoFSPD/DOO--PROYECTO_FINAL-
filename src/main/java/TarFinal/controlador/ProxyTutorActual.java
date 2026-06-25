package TarFinal.controlador;
import TarFinal.logica.Tutor;

public class ProxyTutorActual{
    private static ProxyTutorActual instancia;
    private Tutor tutorSeleccionado;

    // Constructor privado (Singleton)
    private ProxyTutorActual(){ //constructor

    }

    public static ProxyTutorActual getInstancia(){
        if (instancia== null){
            instancia= new ProxyTutorActual();
        }
        return instancia;
    }

    public Tutor getTutorSeleccionado(){
        return tutorSeleccionado;
    }

    public void setTutorSeleccionado(Tutor tutorSeleccionado){
        this.tutorSeleccionado = tutorSeleccionado;
    }

    public boolean hayTutorSeleccionado(){
        return this.tutorSeleccionado!= null;
    }
}