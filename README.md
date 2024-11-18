<h1>Projeto de Java</h1>

<h2>Mack Theather</h2>

<h3> Introdução:</h3>
<p>Bem-vindo ao Sistema de Gestão de Espetáculos, uma aplicação desenvolvida em Java que permite a organização e a venda de ingressos para peças teatrais. Este sistema foi projetado para facilitar o gerenciamento de espetáculos, incluindo a reserva de assentos e a emissão de diferentes tipos de entradas, como inteira, meia-entrada e entrada para professores.

Com uma interface simples e intuitiva, o sistema permite que os usuários visualizem a disponibilidade de assentos, realizem reservas e calculem automaticamente o valor das entradas com base em descontos específicos. A aplicação é ideal para teatros que desejam otimizar suas operações de venda de ingressos.</p>

<h1>Explicação das classes:</h1>

<h2>Classe Main</h2>

<p>Código:</p>

```
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
                    System.out.print("Nome do Espetáculo: ");
                    scanner.nextLine(); 
                    String nome = scanner.nextLine();
                    System.out.print("Data (dd/mm/yyyy): ");
                    String data = scanner.nextLine();
                    System.out.print("Hora (hh:mm): ");
                    String hora = scanner.nextLine();
                    System.out.print("Preço da Entrada Inteira: ");
                    double preco = scanner.nextDouble();
                    teatro.cadastrarEspetaculo(nome, data, hora, preco);
                    break;

                case 2: 
                    System.out.print("Nome do Cliente: ");
                    scanner.nextLine(); 
                    String nomeCliente = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpfCliente = scanner.nextLine();
                    teatro.cadastrarCliente(nomeCliente, cpfCliente);
                    break;

                case 3: 
                    teatro.novaCompra();
                    break;

                case 4: 
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
```

<p>
    1-) A classe Scanner é importada para permitir a leitura de dados de entrada do usuário (como texto e números).
    2-) A classe Main contém o método main, que é o ponto de entrada da aplicação, O método main é onde o código é executado. É nele que o menu e a interação com o usuário acontecem.
    3-) Objeto teatro é criado a partir da classe Teatro.
    4-) O código entra em um loop infinitp (while (true)) que exibe um menu com 4 opções:

Cadastrar espetáculo
Cadastrar cliente
Comprar entradas
Sair do sistema
O usuário escolhe uma opção e o número escolhido é lido através do scanner.nextInt().

5-) Dependendo da escolha do usuário, o programa executa uma ação específica. O switch é utilizado para comparar a opção escolhida com os casos definidos.
</p>
