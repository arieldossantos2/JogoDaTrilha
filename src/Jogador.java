
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
        }
    }
}
