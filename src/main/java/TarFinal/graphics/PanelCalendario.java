package TarFinal.graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**Panel que representa el calendario y le agrega funcionalidad (no solo visual)
 * @author Nicolas Silva
 * @version v2.0 - 1 de Julio de 2026*/

public class PanelCalendario extends JPanel {

    private JPanel panelFechas;
    private ArrayList<JButton> fechas;

    /**Constructor de la clase*/

    public PanelCalendario(){

        setLayout(null);

        this.fechas = new ArrayList<>();

        panelFechas = new JPanel();
        panelFechas.setLayout(new GridLayout(5, 7, 0, 0));
        panelFechas.setBounds(50, 70, 700, 480);

        // traslucidez
        panelFechas.setOpaque(false);

        String[] diasJunio = {
                "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10", "11", "12",
                "13", "14", "15", "16", "17", "18", "19",
                "20", "21", "22", "23", "24", "25", "26",
                "27", "28", "29", "30", "", "", "", "", ""
        };

        //CICLO DE CREACION DE LOS BOTONES PARA LAS CASILLAS
        for (String dia : diasJunio) {

            if (dia.isEmpty()) {
                // dias que no corresponden
                panelFechas.add(new JLabel());
            } else {
                JButton btnDia = new JButton(dia);
                // traslucidez del boton

                btnDia.setOpaque(false);
                btnDia.setContentAreaFilled(false);

                // Color, fuente/tamano y listener
                btnDia.setBorderPainted(true);
                btnDia.setForeground(Color.BLACK);
                btnDia.setFont(new Font("Arial", Font.BOLD, 16));
                btnDia.addActionListener(e -> {
                    System.out.println("Día seleccionado: " + dia);
                });
                panelFechas.add(btnDia);
                this.fechas.add(btnDia);
            }
        }
        add(panelFechas);
    }

    /**Metodo paintComponent*/
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(30, 30, 740, 540);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(30, 30, 740, 540);
        g.setColor(Color.WHITE);
        g.fillRect(50, 50, 700, 500);
        g.setColor(Color.BLACK);
        g.drawRect(50, 50, 700, 500);

        for(int i=0; i < 6; i++){
            g.drawLine(50,
                    70+(96*i),
                    750,
                    70+(96*i));
        }
        for(int i=0; i<8; i++){
            g.drawLine(50+(i*100),
                    50,
                    50+(i*100),
                    550);
        }
        g.drawString("LUNES", 80, 65);
        g.drawString("MARTES", 175, 65);
        g.drawString("MIERCOLES", 265, 65);
        g.drawString("JUEVES", 375, 65);
        g.drawString("VIERNES", 470, 65);
        g.drawString("SABADO", 575, 65);
        g.drawString("DOMINGO", 670, 65);



        g.setColor(Color.BLACK);
        g.drawString("JUNIO 2026", 350, 25);
    }

    /**Getter para el arreglo con los botones de las fechas
     * @return El tipo generico con las fechas*/
    public ArrayList<JButton> getFechas() {
        return fechas;
    }
}
