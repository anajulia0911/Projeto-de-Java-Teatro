import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Teatro teatro = new Teatro();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("*** MACK THEATER ***");
            System.out.println("1) Cadastrar Espetáculo");
            System.out.println("2) Cadastrar Cliente");
            System.out.println("3) Compra de Entradas");
            System.out.println("4) Sair");
            System.out.print("Selecione uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1: // Cadastrar Espetáculo
                    System.out.print("Nome do Espetáculo: ");
                    scanner.nextLine(); // Consumir a nova linha
                    String nome = scanner.nextLine();
                    System.out.print("Data (dd/mm/yyyy): ");
                    String data = scanner.nextLine();
                    System.out.print("Hora (hh:mm): ");
                    String hora = scanner.nextLine();
                    System.out.print("Preço da Entrada Inteira: ");
                    double preco = scanner.nextDouble();
                    teatro.cadastrarEspetaculo(nome, data, hora, preco);
                    break;

                case 2: // Cadastrar Cliente
                    System.out.print("Nome do Cliente: ");
                    scanner.nextLine(); // Consumir a nova linha
                    String nomeCliente = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpfCliente = scanner.nextLine();
                    teatro.cadastrarCliente(nomeCliente, cpfCliente);
                    break;

                case 3: // Compra de Entradas
                    teatro.novaCompra();
                    break;

                case 4: // Sair
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}