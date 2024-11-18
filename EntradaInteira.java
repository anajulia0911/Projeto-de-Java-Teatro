
public class EntradaInteira extends Entrada {
    private double preco;

    public EntradaInteira(int numeroDoAssento, double preco) {
        super(numeroDoAssento);
        this.preco = preco;
    }

    @Override
    public double calculaValor() {
        return preco;
    }
}