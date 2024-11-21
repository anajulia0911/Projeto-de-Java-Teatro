import java.util.List;
import java.util.ArrayList;

public class Pedido {
    private List<Entrada> entradas = new ArrayList<>();

    public Pedido() {}

    public void adicionaEntrada(Entrada entrada) {
        entradas.add(entrada);
    }

    public double calculaValorTotal() {
        return entradas.stream()
                       .mapToDouble(Entrada::calculaValor)
                       .sum();
    }
}
