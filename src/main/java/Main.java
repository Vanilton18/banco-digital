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
                case 6:
                    main.depositarPoupanca();
                    break;
                case 7:
                    main.transferirCorrenteParaPoupanca();
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

    private void exibirMenu() {
        System.out.println("\n\n");
        System.out.println("+-------------------------------------------+");
        System.out.println("|        Menu de Opções                     |");
        System.out.println("+-------------------------------------------+");
        System.out.println("| 01 - Cadastrar Cliente                    |");
        System.out.println("| 02 - Cadastrar Conta                      |");
        System.out.println("| 03 - Imprimir relatório de clientes       |");
        System.out.println("| 04 - Imprimir relatório de contas         |");
        System.out.println("| 05 - Depositar Corrente                   |");
        System.out.println("| 06 - Depositar Poupança                   |");
        System.out.println("| 07 - Transferir CC para Poupança          |");
        System.out.println("| 50 - Exibir o menu de opções              |");
        System.out.println("| 99 - Sair                                 |");
        System.out.println("+-------------------------------------------+");
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


    private void imprimirConta() {
        for (Conta conta : contas) {
            if(conta instanceof ContaPoupanca){
                System.out.println("\n");
                System.out.println("=== Extrato Conta Poupança ===");
            } else {
                System.out.println("\n");
                System.out.println("=== Extrato Conta Corrente ===");
            }
            System.out.println(String.format("Titular: %s", conta.cliente.getNome()));
            System.out.println(String.format("Agencia: %d", conta.agencia));
            System.out.println(String.format("Numero: %d", conta.numero));
            System.out.println(String.format("Saldo: %.2f", conta.saldo));
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

    private void depositarPoupanca() {
        Scanner leitor = new Scanner(System.in);
        System.out.print("ID do cliente: ");
        int idClient = leitor.nextInt();
        Cliente c = findClientById(idClient, clientes);
        Conta pp = new ContaPoupanca(c);
        Conta option = contas.stream().filter(x -> x.cliente.getIdCliente() == idClient).findFirst().get();
        pp.setAgencia(option.agencia);
        pp.setNumero(option.numero);
        System.out.print("Valor de depósito: ");
        String deposito = leitor.next();
        double saldoAtual = contas.stream().filter(x -> x.cliente.getIdCliente() == idClient && x instanceof ContaPoupanca).findFirst().get().saldo;
        pp.depositar(Double.parseDouble(deposito) + saldoAtual);
        int pos = contas.indexOf(contas.stream().filter(x -> x.cliente.getIdCliente() == idClient && x instanceof ContaPoupanca).findFirst().get());
        contas.remove(pos);
        contas.add(pp);
        pp.imprimirExtrato();
    }


    private void transferirCorrenteParaPoupanca() {
        Scanner leitor = new Scanner(System.in);
        System.out.print("ID do cliente: ");
        int idClient = leitor.nextInt();
        Cliente c = findClientById(idClient, clientes);
        Conta cc = contas.stream().filter(x -> x.cliente.getIdCliente() == idClient && x instanceof ContaCorrente).findFirst().get();
        Conta pp = contas.stream().filter(x -> x.cliente.getIdCliente() == idClient && x instanceof ContaPoupanca).findFirst().get();
        System.out.print("Valor da transferência: ");
        String valor = leitor.next();
        cc.transferir(Double.parseDouble(valor), pp);
        int posPP = contas.indexOf(contas.stream().filter(x -> x.cliente.getIdCliente() == idClient && x instanceof ContaPoupanca).findFirst().get());
        contas.remove(posPP);
        contas.add(pp);
        int posCC = contas.indexOf(contas.stream().filter(x -> x.cliente.getIdCliente() == idClient && x instanceof ContaCorrente).findFirst().get());
        contas.remove(posCC);
        contas.add(cc);
        pp.imprimirExtrato();
    }

}
