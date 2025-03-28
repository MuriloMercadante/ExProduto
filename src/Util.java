import java.text.DecimalFormat;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;
public class Util {
    private Fornecedor[] fornecedor = new Fornecedor[5];
    private Produto[] produto = new Produto[5];
    private int indexProduto = 0;
    private int indexFornecedor = 0;

    public void menu() {
        int opcao;
        int resposta;
        String aux = "Escolha uma opção\n";
        aux += "1.Cadastrar produto:\n";
        aux += "2.Pesquisar produto por nome:\n";
        aux += "3.Pesquisar fornecedor por CNPJ:\n";
        aux += "4.Finalizar:";


        while (true) {
            opcao = parseInt(showInputDialog(aux));

            if (opcao == 4) {
                resposta = showConfirmDialog(null, "Você realmente deseja sair? ");
                if (resposta == YES_OPTION) {
                    break;
                }
            }
            if (opcao < 1 || opcao > 4) {
                showMessageDialog(null, "Opção inválida");
            } else {
                switch (opcao) {
                    case 1:
                        CadastrarProduto();
                        break;
                    case 2:
                        PesquisarProduto();
                        break;
                    case 3:
                        PesquisarFornecedor();
                        break;

                }
            }

        }


    }

    private Fornecedor PesquisarFornecedor() {
        long cnpj = parseInt(showInputDialog("CNPJ para pesquisa"));
        for (int i = 0; i < indexFornecedor; i++) {
            if (fornecedor[i].getCnpj() == cnpj) {
                return fornecedor[i];
            }
        }
        showConfirmDialog(null, cnpj + "  fornecedor n encontrado");
        return null;
    }

    private void PesquisarProduto() {
        DecimalFormat df = new DecimalFormat("0.00");
        String nome = showInputDialog("Nome do produto");
        String aux = " ";

        for (int i = 0; i < indexProduto; i++) {
            if (produto[i].getNome().equalsIgnoreCase(nome)) {
                aux += "Nome do Produto " + nome + "/n";
                aux += "Preço Unitario R$ " + df.format(produto[i].getValor()) + "/n";
                aux += "quantidade em estoque " + produto[i].getQtdEstoque() + "/n";
                aux += "Nome do Fornecedor " + produto[i].getFornecedor().getNome() + "/n";
                break;
            }

        }
        showMessageDialog(null, aux);

    }

    private void CadastrarProduto() {
        Fornecedor fornecedor = PesquisarFornecedor();
        String nome;
        Double valor;
        int qtdEstoque;
        if (fornecedor == null) {
            fornecedor = CadastrarFornecedor();
        }
        nome = showInputDialog("Nome do produto");
        valor = parseDouble(showInputDialog("Valor Unitario"));
        qtdEstoque = parseInt(showInputDialog("Quantidade em estoque"));
        produto[indexProduto] = new Produto(nome, valor, qtdEstoque, fornecedor);
        indexProduto++;
    }


    private Fornecedor CadastrarFornecedor() {
        String nome;
        long cnpj;
        Fornecedor f = null;
        if (indexFornecedor < fornecedor.length) {
            nome = showInputDialog("Nome do fornecedor");
            cnpj = parseLong(showInputDialog("CNPJ"));
            f = new Fornecedor(nome, cnpj);
            fornecedor[indexFornecedor] = f;



        }
        indexFornecedor++;
        return f;
    }
}




