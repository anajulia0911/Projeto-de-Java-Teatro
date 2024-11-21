public abstract class Entrada {
    protected int numeroDoAssento;
    protected double preco;

    public Entrada(int numeroDoAssento, double preco) {
        this.numeroDoAssento = numeroDoAssento;
        this.preco = preco;
    }

    public abstract double calculaValor();
}

class EntradaInteira extends Entrada {
    public EntradaInteira(int numeroDoAssento, double preco) {
        super(numeroDoAssento, preco);
    }

    public double calculaValor() {
        return preco;
    }
}

class EntradaMeia extends Entrada {
    public EntradaMeia(int numeroDoAssento, double preco) {
        super(numeroDoAssento, preco);
    }

    public double calculaValor() {
        return preco / 2;
    }
}

class EntradaProfessor extends Entrada {
    public EntradaProfessor(int numeroDoAssento, double preco) {
        super(numeroDoAssento, preco);
    }

    public double calculaValor() {
        return preco * 0.4;
    }
}
