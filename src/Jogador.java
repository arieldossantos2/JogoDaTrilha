// AUTORES
// ARIEL AUGUSTO DOS SANTOS LEITE - RA 02220134
// FELIPE NASCIMENTO DA SILVA - RA 02110325

import javax.swing.JOptionPane;

public class Jogador {
    private int posicaoAtual;
    private int posicaoAntiga;

    public Jogador() {
        this.posicaoAtual = 1;
        this.posicaoAntiga = 1;
    }

    public int getPosicaoAtual() {
        return posicaoAtual;
    }

    public int getPosicaoAntiga() {
        return posicaoAntiga;
    }

    public void avancar(int passos) {
        posicaoAntiga = posicaoAtual;
        posicaoAtual += passos;
        if (posicaoAtual < 1) {
            posicaoAtual = 1;
        } else if (posicaoAtual >= Tabuleiro.getNumeroDeCasas()){
            JOptionPane.showMessageDialog(null, "Parabéns, você venceu!", "Temos um vencedor!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
