import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Tabuleiro extends JPanel {
    private List<JLabel> casasNumeradas;
    private List<JLabel> jogadoresLabels;
    private JButton btnTirarCarta;

    public Tabuleiro(Jogo jogo) {
        setLayout(new GridLayout(5, 10));

        // Inicializa as listas
        casasNumeradas = new ArrayList<>();
        jogadoresLabels = new ArrayList<>();

        // Adiciona 80 casas numeradas ao tabuleiro
        for (int i = 1; i <= 80; i++) {
            JPanel panelCasa = new JPanel(new FlowLayout());
            JLabel labelCasa = new JLabel(Integer.toString(i), SwingConstants.CENTER);
            // labelCasa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panelCasa.add(labelCasa);
            casasNumeradas.add(labelCasa);  // Adiciona a lista de casas numeradas
            add(panelCasa);
        }

        // Adiciona jogadores em suas posições iniciais
        for (int i = 0; i < jogo.getQuantidadeJogadores(); i++) {
            JLabel labelJogador = new JLabel(new ImageIcon("lib/imagem" + (i + 1) + ".png"), SwingConstants.CENTER);
            labelJogador.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            jogadoresLabels.add(labelJogador);  // Adiciona a lista de jogadores
        }


        // Posiciona os jogadores nas casas iniciais
        moverJogadoresParaPosicoesIniciais();

        // Adiciona jogadores ao tabuleiro
        for (int i = 0; i < jogo.getQuantidadeJogadores(); i++) {
            add(jogadoresLabels.get(i));
        }

        // Destaca as casas com carta extra em amarelo
        destacarCasasCartasExtras(jogo.getCasasCartasExtras());
    }

    public void moverJogador(int jogadorIndex, int posicaoAntiga, int posicaoNova) {
        // Remova o jogador da posição antiga
        JPanel panelCasaAntiga = (JPanel) getComponent(posicaoAntiga - 1);
        panelCasaAntiga.remove(jogadoresLabels.get(jogadorIndex));
    
        // Adicione o jogador na nova posição
        JPanel panelCasaNova = (JPanel) getComponent(posicaoNova - 1);
        panelCasaNova.add(jogadoresLabels.get(jogadorIndex));
    
        revalidate();
        repaint();
    }
    
    private void moverJogadoresParaPosicoesIniciais() {
        // Posiciona os jogadores nas casas iniciais
        int[] posicoesIniciais = {1, 1, 1, 1, 1, 1};  // Todos começam na primeira casa
        for (int i = 0; i < jogadoresLabels.size(); i++) {
            JPanel panelCasa = (JPanel) getComponent(posicoesIniciais[i] - 1);
            panelCasa.add(jogadoresLabels.get(i));
        }
    }

    public JButton getBtnTirarCarta() {
        return btnTirarCarta;
    }

    public void destacarCasasCartasExtras(List<Integer> casasCartasExtras) {
        for (int casa : casasCartasExtras) {
            JPanel panelCasa = (JPanel) getComponent(casa - 1);
            panelCasa.setBackground(Color.YELLOW);
        }
    }

}
