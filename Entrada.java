
public abstract class Entrada {
    protected int numeroDoAssento;

    public Entrada(int numeroDoAssento) {
        this.numeroDoAssento = numeroDoAssento;
    }

    public abstract double calculaValor();
}
