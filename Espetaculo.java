public class Espetaculo {
    private String nome;
    private String data;
    private String hora;
    private double preco;
    private boolean[] assentos = new boolean[50];

    public Espetaculo(String nome, String data, String hora, double preco) {
        this.nome = nome;
        this.data = data;
        this.hora = hora;
        this.preco = preco;
    }

    public void apresentaAssentos() {
        System.out.println("||| Assentos Disponíveis |||");
        for (int i = 0; i < assentos.length; i++) {
            if (i % 10 == 0) System.out.println();
            System.out.print(assentos[i] ? "XX " : (i + 1) + " ");
        }
        System.out.println();
    }

    public boolean isAssentoDisponivel(int assento) {
        return !assentos[assento - 1];
    }

    public Entrada novaEntrada(int tipo, int assento) {
        if (assento < 1 || assento > 50 || assentos[assento - 1]) {
            return null;
        }
        assentos[assento - 1] = true;
        switch (tipo) {
            case 1:
                return new EntradaInteira(assento, preco);
            case 2:
                return new EntradaMeia(assento, preco);
            case 3:
                return new EntradaProfessor(assento, preco);
            default:
                return null;
        }
    }

    public String getNome() {
        return nome;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public double getPreco() {
        return preco;
    }
}
