import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientTest {

    Cliente cliente;

    @BeforeEach
    public void setUp(){
        cliente = new Cliente();
    }

    @Test
    public void testCadastrarClienteValido(){
        cliente.setIdCliente(1);
        cliente.setNome("Vava");
        assertTrue(cliente.getIdCliente() == 1);
        assertEquals("Vava", cliente.getNome());
    }

    @Test
    public void testCadastrarClienteInValido(){
        cliente.setIdCliente(-1);
        cliente.setNome("Vava");
        assertTrue(cliente.getIdCliente() >  0);

    }

    @AfterEach
    public void tearDown(){
        cliente = null;
    }

}
