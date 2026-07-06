package TarFinal.logica;

/**
 * Interfaz que define el contrato obligatorio para cualquier pantalla o componente visual
 * que necesite enterarse automáticamente cuando los datos de las reservas cambien.
 * @author Daniel López
 * @version v1.0, 6 de julio de 2026
 */
public interface ObservadorReservas {
    /**
     * Este método se ejecutará de forma automática cada vez que el
     * GestorReservas avise que una clase fue agendada, modificada o anulada.
     * Su objetivo es obligar a la pantalla a actualizar su contenido visual.
     */
    void actualizar();
}
