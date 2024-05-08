import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// UMA CLASSE QUE REPRESENTA A JANELA DO JOGO
public class GameFrame extends JFrame {

    // UM PAINEL ONDE O JOGO VAI ACONTECER
    PainelDoJogo painel;

    // CONSTRUTOR DA JANELA DO JOGO
    GameFrame() {
        painel = new PainelDoJogo(); // CRIANDO O PAINEL DO JOGO
        this.add(painel); // ADICIONANDO O PAINEL À JANELA
        this.setTitle("PONG DOS CRIA"); // COLOCANDO UM TÍTULO NA JANELA
        this.setResizable(false); // IMPEDINDO QUE O JOGADOR REDIMENSIONE A JANELA
        this.setBackground(Color.BLACK); // COLOCANDO A COR DE FUNDO DO JOGO PRETO

        // DEFININDO O QUE ACONTECE QUANDO O JOGADOR CLICA NO BOTÃO DE FECHAR
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.pack(); // AJUSTANDO O TAMANHO DA JANELA PARA SE ADEQUAR AO PAINEL DO JOG
        this.setVisible(true); // TORNANDO A JANELA VISÍVEL PARA O JOGADOR
        this.setLocationRelativeTo(null); // CENTRALIZANDO A JANELA NA TELA DO JOGADOR

    }
}
