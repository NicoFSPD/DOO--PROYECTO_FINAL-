package TarFinal.graphics;

import javax.swing.*;
import java.awt.*;

public class PanelCalendario extends JPanel {


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
}
