import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

public class Jogo {
    private List<Jogador> jogadores;
    private List<Integer> cartasRetiradas;
    private List<Integer> casasCartasExtras;
    private int vezDoJogador;

    public Jogo(int quantidadeJogadores) {
        jogadores = new ArrayList<>();
        cartasRetiradas = new ArrayList<>();
        casasCartasExtras = new ArrayList<>();

        // Adicione jogadores ao jogo
        for (int i = 0; i < quantidadeJogadores; i++) {
            jogadores.add(new Jogador());
        }

        // Inicialize as cartas
        inicializarCartas();

        // Defina casas aleatórias para cartas extras
        definirCasasCartasExtras(8);  // Altere o número conforme necessário

        // Inicializa a vez do jogador como o primeiro
        vezDoJogador = 0;
    }

    private void inicializarCartas() {
        // Adicione as cartas ao jogo (por exemplo, de -3 a 3)
        for (int i = -2; i <= 3; i++) {
            cartasRetiradas.add(i);
        }

        // Embaralhe as cartas
        embaralharCartas();
    }

    private void embaralharCartas() {
        Collections.shuffle(cartasRetiradas);
    }

    private void definirCasasCartasExtras(int quantidadeCasas) {
        // Adicione casas aleatórias às casas de cartas extras
        Random random = new Random();
        for (int i = 1; i < quantidadeCasas; i++) {
            int casa = random.nextInt(50) + 1;  // 2 a quantidadeCasas
            casasCartasExtras.add(casa);
        }
    }

    public void realizarJogada(int jogadorIndex, int passos) {
        Jogador jogador = jogadores.get(jogadorIndex);
     //   int posicaoAntiga = jogador.getPosicaoAntiga();
        jogador.avancar(passos);
        int posicaoNova = jogador.getPosicaoAtual();
    
        // Verifique se o jogador caiu em uma casa com carta extra
        if (casasCartasExtras.contains(posicaoNova)) {
            int carta = tirarCartaExtra();
            JOptionPane.showMessageDialog(null, "Carta Extra: " + carta, "Você caiu em uma casa bônus!", JOptionPane.INFORMATION_MESSAGE);
            jogador.avancar(carta);  // Mova o jogador de acordo com a carta extra
        }
    }
    

    public int tirarCartaExtra() {
        return cartasRetiradas.remove(0);
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
        return cartasRetiradas.remove(0);
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
