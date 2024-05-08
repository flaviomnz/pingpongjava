import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// CLASSE QUE REPRESENTA O PLACAR NO JOGO DE PONG
public class Placar extends Rectangle {
    // DIMENSÕES DO JOGO
    static int JOGO_LARGURAW;
    static int JOGO_ALTURAH;
    // PONTUAÇÃO DOS JOGADORES
    int jogador1;
    int jogador2;

    // CONSTRUTOR DO PLACAR
    Placar(int JOGO_LARGURAW, int JOGO_ALTURAH) {
        // DEFINE AS DIMENSÕES DO JOGO
        Placar.JOGO_LARGURAW = JOGO_LARGURAW;
        Placar.JOGO_ALTURAH = JOGO_ALTURAH;
    }

    // MÉTODO PARA DESENHAR O PLACAR NA TELA
    public void draw(Graphics g) {
        // CONFIGURAÇÕES DO PLACAR
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.PLAIN, 50));

        // LINHA DIVISÓRIA NO MEIO DO CAMPO
        g.drawLine(JOGO_LARGURAW / 2, 0, JOGO_LARGURAW / 2, JOGO_ALTURAH);

        // PONTUAÇÃO DOS JOGADORES
        g.drawString(String.valueOf(jogador1 / 10) + String.valueOf(jogador1 % 10), (JOGO_LARGURAW / 2) - 85, 50);
        g.drawString(String.valueOf(jogador2 / 10) + String.valueOf(jogador2 % 10), (JOGO_LARGURAW / 2) + 25, 50);
        //System.out.println(String.valueOf(jogador1 / 10) + String.valueOf(jogador1 % 10));
    }
}
