package controller;

import dao.ExemploDAO;
import dao.PacoteDAO;
import model.Acomodacao;
import model.Exemplo;
import model.Pacote;

import java.util.ArrayList;

public class PacoteController {

    public String cadastrar(String nome, Long idAcomodacao, Integer qtdDiarias, Double valorTotal) {
        PacoteDAO pacoteDAO = new PacoteDAO();
        Acomodacao  acomodacao = pacoteDAO.selecionarAcomodacaoPorId(idAcomodacao);
        if(nome == null || nome.trim().equals("")) {
            return "[ERRO] Campo nome é obrigatório. Pacote não cadastrado!";
        }
        if(idAcomodacao == null) {
            return "[ERRO] Campo acomodacao é obrigatório. Pacote não cadastrado!";
        }

        if(pacoteDAO.selecionarAcomodacaoPorId(idAcomodacao) == null) {
            return "[ERRO] Acomodacao não existente. Pacote não cadastrado!";
        }

        if(qtdDiarias == null || qtdDiarias == 0) {
            return "[ERRO] qtdDiarias é obrigatório. Pacote não cadastrado!";
        }
        if(valorTotal == null || valorTotal == 0) {
            return "[ERRO] Campo valorTotal é obrigatório. Pacote não cadastrado!";
        }

        Pacote pacote = new Pacote(nome, acomodacao, qtdDiarias, valorTotal);

        if(pacoteDAO.inserir(pacote)) {
            return "[OK] Pacote cadastrado com sucesso!";
        } else {
            return "[ERRO] Erro desconhecido. Pacote não cadastrado!";
        }
    }

    public String listar() {
        PacoteDAO pacoteDAO = new PacoteDAO();
        ArrayList<Pacote> lista = pacoteDAO.selecionar();

        String conteudo = "";
        for (Pacote pacote : lista) {
            conteudo += pacote + "\n";
        }

        return conteudo;
    }

    public String alterar(Long id, String nome, Long idAcomodacao, Integer qtdDiarias, Double valorTotal) {
        PacoteDAO pacoteDAO = new PacoteDAO();
        Acomodacao  acomodacao = pacoteDAO.selecionarAcomodacaoPorId(idAcomodacao);
        if(id == null) {
            return "[ERRO] ID não pode ser nulo. Pacote não alterado!";
        }
        if(pacoteDAO.selecionarPorId(id) == null) {
            return "[ERRO] Não encontrado. Pacote não alterado!";
        }
        if(nome == null || nome.trim().equals("")) {
            return "[ERRO] Campo nome é obrigatório. Pacote não alterado!";
        }

        if(pacoteDAO.selecionarAcomodacaoPorId(idAcomodacao) == null) {
            return "[ERRO] Acomodacao não existente. Pacote não alterado!";
        }

        if(qtdDiarias == null || qtdDiarias == 0) {
            return "[ERRO] qtdDiarias é obrigatório. Pacote não alterado!";
        }
        if(valorTotal == null || valorTotal == 0) {
            return "[ERRO] Campo valorTotal é obrigatório. Pacote não alterado!";
        }

        Pacote pacote = new Pacote(id, nome, acomodacao, qtdDiarias, valorTotal);
        if(pacoteDAO.atualizar(pacote)) {
            return "[OK] Pacote alterado com sucesso!";
        } else {
            return "[ERRO] Erro desconhecido. Pacote não alterado!";


        }
    }

    public String excluir(Long id) {
        PacoteDAO pacoteDAO = new PacoteDAO();

        if(id == null) {
            return "[ERRO] ID não pode ser nulo. Pacote não excluído!";
        }
        if(pacoteDAO.selecionarPorId(id) == null) {
            return "[ERRO] Não encontrado. Pacote não excluído!";
        }

        if(pacoteDAO.deletar(id)) {
            return "[OK] Pacote excluído com sucesso!";
        } else {
            return "[ERRO] Erro desconhecido. Pacote não excluído!";
        }
    }

}
