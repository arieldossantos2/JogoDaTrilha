import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class FrontJogo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String escolha = JOptionPane.showInputDialog("Escolha a quantidade de jogadores (2 a 6):");
            int quantidadeJogadores = Integer.parseInt(escolha);

            Jogo jogo = new Jogo(quantidadeJogadores);
            Tabuleiro tabuleiro = new Tabuleiro(jogo);

            JButton btnJogarDados = new JButton("Jogar Dados");
            JLabel lblVezDoJogador = new JLabel("Vez do Jogador " + (jogo.getVezDoJogador() + 1));

            JPanel panelPrincipal = new JPanel(new BorderLayout());
            panelPrincipal.add(btnJogarDados, BorderLayout.NORTH);
            panelPrincipal.add(lblVezDoJogador, BorderLayout.WEST);
            panelPrincipal.add(tabuleiro, BorderLayout.CENTER);

            JFrame frame = new JFrame("Jogo da Trilha");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(panelPrincipal);
            frame.setSize(800, 600);
            frame.setVisible(true);

            btnJogarDados.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int jogadorIndex = jogo.getVezDoJogador();
                    btnJogarDados.setEnabled(false);
                    int dado = jogo.lancarDado();
                    JOptionPane.showMessageDialog(null, "Dado = " + dado, "Dados", JOptionPane.INFORMATION_MESSAGE);
                    jogo.realizarJogada(jogadorIndex, dado);
                    atualizarTabuleiro(tabuleiro, jogo);
                    JOptionPane.showMessageDialog(null, "Posição atual:" + jogo.getPosicaoAtual(jogadorIndex), "Jogada", JOptionPane.INFORMATION_MESSAGE);
                    jogo.setVezDoJogador();
                    lblVezDoJogador.setText("Vez do Jogador " + (jogo.getVezDoJogador() + 1));
                    btnJogarDados.setEnabled(true);
                }
            });
        });
    }

    public static void atualizarTabuleiro(Tabuleiro tabuleiro, Jogo jogo) {
        tabuleiro.moverJogador(jogo.getVezDoJogador(), jogo.getPosicaoAntiga(jogo.getVezDoJogador()), jogo.getPosicaoAtual(jogo.getVezDoJogador()));
    }
}
