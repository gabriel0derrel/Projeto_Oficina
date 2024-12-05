/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import modelos.*;
import persistencia.*;
/**
 *
 * @author lusra
 */
public class GerarPDF {
    public GerarPDF(){
        
    }
    
   public void gerarPdf(OrdemDeServico ordem,Veiculo veiculo,Cliente cliente,java.util.List<Acessorio> listaDeAcessorio,java.util.List<ItensPeca> listaDePecasOrdemAtual,java.util.List<ItensServicos> listaDeServicoOrdemAtual,Oficina oficina) throws Exception{
        try {
            // Cria o documento PDF
            OrdemDeServicoDAO ordemDAO = new OrdemDeServicoDAO();
            ordem = ordemDAO.consultar(ordem);
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream("src/pdf/Orcamento.pdf"));

            document.open();

            // Fontes
            Font fontTitulo = new Font(Font.HELVETICA, 16, Font.BOLD);
            Font fontSubTitulo = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font fontTexto = new Font(Font.HELVETICA, 10, Font.NORMAL);
            Font fontTabela = new Font(Font.HELVETICA, 10, Font.BOLD);

            // Cabeçalho
            Paragraph header = new Paragraph(oficina.getNome(), fontTitulo);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);

            Paragraph endereco = new Paragraph("Logradouro:"+oficina.getEndereco().getLogradouro()+"\n"+"Numero:"+oficina.getEndereco().getNumeroEndereco()+"\n"+
                                                "Cep:"+oficina.getEndereco().getCep()+"\n"+"Bairro:"+oficina.getEndereco().getBairro()+"\n"+"Complemento:"+oficina.getEndereco().getComplemento()+"\n"+
                                                "Cidade:"+oficina.getEndereco().getCidade()+"-"+oficina.getEndereco().getEstado()+"\n"+oficina.getTelefone1().toString()+"   "+oficina.getTelefone2().toString()+"\n"+oficina.getIdentificador_Email(), fontTexto);
            endereco.setAlignment(Element.ALIGN_CENTER);
            endereco.setSpacingAfter(2);
            document.add(endereco);

            // Título do documento
            Paragraph titulo = new Paragraph("Orçamento da Ordem de Serviço Nº " + ordem.getIdOrdem(), fontSubTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            document.add(titulo);

            // Informações do Cliente
            Paragraph subtituloTabelaCliente = new Paragraph("Cliente", fontSubTitulo);
            subtituloTabelaCliente.setAlignment(Element.ALIGN_CENTER);
            subtituloTabelaCliente.setSpacingAfter(10);
            document.add(subtituloTabelaCliente);
            
            PdfPTable tabelaCliente = new PdfPTable(2);
            tabelaCliente.setWidthPercentage(100);
            tabelaCliente.setSpacingAfter(10);
            tabelaCliente.setWidths(new int[]{1, 3}); // Largura relativa das colunas

            tabelaCliente.addCell(new Phrase("Cliente:", fontTabela));
            tabelaCliente.addCell(new Phrase(cliente.getNome(), fontTexto));
            tabelaCliente.addCell(new Phrase("Endereço:", fontTabela));
            tabelaCliente.addCell(new Phrase(cliente.getEndereco().toString(), fontTexto));
            tabelaCliente.addCell(new Phrase("CPF/CNPJ:", fontTabela));
            tabelaCliente.addCell(new Phrase(cliente.getCpf()+","+cliente.getCnpj(), fontTexto));
            tabelaCliente.addCell(new Phrase("Fones:", fontTabela));
            tabelaCliente.addCell(new Phrase(cliente.getTelefone1().toString()+"--"+cliente.getTelefone2().toString(), fontTexto));
            tabelaCliente.addCell(new Phrase("Email:", fontTabela));
            tabelaCliente.addCell(new Phrase(cliente.getEmail(), fontTexto));
            tabelaCliente.addCell(new Phrase("Ins. Est:", fontTabela));
            tabelaCliente.addCell(new Phrase(cliente.getInscricaoEstadual(), fontTexto));
            tabelaCliente.addCell(new Phrase("Contato:", fontTabela));
            tabelaCliente.addCell(new Phrase(cliente.getContato(), fontTexto));
            document.add(tabelaCliente);

            // Informações do Veículo
            Paragraph subtituloTabelaVeiculo = new Paragraph("Veículo", fontSubTitulo);
            subtituloTabelaVeiculo.setAlignment(Element.ALIGN_CENTER);
            subtituloTabelaVeiculo.setSpacingAfter(10);
            document.add(subtituloTabelaVeiculo);
            
            PdfPTable tabelaVeiculo = new PdfPTable(2);
            tabelaVeiculo.setWidthPercentage(100);
            tabelaVeiculo.setSpacingAfter(10);
            tabelaVeiculo.setWidths(new int[]{1, 3});

            tabelaVeiculo.addCell(new Phrase("Veículo/Ano:", fontTabela));
            tabelaVeiculo.addCell(new Phrase(veiculo.getModelo().getDescricao()+veiculo.getAnoModelo(), fontTexto));
            tabelaVeiculo.addCell(new Phrase("Marca:", fontTabela));
            tabelaVeiculo.addCell(new Phrase(veiculo.getModelo().getMarca().getDescricao(), fontTexto));
            tabelaVeiculo.addCell(new Phrase("Placa:", fontTabela));
            tabelaVeiculo.addCell(new Phrase(veiculo.getPlaca(), fontTexto));
            tabelaVeiculo.addCell(new Phrase("Nº Chassis:", fontTabela));
            tabelaVeiculo.addCell(new Phrase(veiculo.getChassi(), fontTexto));
            tabelaVeiculo.addCell(new Phrase("Nº Patrimônio:", fontTabela));
            tabelaVeiculo.addCell(new Phrase(veiculo.getPatrimonio()+"", fontTexto));
            tabelaVeiculo.addCell(new Phrase("Kilometragem:", fontTabela));
            tabelaVeiculo.addCell(new Phrase(veiculo.getKilometragem()+"", fontTexto));
            tabelaVeiculo.addCell(new Phrase("Entrada:", fontTabela));
            tabelaVeiculo.addCell(new Phrase(veiculo.getDataRegistro().toString(), fontTexto));
            document.add(tabelaVeiculo);
            
            // Acessórios
            Paragraph subtituloTabelaAcessorios = new Paragraph("Acessórios", fontSubTitulo);
            subtituloTabelaAcessorios.setAlignment(Element.ALIGN_CENTER);
            subtituloTabelaAcessorios.setSpacingAfter(10);
            document.add(subtituloTabelaAcessorios);
            
            PdfPTable tabelaAcessorios = new PdfPTable(1);
            tabelaAcessorios.setWidthPercentage(100);
            tabelaAcessorios.setSpacingAfter(10);
            tabelaAcessorios.setWidths(new int[]{1});
            for(int pos = 0; pos < listaDeAcessorio.size(); pos++){
            tabelaAcessorios.addCell(new Phrase(listaDeAcessorio.get(pos).getDescricao(), fontTabela));
            } 
            
            document.add(tabelaAcessorios);

            // Tabela de Peças e Serviços
            Paragraph subtituloTabela = new Paragraph("Valores do Orçamento", fontSubTitulo);
            subtituloTabela.setAlignment(Element.ALIGN_CENTER);
            subtituloTabela.setSpacingAfter(10);
            document.add(subtituloTabela);

            PdfPTable tabelaPecas = new PdfPTable(4);
            tabelaPecas.setWidthPercentage(100);
            tabelaPecas.setSpacingAfter(10);
            tabelaPecas.setWidths(new int[]{5, 1, 1, 1}); // Largura relativa das colunas

            tabelaPecas.addCell(new Phrase("Peças a substituir", fontTabela));
            tabelaPecas.addCell(new Phrase("Qtd", fontTabela));
            tabelaPecas.addCell(new Phrase("Vl Un", fontTabela));
            tabelaPecas.addCell(new Phrase("Total", fontTabela));
            for(int pos = 0; pos < listaDePecasOrdemAtual.size(); pos++){
            tabelaPecas.addCell(new Phrase(listaDePecasOrdemAtual.get(pos).getPeca().getIdPeca()+"  "+listaDePecasOrdemAtual.get(pos).getPeca().getDescricaoPeca()+"    ("+listaDePecasOrdemAtual.get(pos).getPeca().getCodigoFabricante()+")", fontTexto));
            tabelaPecas.addCell(new Phrase(listaDePecasOrdemAtual.get(pos).getQuantidade()+"", fontTexto));
            tabelaPecas.addCell(new Phrase(listaDePecasOrdemAtual.get(pos).getValorUnitario(), fontTexto));
            tabelaPecas.addCell(new Phrase(listaDePecasOrdemAtual.get(pos).getValorTotal()+"", fontTexto));
            } 

            document.add(tabelaPecas);

            // Serviços a Executar
            PdfPTable tabelaServicos = new PdfPTable(6);
            tabelaServicos.setWidthPercentage(100);
            tabelaServicos.setSpacingAfter(10);
            tabelaServicos.setWidths(new int[]{3, 2, 1, 1, 1, 1}); // Largura relativa das colunas
            
            tabelaServicos.addCell(new Phrase("Serviços a executar", fontTabela));
            tabelaServicos.addCell(new Phrase("Responsável", fontTabela));
            tabelaServicos.addCell(new Phrase("Início", fontTabela));
            tabelaServicos.addCell(new Phrase("Fim", fontTabela));
            tabelaServicos.addCell(new Phrase("Qtd", fontTabela));
            tabelaServicos.addCell(new Phrase("Total", fontTabela));
            
            for(int pos = 0; pos < listaDeServicoOrdemAtual.size(); pos++){
            tabelaServicos.addCell(new Phrase(listaDeServicoOrdemAtual.get(pos).getServico().getDescricaoServico(), fontTexto));
            tabelaServicos.addCell(new Phrase(listaDeServicoOrdemAtual.get(pos).getFuncionario().getNome(), fontTexto));
            tabelaServicos.addCell(new Phrase(listaDeServicoOrdemAtual.get(pos).getOrdem().getDataInicio()+"", fontTexto));
            tabelaServicos.addCell(new Phrase(listaDeServicoOrdemAtual.get(pos).getOrdem().getDataFim()+"", fontTexto));
            tabelaServicos.addCell(new Phrase(listaDeServicoOrdemAtual.get(pos).getQuantidade()+"", fontTexto));
            tabelaServicos.addCell(new Phrase(listaDeServicoOrdemAtual.get(pos).getPrecoFinal(), fontTexto));
            }
            document.add(tabelaServicos);

            // Totais
            PdfPTable tabelaTotais = new PdfPTable(2);
            tabelaTotais.setWidthPercentage(50); // Ocupa 50% da largura da página
            tabelaTotais.setHorizontalAlignment(Element.ALIGN_RIGHT); // Alinha à direita
            tabelaTotais.setSpacingAfter(20);

            tabelaTotais.addCell(new Phrase("Valor Pago", fontTabela));
            tabelaTotais.addCell(new Phrase(ordem.getValorPago(), fontTexto));
            tabelaTotais.addCell(new Phrase("Diferenca", fontTabela));
            tabelaTotais.addCell(new Phrase(ordem.getDiferenca(), fontTexto));
            tabelaTotais.addCell(new Phrase("TOTAL", fontTabela));
            tabelaTotais.addCell(new Phrase(ordem.getValorTotal(), fontTexto));

            document.add(tabelaTotais);

            // Nota de Encerramento
            Paragraph notaEncerramento = new Paragraph("*** ORÇAMENTO " + ordem.getStatus().toUpperCase() + " ***", fontTexto);
            notaEncerramento.setAlignment(Element.ALIGN_CENTER);
            document.add(notaEncerramento);

            document.close();
            
            // Verifica se o Desktop é suportado no sistema operacional
            if (Desktop.isDesktopSupported()) {
                try {
                    // Localiza o arquivo PDF recém-criado
                    File pdfFile = new File("src/pdf/Orcamento.pdf"); // Substitua pelo caminho real do arquivo
                    if (pdfFile.exists()) {
                        // Abre o PDF com o leitor padrão do sistema
                        Desktop.getDesktop().open(pdfFile);
                    } else {
                        throw new Exception("O arquivo PDF não foi encontrado.");
                    }
                } catch (IOException e) {
                    throw new Exception("Erro ao abrir o PDF: " + e.getMessage());
                }
            } else {
                throw new Exception("A funcionalidade Desktop não é suportada neste sistema operacional.");
            }
            throw new Exception("PDF gerado com sucesso!");
        } catch (Exception erro) {
            throw new Exception("Gerar PDF: " + erro);
        }
    }
}
