import controller.ExemploController;
import model.*;
import model.enums.TipoMidia;
import test.ExemploTest;
import test.PacoteTest;

import java.time.LocalDate;
import java.util.Date;

public class Principal {
    public static void main(String[] args) {

        Acomodacao a1 = new Acomodacao(
            "Chalé Família",
            400.0,
            6,
            "Isto é uma descrição"
        );
        Acomodacao a2 = new Acomodacao(
                "Chalé Casal",
                1000.0,
                2,
                "Isto é uma aaa"
        );

        Acomodacao a3 = new Acomodacao(
                "Chalé amigos",
                2000.00,
                10,
                "Isto é uma bbb");

        PacoteTest pt1 = new PacoteTest();
//        System.out.println(pt1.testeCadastro("LALAL",1L, 12, 100.0));
//        System.out.println(pt1.testeListagem());
//        System.out.println(pt1.testeAlteracao(5L, "Party", 1L , 30, 2000.00));
//        System.out.println(pt1.testeExclusao(300L));


    }
}
