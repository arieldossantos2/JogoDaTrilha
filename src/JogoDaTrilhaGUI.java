import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JogoDaTrilhaGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Simule a tela inicial onde o jogador escolhe a quantidade de jogadores
            String escolha = JOptionPane.showInputDialog("Escolha a quantidade de jogadores (2 a 6):");
            int quantidadeJogadores = Integer.parseInt(escolha);

            Jogo jogo = new Jogo(quantidadeJogadores);
            Tabuleiro tabuleiro = new Tabuleiro(jogo);

            // Adiciona botão de jogar dados ao painel principal
            JButton btnJogarDados = new JButton("Jogar Dados");
            JLabel lblVezDoJogador = new JLabel("Vez do Jogador " + (jogo.getVezDoJogador()));

            // Adiciona botão de jogar dados ao painel principal
            JPanel panelPrincipal = new JPanel(new BorderLayout());
            panelPrincipal.add(btnJogarDados, BorderLayout.NORTH);
            panelPrincipal.add(lblVezDoJogador, BorderLayout.WEST);
            panelPrincipal.add(tabuleiro, BorderLayout.CENTER);

            JFrame frame = new JFrame("Jogo da Trilha");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(panelPrincipal);
            frame.setSize(800, 600);
            frame.setVisible(true);

            // Adiciona listener para habilitar o botão de jogar dados
            btnJogarDados.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int jogadorIndex = jogo.getVezDoJogador();
                    btnJogarDados.setEnabled(false);
                    int dado = jogo.lancarDado();
                    JOptionPane.showMessageDialog(null, "Dado = " + dado, "Jogada", JOptionPane.INFORMATION_MESSAGE);
                    jogo.realizarJogada(jogadorIndex, dado);
                    atualizarTabuleiro(tabuleiro, jogo);
                    jogo.setVezDoJogador();
                    lblVezDoJogador.setText("Vez do Jogador " + (jogo.getVezDoJogador() + 1));
                    btnJogarDados.setEnabled(true);
                }
            });

            
        });
        
    }
    

    private static void atualizarTabuleiro(Tabuleiro tabuleiro, Jogo jogo) {
        for (int i = 0; i < jogo.getQuantidadeJogadores(); i++) {
            tabuleiro.moverJogador(i, jogo.getPosicaoAntiga(i), jogo.getPosicaoAtual(i));
        }
    }
}
