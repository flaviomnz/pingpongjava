import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// CLASSE QUE REPRESENTA UMA RAQUETE NO JOGO DE PONG
public class Raquete extends Rectangle {

    // IDENTIFICAÇÃO DA RAQUETE (1 OU 2)
    int id;
    // VELOCIDADE ATUAL DA RAQUETE NO EIXO Y
    int yVelocidade;
    // VELOCIDADE PADRÃO DA RAQUETE
    int velocidadeRaquete = 6;

    // CONSTRUTOR DA RAQUETE
    Raquete(int x, int y, int RAQUETE_LARGURAW, int RAQUETE_ALTURAH, int id) {
        // CHAMA O CONSTRUTOR DA CLASSE BASE
        super(x, y, RAQUETE_LARGURAW, RAQUETE_ALTURAH);
        // DEFINE O ID DA RAQUETE
        this.id = id;
    }

    // MÉTODO PARA LIDAR COM AS TECLAS PRESSIONADAS PELO JOGADOR
    public void keyPressed(KeyEvent e) {
        // CHECA QUAL RAQUETE ESTÁ SENDO CONTROLADA E MOVE-A DE ACORDO COM A TECLA
        // PRESSIONADA
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirecao(-velocidadeRaquete);
                    movimento();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirecao(velocidadeRaquete);
                    movimento();
                }
                break;

            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirecao(-velocidadeRaquete);
                    movimento();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirecao(velocidadeRaquete);
                    movimento();
                }
                break;
        }
    }

    // MÉTODO PARA LIDAR COM AS TECLAS LIBERADAS PELO JOGADOR
    public void keyReleased(KeyEvent e) {
        // PARA O MOVIMENTO DA RAQUETE QUANDO A TECLA É LIBERADA
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirecao(0);
                    movimento();
                }
                break;

            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirecao(0);
                    movimento();
                }
                break;
        }
    }

    // MÉTODO PARA DEFINIR A DIREÇÃO DO MOVIMENTO DA RAQUETE NO EIXO Y
    public void setYDirecao(int YDirecao) {
        yVelocidade = YDirecao;
    }

    // MÉTODO PARA MOVIMENTAR A RAQUETE
    public void movimento() {
        y = y + yVelocidade;
    }

    // MÉTODO PARA DESENHAR A RAQUETE NA TELA
    public void draw(Graphics g) {
        // DESENHA A RAQUETE DE ACORDO COM O ID (1 OU 2)
        if (id == 1) {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, width, height);
        } else {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, width, height);
        }
    }

}
