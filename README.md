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

<p>A classe Main oferece um menu interativo para o usuário interagir com o sistema de um teatro. O usuário pode cadastrar espetáculos, cadastrar clientes, realizar compras de ingressos e sair do sistema. A lógica para cadastro e compras é delegada a métodos da classe Teatro, que não está definida aqui, mas que provavelmente contém a implementação desses processos.</p>

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
<p>A classe Teatro oferece funcionalidades para:

Cadastrar espetáculos e clientes.
Gerenciar a compra de ingressos, permitindo ao usuário escolher espetáculos, assentos e tipos de ingresso.
Verificar se o cliente está cadastrado e calcular o valor total da compra.
Essa classe depende das classes Espetaculo, Cliente, Pedido e Entrada, que são mencionadas mas não fornecidas no código. Essas classes provavelmente contêm detalhes como os dados do espetáculo, a representação do cliente, a lógica do pedido e o cálculo do valor das entradas.</p>

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

<P>A classe Pedido tem a função de organizar as entradas adquiridas durante uma compra. Ela permite adicionar entradas e calcular o valor total das entradas do pedido. O cálculo do valor de cada entrada é feito através do método calculaValor, que provavelmente está implementado na classe Entrada, onde o preço de cada tipo de ingresso (inteira, meia, professor, etc.) é calculado.

Essa classe é importante para a gestão das compras no sistema, permitindo a consolidação do total a ser pago por um cliente no final de uma compra.</p>

<h2>Classe Espetáculo:</h2>

```

import java.util.Arrays;

public class Espetaculo {
    private String nome;
    private String data;
    private String hora;
    private double preco;
    private boolean[] assentos;

    public Espetaculo(String nome, String data, String hora, double preco) {
        this.nome = nome;
        this.data = data;
        this.hora = hora;
        this.preco = preco;
        this.assentos = new boolean[50]; 
    }

    public boolean assentoDisponivel(int numero) {
        return !assentos[numero]; 
    }

    public Entrada novaEntrada(int tipo, int assento) {
        marcarAssento(assento);
        switch (tipo) {
            case 1:
                return new EntradaInteira(assento, preco);
            case 2:
                return new EntradaMeia(assento, preco);
            case 3:
                return new EntradaProfessor(assento, preco);
            default:
                throw new IllegalArgumentException("Tipo de entrada inválido.");
        }
    }

    private void marcarAssento(int assento) {
        assentos[assento] = true; 
    }

    public void apresentaAssentos() {
        System.out.println("||| Assentos Disponíveis |||");
        for (int i = 0; i < 50; i++) {
            if (assentos[i]) {
                System.out.print("XX ");
            } else {
                System.out.print((i + 1) + " ");
            }
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return nome + " " + data + " " + hora + " R$ " + preco;
    }
}
```

<P>A classe Espetaculo gerencia as informações sobre um espetáculo, incluindo dados como nome, data, hora e preço do ingresso. Ela também lida com os assentos, verificando sua disponibilidade e marcando-os como ocupados quando um ingresso é vendido. Além disso, permite a criação de entradas para diferentes tipos de ingressos (inteira, meia, professor) e fornece uma representação visual dos assentos disponíveis.</P>

<h2>Classe EntradaProfessor:</h2>

```

public class EntradaProfessor extends Entrada {
    private double preco;

    public EntradaProfessor(int numeroDoAssento, double preco) {
        super(numeroDoAssento);
        this.preco = preco;
    }

    @Override
    public double calculaValor() {
        return preco * 0.4; 
    }
}
```

<p>A classe EntradaProfessor é uma especialização da classe Entrada que define o comportamento do cálculo de preço para ingressos destinados a professores. Ela aplica um desconto de 60% sobre o valor do ingresso integral, retornando apenas 40% do preço original. O construtor da classe recebe o número do assento e o preço integral, e a lógica de cálculo do valor do ingresso é implementada no método calculaValor.</p>

<h2>EntradaMeia:</h2>

```

public class EntradaMeia extends Entrada {
    private double preco;

    public EntradaMeia(int numeroDoAssento, double preco) {
        super(numeroDoAssento);
        this.preco = preco;
    }

    @Override
    public double calculaValor() {
        return preco * 0.5; 
    }
}
```
<p>A classe EntradaMeia é uma especialização da classe Entrada que implementa o cálculo do valor de ingresso com desconto para meia-entrada. O valor da entrada para meia-entrada é calculado aplicando um desconto de 50% sobre o preço integral do ingresso. O construtor recebe o número do assento e o preço integral, e a lógica de cálculo do valor da entrada é implementada no método calculaValor.</p>

<h2>Classe EntradaInteira:</h2>

```

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
```

<p>A classe EntradaInteira é uma especialização da classe Entrada e é usada para representar ingressos sem descontos (ingresso inteiro). O valor do ingresso é o preço integral, que é simplesmente retornado pelo método calculaValor. O construtor recebe o número do assento e o preço integral, e a lógica do cálculo é simples: retornar o valor do ingresso integral sem alteração.</p>

<h2>Classe Entrada:</h2>

```

public abstract class Entrada {
    protected int numeroDoAssento;

    public Entrada(int numeroDoAssento) {
        this.numeroDoAssento = numeroDoAssento;
    }

    public abstract double calculaValor();
}

```

<p>A classe Entrada é uma classe abstrata que define a estrutura básica para os ingressos no sistema. Ela armazena o número do assento, que é comum a todos os tipos de ingressos, e define um método abstrato calculaValor, que será implementado pelas classes filhas para calcular o preço do ingresso de forma específica, dependendo do tipo de ingresso (inteira, meia, professor, etc.). A ideia de tornar Entrada abstrata é permitir que cada tipo de ingresso tenha seu próprio comportamento ao calcular o valor, mantendo uma estrutura comum para os ingressos.<p/>

<h2>Classe Cliente:</h2>

```

public class Cliente {
    private String nome;
    private String cpf;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + ")";
    }
}
```
<P>A classe Cliente é uma representação de um cliente no sistema, armazenando informações como nome e CPF. Ela oferece um construtor para inicializar esses dados e um método getCpf para recuperar o CPF. A sobrescrição do método toString permite que os dados do cliente sejam facilmente representados de forma legível em formato de string.</P>



