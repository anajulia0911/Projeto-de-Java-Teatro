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
    4-) O código entra em um loop infinitp (while (true)) que exibe um menu com 4 opções: Cadastrar espetáculo | Cadastrar cliente | Comprar entradas | Sair do sistema |O usuário escolhe uma opção e o número escolhido é lido através do scanner.nextInt().
5-) Dependendo da escolha do usuário, o programa executa uma ação específica. O switch é utilizado para comparar a opção escolhida com os casos definidos.
</p>

<h2>Classe Teatro:</h2>

```
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
```
<p>A classe Teatro é um sistema para gerenciar o teatro, onde você pode cadastrar espetáculos e clientes, além de realizar a compra de entradas.</p>

<p>Atributos:</p>
<p>espetaculos: Uma lista (ArrayList) que armazena os espetáculos cadastrados no sistema. Cada espetáculo é representado por um objeto da classe Espetaculo.
clientes: Uma lista (ArrayList) que armazena os clientes cadastrados. Cada cliente é representado por um objeto da classe Cliente.</p>

<p>Construtor:</p>
<p>O construtor da classe inicializa as listas de espetáculos e clientes vazias. Ou seja, quando um objeto Teatro é criado, ele começa sem espetáculos ou clientes.</p>

<p>Métodos:</p>

<p>O método cadastrarEspestáculo permite cadastrar um novo espetáculo no sistema. Ele recebe como parâmetros o nome, data, hora e preço do espetáculo, cria um objeto Espetaculo com esses dados e o adiciona à lista espetaculos.</p>

<p>O método cadastrarCliente permite registrar um cliente no sistema. Ele recebe o nome e o CPF do cliente, cria um objeto Cliente e o adiciona à lista clientes.</p>

<p>O método novaCompra gerencia o processo de compra de ingressos. Ele primeiro verifica se há espetáculos cadastrados. Caso não haja, uma mensagem é exibida e a execução do método é interrompida.</p>

<p>O método verificaCliente verifica se um cliente com o CPF fornecido está cadastrado. Ele percorre a lista de clientes e retorna true se o CPF for encontrado, e false caso contrário.</p>

<h2> Classe Pedido:</h2>

```

import java.util.ArrayList;

public class Pedido {
    private ArrayList<Entrada> entradas;

    public Pedido() {
        this.entradas = new ArrayList<>();
    }

    public void adicionaEntrada(Entrada entrada) {
        entradas.add(entrada);
    }

    public double calculaValorTotal() {
        double total = 0;
        for (Entrada entrada : entradas) {
            total += entrada.calculaValor();
        }
        return total;
    }
}
```

<P>A classe Pedido é responsável por gerenciar o conjunto de entradas adquiridas durante uma compra no sistema do teatro. Ela contém uma lista de objetos Entrada e métodos para adicionar entradas à lista e calcular o valor total da compra.</P>

<p>Atributos: entradas: Uma lista (ArrayList) que armazena as entradas adquiridas no pedido. Cada entrada é representada por um objeto da classe Entrada.</p>

<p>Construtor: O construtor da classe inicializa a lista de entradas como uma nova instância de ArrayList. Ou seja, quando um objeto Pedido é criado, ele começa com uma lista vazia de entradas. </p>

<p>Método: 
1-) O método adicionaEntrada recebe um objeto Entrada como parâmetro e o adiciona à lista entradas. Ele é usado para registrar uma nova entrada no pedido, como quando o usuário escolhe um tipo de ingresso e assento.
    
2-) O método calculaValorTotal o valor total do pedido somando o valor de cada entrada na lista entradas. Ele percorre todas as entradas do pedido e chama o método calculaValor de cada objeto Entrada para obter o valor de cada ingresso. O valor de todas as entradas é somado e o total é retornado.</p>



