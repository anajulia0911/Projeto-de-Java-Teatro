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
                case 1:
                    teatro.cadastrarEspetaculo();
                    break;
                case 2:
                    teatro.cadastrarCliente();
                    break;
                case 3:
                    teatro.novaCompra();
                    break;
                case 4:
                    System.out.println("Encerrando o programa...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
