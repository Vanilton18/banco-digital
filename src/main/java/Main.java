import java.util.*;

public class Main {

    List<Cliente> clientes = new ArrayList<>();
    List<Conta> contas = new ArrayList<>();

    public static void main(String[] args) {
        short opcao = 50;
        Scanner leitor = new Scanner(System.in);
        Main main = new Main();

        do {
            main.exibirMenu();

            System.out.print("Opção escolhida: ");
            opcao = leitor.nextShort();

            switch (opcao) {
                case 1:
                    main.cadastrarCliente();
                    break;
                case 2:
                    main.criarConta();
                    break;
                case 3:
                    main.imprimirCliente();
                    break;
                case 4:
                    main.imprimirConta();
                    break;
                case 5:
                    main.depositarCorrente();
                    break;
                case 50:
                    main.exibirMenu();
                    break;
                default:
                    main.exibirMenu();
            }
        }
        while (opcao != 99);
    }

    public Cliente findClientById(int id, List<Cliente> cliente) {
        Iterator<Cliente> iterator = cliente.iterator();
        while (iterator.hasNext()) {
            Cliente customer = iterator.next();
            if (customer.getIdCliente() == id) {
                return customer;
            }
        }
        return null;
    }

    private void depositarCorrente() {
        Scanner leitor = new Scanner(System.in);
        System.out.print("ID do cliente: ");
        int idClient = leitor.nextInt();
        Cliente c = findClientById(idClient, clientes);
        Conta cc = new ContaCorrente(c);
        Conta option = contas.stream().filter(x -> x.cliente.getIdCliente() == idClient).findFirst().get();
        cc.setAgencia(option.agencia);
        cc.setNumero(option.numero);
        System.out.print("Valor de depósito: ");
        String deposito = leitor.next();
        double saldoAtual = contas.stream().filter(x -> x.cliente.getIdCliente() == idClient && x instanceof ContaCorrente).findFirst().get().saldo;
        cc.depositar(Double.parseDouble(deposito) + saldoAtual);
        int pos = contas.indexOf(contas.stream().filter(x -> x.cliente.getIdCliente() == idClient && x instanceof ContaCorrente).findFirst().get());
        contas.remove(pos);
        contas.add(cc);
        cc.imprimirExtrato();
    }


    private void exibirMenu() {
        System.out.println("\n\n");
        System.out.println("+-------------------------------------------+");
        System.out.println("|        Menu de Opções                     |");
        System.out.println("+-------------------------------------------+");
        System.out.println("| 01 - Cadastrar Cliente                    |");
        System.out.println("| 02 - Cadastrar Conta                      |");
        System.out.println("| 03 - Imprimir relatório de clientes       |");
        System.out.println("| 04 - Imprimir relatório de contas         |");
        System.out.println("| 05 - Depositar Corrente 				    |");
        System.out.println("| 50 - Exibir o menu de opções              |");
        System.out.println("| 99 - Sair                                 |");
        System.out.println("+-------------------------------------------+");
    }

    private void cadastrarCliente() {
        Scanner leitor = new Scanner(System.in);
        Cliente cliente = new Cliente();
        System.out.print("ID do cliente: ");
        cliente.setIdCliente(leitor.nextInt());
        System.out.print("Nome do cliente: ");
        leitor.nextLine();// limpa o scanner
        cliente.setNome(leitor.nextLine());
        clientes.add(cliente);

    }

    private void imprimirCliente() {
        for (Cliente cliente : clientes) {
            System.out.println("\nId: " + cliente.getIdCliente());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("\n");
        }
    }

    /**
     * TODO Listar contas
     */
    private void imprimirConta() {
        for (Conta conta : contas) {
            System.out.println("\n");
            System.out.println("Cliente: " + conta.cliente.getNome());
            System.out.println("\n");
            System.out.println("Agência: " + conta.agencia);
            System.out.println("\n");
            System.out.println("Número Conta: " + conta.numero);
        }
    }


    private void criarConta() {
        System.out.println("+-------------------------------------------+");
        System.out.println("|        Lista de Clientes                   |");
        imprimirCliente();
        System.out.println("+-------------------------------------------+");
        Scanner leitor = new Scanner(System.in);
        System.out.print("ID do cliente: ");
        int idClient = leitor.nextInt();
        Conta cc = new ContaCorrente(findClientById(idClient, clientes));
        System.out.print("Número da Agência: ");
        int ag = leitor.nextInt();
        cc.setAgencia(ag);
        System.out.print("Número da Conta: ");
        int numConta = leitor.nextInt();
        cc.setNumero(numConta);

        Conta poupanca = new ContaPoupanca(findClientById(idClient, clientes));
        poupanca.agencia = ag;
        poupanca.numero = numConta;

        contas.add(cc);
        contas.add(poupanca);
    }


}
