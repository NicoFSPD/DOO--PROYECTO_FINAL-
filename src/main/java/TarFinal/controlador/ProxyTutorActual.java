package TarFinal.controlador;
import TarFinal.logica.Tutor;

public class GestorSeleccion{
    private static GestorSeleccion instancia;
    private Tutor tutorSeleccionado;

    // Constructor privado (Singleton)
    private GestorSeleccion(){ //constructor

    }

    public static GestorSeleccion getInstancia(){
        if (instancia== null){
            instancia= new GestorSeleccion();
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