package test;

import controller.ExemploController;
import controller.PacoteController;
import model.Acomodacao;
import model.Pacote;

public class PacoteTest {

    public String testeCadastro(String nome, Long idacomodacao, Integer qtdDiarias, Double valorTotal) {
        PacoteController pacoteController = new PacoteController();
        String resposta = pacoteController.cadastrar(nome, idacomodacao, qtdDiarias, valorTotal);
        return resposta;
    }

    public String testeListagem() {
        PacoteController pacoteController = new PacoteController();
        String resposta = pacoteController.listar();
        return resposta;
    }

    public String testeAlteracao(Long id, String nome, Long idAcomodacao, Integer qtdDiarias, Double valorTotal) {
        PacoteController pacoteController = new PacoteController();
        String resposta = pacoteController.alterar(id, nome, idAcomodacao, qtdDiarias, valorTotal);
        return resposta;
    }

    public String testeExclusao(Long id) {
        PacoteController pacoteController = new PacoteController();
        String resposta = pacoteController.excluir(id);
        return resposta;
    }

}


