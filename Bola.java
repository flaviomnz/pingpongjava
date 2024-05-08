import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// CLASSE QUE REPRESENTA A BOLA NO JOGO DE PONG
public class Bola extends Rectangle {

    // GERADOR DE NÚMEROS ALEATÓRIOS PARA A DIREÇÃO INICIAL DA BOLA
    Random random;
    // VELOCIDADE ATUAL DA BOLA NOS EIXOS X E Y
    int xVelocidade;
    int yVelocidade;
    // VELOCIDADE PADRÃO DA BOLA
    int velocidadeBola = 2;

    // CONSTRUTOR DA BOLA
    Bola(int x, int y, int width, int height) {
        // CHAMA O CONSTRUTOR DA CLASSE BASE
        super(x, y, width, height);
        // INICIALIZA O GERADOR DE NÚMEROS ALEATÓRIOS
        random = new Random();
        // GERA UMA DIREÇÃO ALEATÓRIA PARA A BOLA NOS EIXOS X E Y
        int randomXDirecao = random.nextInt(2);
        if (randomXDirecao == 0)
            randomXDirecao--;
        setXDirecao(randomXDirecao * velocidadeBola);

        int randomYDirecao = random.nextInt(2);
        if (randomYDirecao == 0)
            randomYDirecao--;
        setYDirecao(randomYDirecao * velocidadeBola);
    }

    // MÉTODO PARA DEFINIR A DIREÇÃO DA BOLA NO EIXO X
    public void setXDirecao(int randomXDirecao) {
        xVelocidade = randomXDirecao;
    }

    // MÉTODO PARA DEFINIR A DIREÇÃO DA BOLA NO EIXO Y
    public void setYDirecao(int randomYDirecao) {
        yVelocidade = randomYDirecao;
    }

    // MÉTODO PARA MOVIMENTAR A BOLA
    public void movimento() {
        x += xVelocidade;
        y += yVelocidade;
    }

    // MÉTODO PARA DESENHAR A BOLA NA TELA
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, height, width);
    }
}
