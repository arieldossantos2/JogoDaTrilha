import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

public class Jogo {
    private List<Jogador> jogadores;
    private List<Integer> cartasExtras;
    private List<Integer> casasCartasExtras;
    private int vezDoJogador;

    public Jogo(int quantidadeJogadores) {
        jogadores = new ArrayList<>();
        cartasExtras = new ArrayList<>();
        casasCartasExtras = new ArrayList<>();

        for (int i = 0; i < quantidadeJogadores; i++) {
            jogadores.add(new Jogador());
        }

        inicializarCartas();

        definirCasasCartasExtras(8); 

        vezDoJogador = 0;
    }

    private void inicializarCartas() {
        for (int i = -2; i <= 3; i++) {
            cartasExtras.add(i);
        }

        embaralharCartas();
    }

    private void embaralharCartas() {
        Collections.shuffle(cartasExtras);
    }

    private void definirCasasCartasExtras(int quantidadeCasas) {
        Random random = new Random();
        for (int i = 1; i < quantidadeCasas; i++) {
            int casa = random.nextInt(50) + 1;
            casasCartasExtras.add(casa);
        }
    }

    public void realizarJogada(int jogadorIndex, int passos) {
        Jogador jogador = jogadores.get(jogadorIndex);
        jogador.avancar(passos);
        int posicaoNova = jogador.getPosicaoAtual();
    
        if (casasCartasExtras.contains(posicaoNova)) {
            int carta = tirarCartaExtra();
            JOptionPane.showMessageDialog(null, "Carta Extra: " + carta, "Você caiu em uma casa bônus!", JOptionPane.INFORMATION_MESSAGE);
            jogador.avancar(carta);
        }
    }
    

    public int tirarCartaExtra() {
        embaralharCartas();
        return cartasExtras.get(0);
    }

    public List<Integer> getCasasCartasExtras() {
        return casasCartasExtras;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public int getPosicaoAntiga(int jogadorIndex) {
        return jogadores.get(jogadorIndex).getPosicaoAntiga();
    }

    public int getPosicaoAtual(int jogadorIndex) {
        return jogadores.get(jogadorIndex).getPosicaoAtual();
    }

    public int getQuantidadeJogadores() {
        return jogadores.size();
    }

    public int tirarCartaInicial() {
        return cartasExtras.remove(0);
    }

    public int lancarDado() {
        return (int) (Math.random() * 6) + 1;
    }

    public int getVezDoJogador() {
        return vezDoJogador;
    }

     public void setVezDoJogador() {
        if (vezDoJogador + 1 == jogadores.size()){
            vezDoJogador = 0;
        } else {
            vezDoJogador = vezDoJogador + 1;
        }
    }
}
