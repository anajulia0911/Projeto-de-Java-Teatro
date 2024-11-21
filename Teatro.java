import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Teatro {
    private List<Espetaculo> espetaculos = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();

    public void novaCompra() {
        if (espetaculos.isEmpty()) {
            System.out.println("Nenhum espetáculo cadastrado.");
            return;
        }

        System.out.println("*** VENDA DE ENTRADAS - ESPETÁCULOS ***");
        for (int i = 0; i < espetaculos.size(); i++) {
            System.out.printf("%d) %s %s %s R$ %.2f\n",
                    i + 1,
                    espetaculos.get(i).getNome(),
                    espetaculos.get(i).getData(),
                    espetaculos.get(i).getHora(),
                    espetaculos.get(i).getPreco());
        }

        System.out.print("Selecione um espetáculo: ");
        Scanner scanner = new Scanner(System.in);
        int escolhaEspetaculo = scanner.nextInt() - 1;

        if (escolhaEspetaculo < 0 || escolhaEspetaculo >= espetaculos.size()) {
            System.out.println("Espetáculo inválido.");
            return;
        }

        Espetaculo espetaculo = espetaculos.get(escolhaEspetaculo);
        Pedido pedido = new Pedido();

        while (true) {
            espetaculo.apresentaAssentos();
            System.out.print("Selecione um assento: ");
            int assento = scanner.nextInt();

            if (!espetaculo.isAssentoDisponivel(assento)) {
                System.out.println("Assento indisponível.");
                continue;
            }

            System.out.println("||| Tipos de Entrada |||");
            System.out.println("1) Inteira");
            System.out.println("2) Meia 50% do valor da entrada");
            System.out.println("3) Professor 40% do valor da entrada");
            System.out.print("Selecione um tipo de entrada: ");
            int tipoEntrada = scanner.nextInt();

            Entrada entrada = espetaculo.novaEntrada(tipoEntrada, assento);
            if (entrada != null) {
                pedido.adicionaEntrada(entrada);
                System.out.println("Entrada adicionada.");
            }

            System.out.print("Deseja comprar uma outra entrada (S/N)? ");
            String continuar = scanner.next();
            if (!continuar.equalsIgnoreCase("S")) break;
        }

        System.out.print("Informe o CPF do Cliente Cadastrado: ");
        String cpf = scanner.next();
        Cliente cliente = buscarCliente(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        cliente.adicionaPedido(pedido);
        double total = pedido.calculaValorTotal();
        System.out.printf("Valor Total: R$ %.2f\n", total);
    }

    public void cadastrarEspetaculo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*** CADASTRO DE ESPETÁCULO ***");
        System.out.print("Nome do Espetáculo: ");
        String nome = scanner.nextLine();
        System.out.print("Data: ");
        String data = scanner.nextLine();
        System.out.print("Hora: ");
        String hora = scanner.nextLine();
        System.out.print("Preço da Entrada Inteira: ");
        double preco = scanner.nextDouble();

        Espetaculo espetaculo = new Espetaculo(nome, data, hora, preco);
        espetaculos.add(espetaculo);
        System.out.println("Espetáculo cadastrado com sucesso!");
    }

    public void cadastrarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*** CADASTRO DE CLIENTE ***");
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        Cliente cliente = new Cliente(nome, cpf);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private Cliente buscarCliente(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }
}
