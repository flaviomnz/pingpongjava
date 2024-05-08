
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// CLASSE QUE REPRESENTA O PAINEL DO JOGO, ONDE A AÇÃO ACONTECE
public class PainelDoJogo extends JPanel implements Runnable {

    // CONSTANTES PARA DEFINIR AS DIMENSÕES DO JOGO E DOS OBJETOS
    static final int JOGO_LARGURAW = 1000;
    static final int JOGO_ALTURAH = (int) (JOGO_LARGURAW * (0.5555));
    static final Dimension TAMANHO_TELA = new Dimension(JOGO_LARGURAW, JOGO_ALTURAH);
    static final int BOLA_DIAMETRO = 20;
    static final int RAQUETE_LARGURAW = 25;
    static final int RAQUETE_ALTURAH = 100;
    private int pontuacao1Old=0, pontuacao2Old=0;

    // THREAD DO JOGO
    Thread jogoThread;
    // IMAGEM E GRÁFICOS PARA DESENHAR NA TELA
    Image image;
    Graphics graphics;
    // CLASSE ALEATÓRIA PARA GERAR NÚMEROS ALEATÓRIOS
    Random random;
    // OBJETOS DO JOGO: RAQUETES, BOLA E PLACAR
    Raquete raquete1;
    Raquete raquete2;
    Bola bola;
    Placar placar;

    // CONSTRUTOR DO PAINEL DO JOGO
    PainelDoJogo() {
        // INICIALIZA AS RAQUETES, BOLA E PLACAR
        newRaquete();
        newBola();
        placar = new Placar(JOGO_LARGURAW, JOGO_ALTURAH);
        // PERMITE QUE O PAINEL DO JOGO RECEBA O FOCO DO TECLADO
        this.setFocusable(true);
        // ADICIONA UM LISTENER PARA O TECLADO
        this.addKeyListener(new AL());
        // DEFINE O TAMANHO DO PAINEL DO JOGO
        this.setPreferredSize(TAMANHO_TELA);
        // INICIA A THREAD DO JOGO
        jogoThread = new Thread(this);
        jogoThread.start();
    }

    // MÉTODO PARA CRIAR UMA NOVA BOLA
    public void newBola() {
        bola = new Bola((JOGO_LARGURAW / 2) - (BOLA_DIAMETRO / 2), (JOGO_ALTURAH / 2) - (BOLA_DIAMETRO / 2),
                BOLA_DIAMETRO, BOLA_DIAMETRO);
    }

    // MÉTODO PARA CRIAR NOVAS RAQUETES
    public void newRaquete() {
        raquete1 = new Raquete(0, (JOGO_ALTURAH / 2) - (RAQUETE_ALTURAH / 2), RAQUETE_LARGURAW, RAQUETE_ALTURAH, 1);
        raquete2 = new Raquete(JOGO_LARGURAW - RAQUETE_LARGURAW, (JOGO_ALTURAH / 2) - (RAQUETE_ALTURAH / 2),
                RAQUETE_LARGURAW, RAQUETE_ALTURAH, 2);
    }

    // MÉTODO PARA DESENHAR NA TELA
    public void paint(Graphics g) {
        // CRIA UMA NOVA IMAGEM E UM NOVO GRÁFICO PARA DESENHAR NA IMAGEM
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        // CHAMA O MÉTODO PARA DESENHAR OS OBJETOS DO JOGO
        draw(graphics);
        // DESENHA A IMAGEM NA TELA
        g.drawImage(image, 0, 0, this);
    }

    // MÉTODO PARA DESENHAR OS OBJETOS DO JOGO
    public void draw(Graphics g) {
        raquete1.draw(g);
        raquete2.draw(g);
        bola.draw(g);
        placar.draw(g);
    }

    // MÉTODO PARA ATUALIZAR O MOVIMENTO DOS OBJETOS DO JOGO
    @SuppressWarnings("static-access")
    public void movimento() {
        raquete1.movimento();
        raquete2.movimento();
        //, (JOGO_ALTURAH / 2) - (BOLA_DIAMETRO / 2)

        if(((this.pontuacao1Old!=placar.jogador1) && (placar.jogador1!=0) && (bola.getCenterX()==(JOGO_LARGURAW / 2) - (BOLA_DIAMETRO / 2))) ||
        ((this.pontuacao2Old!=placar.jogador2) && (placar.jogador2!=0) && (bola.getCenterX()==(JOGO_LARGURAW / 2) - (BOLA_DIAMETRO / 2)))) {
            this.pontuacao1Old=placar.jogador1;
            this.pontuacao2Old=placar.jogador2;
            try {
                jogoThread.sleep(2000);
                bola.movimento();
            } catch (Exception e) {
                System.err.println("Erro: "+e.getMessage());
            }
        } else {
            bola.movimento();
        }
    }

    // MÉTODO PARA CHECAR AS COLISÕES ENTRE OS OBJETOS DO JOGO
    public void checarColisao() {
        // COLISÃO COM AS BORDAS DA TELA
        if (bola.y <= 0 || bola.y >= JOGO_ALTURAH - BOLA_DIAMETRO) {
            bola.setYDirecao(-bola.yVelocidade);
        }

        // COLISÃO COM AS RAQUETES
        if (bola.intersects(raquete1)) {
            // FAZ A BOLA MUDAR DE DIREÇÃO E AUMENTA A VELOCIDADE
            bola.xVelocidade = Math.abs(bola.xVelocidade);
            bola.xVelocidade++;
            if (bola.yVelocidade > 0)
                bola.yVelocidade++;
            else
                bola.yVelocidade--;
            bola.setXDirecao(bola.xVelocidade);
            bola.setYDirecao(bola.yVelocidade);
        }

        if (bola.intersects(raquete2)) {
            bola.xVelocidade = Math.abs(bola.xVelocidade);
            bola.xVelocidade++;
            if (bola.yVelocidade > 0)
                bola.yVelocidade++;
            else
                bola.yVelocidade--;
            bola.setXDirecao(-bola.xVelocidade);
            bola.setYDirecao(bola.yVelocidade);
        }

        // LIMITA O MOVIMENTO DAS RAQUETES DENTRO DA TELA
        raquete1.y = Math.min(Math.max(raquete1.y, 0), JOGO_ALTURAH - RAQUETE_ALTURAH);
        raquete2.y = Math.min(Math.max(raquete2.y, 0), JOGO_ALTURAH - RAQUETE_ALTURAH);

        // QUANDO UM JOGADOR PONTUA, A BOLA E AS RAQUETES SÃO RESETADAS
        if (bola.x <= 0) {
            placar.jogador2++;
            newRaquete();
            newBola();
            if(placar.jogador2==5) {
                jogoThread.interrupt();
                JOptionPane.showMessageDialog(this, "O jogador 2 ganhou!");
                placar.jogador2=0;
                placar.jogador1=0;
            }
        }

        if (bola.x >= JOGO_LARGURAW - BOLA_DIAMETRO) {
            placar.jogador1++;
            newRaquete();
            newBola();          
            if(placar.jogador1==5) {
                jogoThread.interrupt();
                JOptionPane.showMessageDialog(this, "O jogador 1 ganhou!");
                placar.jogador1=0;
                placar.jogador2=0;
            }
        }
    }

    // MÉTODO PRINCIPAL DO LOOP DO JOGO
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                movimento();
                checarColisao();
                repaint();
                delta--;
            }
        }
    }

    // CLASSE PARA LIDAR COM AS TECLAS PRESSIONADAS PELO JOGADOR
    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            raquete1.keyPressed(e);
            raquete2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            raquete1.keyReleased(e);
            raquete2.keyReleased(e);
        }
    }
}
