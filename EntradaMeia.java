
public class EntradaMeia extends Entrada {
    private double preco;

    public EntradaMeia(int numeroDoAssento, double preco) {
        super(numeroDoAssento);
        this.preco = preco;
    }

    @Override
    public double calculaValor() {
        return preco * 0.5; // 50% do valor da inteira
    }
}