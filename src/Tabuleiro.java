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
    private int numeroDeCasas;

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
            JLabel labelJogador = new JLabel(new ImageIcon("lib/imagem" + (i + 1) + ".png"), SwingConstants.CENTER);
            labelJogador.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            jogadoresLabels.add(labelJogador); 
        }


        moverJogadoresParaPosicoesIniciais();

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
    
    private void moverJogadoresParaPosicoesIniciais() {
        int[] posicoesIniciais = {1, 1, 1, 1, 1, 1}; 
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
