// AUTORES
// ARIEL AUGUSTO DOS SANTOS LEITE - RA 02220134
// FELIPE NASCIMENTO DA SILVA - RA 02110325

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Tabuleiro extends JPanel {
    private List<JLabel> casasNumeradas;
    private List<JLabel> jogadoresLabels;

    public Tabuleiro(Jogo jogo) {
        setLayout(new GridLayout(5, 10));

        casasNumeradas = new ArrayList<>();
        jogadoresLabels = new ArrayList<>();

        
        for (int i = 1; i <= getNumeroDeCasas(); i++) {
            JPanel panelCasa = new JPanel(new FlowLayout());
            JLabel labelCasa = new JLabel(Integer.toString(i), SwingConstants.CENTER);
            panelCasa.add(labelCasa);
            casasNumeradas.add(labelCasa); 
            add(panelCasa);
        }

        for (int i = 0; i < jogo.getQuantidadeJogadores(); i++) {
            JLabel labelJogador = new JLabel(new ImageIcon("img/imagem" + (i + 1) + ".png"), SwingConstants.CENTER);
            jogadoresLabels.add(labelJogador); 
        }

        for (int i = 0; i < jogo.getQuantidadeJogadores(); i++) {
            add(jogadoresLabels.get(i));
        }

        destacarCasasCartasExtras(jogo.getCasasCartasExtras());
    }

    public static int getNumeroDeCasas(){
        return 50;
    }

    public void moverJogador(int jogadorIndex, int posicaoAntiga, int posicaoNova) {
        JPanel panelCasaAntiga = (JPanel) getComponent(posicaoAntiga - 1);
        panelCasaAntiga.remove(jogadoresLabels.get(jogadorIndex));

        JPanel panelCasaNova = (JPanel) getComponent(posicaoNova - 1);
        panelCasaNova.add(jogadoresLabels.get(jogadorIndex));
    
        revalidate();
        repaint();
    }

    public void destacarCasasCartasExtras(List<Integer> casasCartasExtras) {
        for (int casa : casasCartasExtras) {
            JPanel panelCasa = (JPanel) getComponent(casa - 1);
            panelCasa.setBackground(Color.YELLOW);
        }
    }

}
