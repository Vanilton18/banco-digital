import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ContaTest {

    Conta conta;
    Cliente cliente;

    @BeforeEach
    public void setUp(){
        cliente = new Cliente();
        cliente.setNome("Robertinho");
        cliente.setIdCliente(2);
        conta = new ContaCorrente(cliente);
        conta.setNumero(2412);
        conta.setAgencia(1);
    }

    @Test
    public void verificarDepositoValidoEmContaCorrente(){
        conta.depositar(30);
        Assertions.assertEquals(30, conta.getSaldo());
    }

    @Test
    public void verificarDepositoDuploEmContaCorrente(){
        conta.depositar(30);
        conta.depositar(50.90);
        Assertions.assertEquals(80.90, conta.getSaldo());
    }

    @Test
    public void verificarTransferenciaValida(){
        Conta pp = new ContaPoupanca(cliente);
        pp.setAgencia(1);
        pp.setNumero(2412);
        conta.transferir(10, pp);
        Assertions.assertEquals(10, pp.getSaldo());
        Assertions.assertEquals(-10, conta.getSaldo());
    }

    @AfterEach
    public void tearDown(){
        conta = null;
        cliente = null;
    }

}
