import java.util.ArrayList;
import java.util.Scanner;

public class Teatro {
    private ArrayList<Espetaculo> espetaculos;
    private ArrayList<Cliente> clientes;

    public Teatro() {
        this.espetaculos = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public void cadastrarEspetaculo(String nome, String data, String hora, double preco) {
        Espetaculo espetaculo = new Espetaculo(nome, data, hora, preco);
        espetaculos.add(espetaculo);
        System.out.println("Espetáculo cadastrado com sucesso!");
    }

    public void cadastrarCliente(String nome, String cpf) {
        Cliente cliente = new Cliente(nome, cpf);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    public void novaCompra() {
        if (espetaculos.isEmpty()) {
            System.out.println("Nenhum espetáculo cadastrado.");
            return;
        }

        System.out.println("*** VENDA DE ENTRADAS - ESPETÁCULOS ***");
        for (int i = 0; i < espetaculos.size(); i++) {
            System.out.println((i + 1) + ") " + espetaculos.get(i));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Selecione um espetáculo: ");
        int escolhaEspetaculo = scanner.nextInt() - 1;

        if (escolhaEspetaculo < 0 || escolhaEspetaculo >= espetaculos.size()) {
            System.out.println("Espetáculo inválido.");
            return;
        }

        Espetaculo espetaculoSelecionado = espetaculos.get(escolhaEspetaculo);
        espetaculoSelecionado.apresentaAssentos();

        Pedido pedido = new Pedido();
        while (true) {
            System.out.print("Selecione um assento: ");
            int assento = scanner.nextInt();
            if (assento < 1 || assento > 50 || !espetaculoSelecionado.assentoDisponivel(assento - 1)) {
                System.out.println("Assento inválido ou já ocupado.");
                continue;
            }

            System.out.println("||| Tipos de Entrada |||");
            System.out.println("1) Inteira");
            System.out.println("2) Meia (50% do valor da entrada)");
            System.out.println("3) Professor (40% do valor da entrada)");
            System.out.print("Selecione um tipo de entrada: ");
            int tipoEntrada = scanner.nextInt();

            Entrada entrada = espetaculoSelecionado.novaEntrada(tipoEntrada, assento - 1);
            pedido.adicionaEntrada(entrada);

            System.out.print("Deseja comprar uma outra entrada (S/N)? ");
            char resposta = scanner.next().charAt(0);
            if (resposta != 'S' && resposta != 's') {
                break;
            }
        }

        System.out.print("Informe o CPF do Cliente Cadastrado: ");
        String cpf = scanner.next();
        if (verificaCliente(cpf)) {
            double valorTotal = pedido.calculaValorTotal();
            System.out.println("Valor Total: R$ " + valorTotal);
        } else {
            System.out.println("Cliente não cadastrado. A compra não pode ser concluída.");
        }
    }

    private boolean verificaCliente(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }
}