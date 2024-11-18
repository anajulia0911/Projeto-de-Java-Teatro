
public class EntradaProfessor extends Entrada {
    private double preco;

    public EntradaProfessor(int numeroDoAssento, double preco) {
        super(numeroDoAssento);
        this.preco = preco;
    }

    @Override
    public double calculaValor() {
        return preco * 0.4; // 40% do valor da inteira
    }
}