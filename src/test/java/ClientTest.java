import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {

    @BeforeEach
    public void setUp(){
        System.out.println("Antes do teste faça isso");
    }

    @Test
    public void testCadastrarClienteValido(){
        System.out.println("Agora faço os passos do teste aqui");
    }

    @AfterEach
    public void tearDown(){
        System.out.println("Escrevo aqui o que será feito ao final do teste");
    }

}
