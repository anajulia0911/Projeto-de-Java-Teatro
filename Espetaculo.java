
import java.util.Arrays;

public class Espetaculo {
    private String nome;
    private String data;
    private String hora;
    private double preco;
    private boolean[] assentos;

    public Espetaculo(String nome, String data, String hora, double preco) {
        this.nome = nome;
        this.data = data;
        this.hora = hora;
        this.preco = preco;
        this.assentos = new boolean[50]; // Inicialmente todos os assentos estão disponíveis
    }

    public boolean assentoDisponivel(int numero) {
        return !assentos[numero]; // Retorna true se o assento não estiver ocupado
    }

    public Entrada novaEntrada(int tipo, int assento) {
        marcarAssento(assento);
        switch (tipo) {
            case 1:
                return new EntradaInteira(assento, preco);
            case 2:
                return new EntradaMeia(assento, preco);
            case 3:
                return new EntradaProfessor(assento, preco);
            default:
                throw new IllegalArgumentException("Tipo de entrada inválido.");
        }
    }

    private void marcarAssento(int assento) {
        assentos[assento] = true; // Marca o assento como ocupado
    }

    public void apresentaAssentos() {
        System.out.println("||| Assentos Disponíveis |||");
        for (int i = 0; i < 50; i++) {
            if (assentos[i]) {
                System.out.print("XX ");
            } else {
                System.out.print((i + 1) + " ");
            }
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return nome + " " + data + " " + hora + " R$ " + preco;
    }
}
