/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package visao;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Acessorio;
import modelos.Cliente;
import modelos.OrdemDeServico;
import modelos.ICrud;
import modelos.ItensPeca;
import modelos.ItensServicos;
import modelos.Oficina;
import modelos.Proprietario;
import modelos.Veiculo;
import modelos.VeiculoAcessorio;
import modelos.enums.StatusEnum;
import pdf.GerarPDF;
import persistencia.ClienteDAO;
import persistencia.ItensPecaDAO;
import persistencia.ItensServicosDAO;
import persistencia.OficinaDAO;
import persistencia.OrdemDeServicoDAO;
import persistencia.ProprietarioDAO;
import persistencia.VeiculoAcessorioDAO;
import persistencia.VeiculoDAO;

/**
 *
 * @author misuka
 */
public class TelaOrdemDeServico extends javax.swing.JInternalFrame {

    private ICrud<Veiculo> veiculoDB = null;
    private ICrud<Oficina> oficinaBD = null;
    private ICrud<VeiculoAcessorio> veiculoAcessorioBD = null;
    private ICrud<ItensServicos> itensServicoBD = null;
    private ICrud<ItensPeca> itensPecaBD = null;
    private ICrud<Proprietario> proprietarioBD = null;
    private ICrud<Cliente> clienteBD = null;
    private ICrud<OrdemDeServico> ordemDB = null;
    
    public TelaOrdemDeServico() {
        initComponents();
        jTextField_ValorPago.setEnabled(false);
        jTextField_Diferenca.setEnabled(false);
        jTextField_ValorTotal.setEnabled(false);
        jButton_itensPeca.setEnabled(false);
        jButton_itensServico.setEnabled(false);
        try {
            veiculoDB = new VeiculoDAO();
            ordemDB = new OrdemDeServicoDAO();
            oficinaBD = new OficinaDAO();
          
            List<Veiculo> listaDeVeiculo = veiculoDB.listar();
            jComboBox_Veiculo.removeAllItems();
            for(int pos = 0; pos < listaDeVeiculo.size(); pos++){
                jComboBox_Veiculo.addItem(listaDeVeiculo.get(pos).toString());
            }
            
            jComboBox_Status.removeAll();
            for(StatusEnum status : StatusEnum.values()){
                jComboBox_Status.addItem(status.getDescricao());
            }
            
            List<Oficina> listaDeOficina = oficinaBD.listar();
            jComboBox_Oficina.removeAllItems();
            for(int pos = 0; pos < listaDeOficina.size(); pos++){
                jComboBox_Oficina.addItem(listaDeOficina.get(pos).toString());
            }
            jTextField_Diferenca.setEditable(false);
            mostrarNaGrid();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Construtor Tela: "+erro.getMessage());
        }
    }
    
    private void limparTela(){
        jComboBox_Veiculo.setSelectedIndex(0);
        jComboBox_Status.setSelectedIndex(0);
        jTextField_ValorTotal.setText("");
        jTextField_ID.setText("");
        jTextField_ValorPago.setText("");
        jTextField_Diferenca.setText("");
    }
    
    public void atualizarPrecosOrdem(int idOrdem){
        try{
            jTextField_ValorPago.setEnabled(false);
                float valorTotal = 0;
                itensPecaBD = new ItensPecaDAO();
                List<ItensPeca> listaDePeca = null;
                listaDePeca = itensPecaBD.listar();
                for(int pos = 0; pos < listaDePeca.size(); pos++){
                    ItensPeca objItensPeca = listaDePeca.get(pos);
                    if(objItensPeca.getOrdem().getIdOrdem() == idOrdem){
                        valorTotal += Float.parseFloat(objItensPeca.getValorTotal().replace("R$", "").replace(".", "").replace(",", ".").trim());
                    }
                }
                itensServicoBD = new ItensServicosDAO();
                List<ItensServicos> listaDeServico = null;
                listaDeServico = itensServicoBD.listar();
                for(int pos = 0; pos < listaDeServico.size(); pos++){
                    ItensServicos objItensServico = listaDeServico.get(pos);
                    if(objItensServico.getOrdem().getIdOrdem() == idOrdem){
                        valorTotal += Float.parseFloat(objItensServico.getPrecoFinal().replace("R$", "").replace(".", "").replace(",", ".").trim());
                    }
                }
                jTextField_ValorTotal.setText(valorTotal+"");
        } catch(Exception erro){
            JOptionPane.showMessageDialog(rootPane, "Erro: " + erro.getMessage());
        }
    }
    
    private void mostrarNaGrid(){
        try {
            jTextField_ValorPago.setEnabled(false);
            List<OrdemDeServico> listaDeOrdem = null;
            listaDeOrdem = ordemDB.listar();
            DefaultTableModel model =  (DefaultTableModel) jTable_Saida.getModel();
            model.setNumRows(0); 
            if(listaDeOrdem.isEmpty()) throw new Exception("Lista de Ordem De Serviço BD Vazia");

            jTable_Saida.setRowHeight(75);
            for(int j = 0; j<8;j++){
                jTable_Saida.getColumnModel().getColumn(j).setCellRenderer(new MultiLineTableCellRenderer());
            }
            for(int pos = 0; pos < listaDeOrdem.size(); pos++){
                OrdemDeServico objOrdem = listaDeOrdem.get(pos);
                String[] saida = new String[8];
                saida[0] = objOrdem.getIdOrdem()+"";
                saida[1] = objOrdem.getStatus();
                saida[2] = objOrdem.getDataInicio()+"";
                saida[3] = objOrdem.getDataFim()+"";
                saida[4] = objOrdem.getValorTotal();
                saida[5] = objOrdem.getValorPago();
                saida[6] = objOrdem.getDiferenca();
                saida[7] = objOrdem.getVeiculo().toString();
                model.addRow(saida);
            }  
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField_ID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox_Status = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextField_ValorTotal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField_ValorPago = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField_Diferenca = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox_Veiculo = new javax.swing.JComboBox<>();
        jButton_Incluir = new javax.swing.JButton();
        jButton_Alterar = new javax.swing.JButton();
        jButton_Buscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Saida = new javax.swing.JTable();
        jButton1_pdf = new javax.swing.JButton();
        jComboBox_Oficina = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jButton_itensPeca = new javax.swing.JButton();
        jTextField_dataInicio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField_dataFim = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton_itensServico = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setText("ID");

        jTextField_ID.setBackground(new java.awt.Color(204, 255, 204));

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setText("Status");

        jComboBox_Status.setBackground(new java.awt.Color(204, 255, 204));
        jComboBox_Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_StatusActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel3.setText("Valor Total");

        jTextField_ValorTotal.setBackground(new java.awt.Color(204, 255, 204));

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel4.setText("Valor Pago");

        jTextField_ValorPago.setBackground(new java.awt.Color(204, 255, 204));

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel5.setText("Diferença");

        jTextField_Diferenca.setBackground(new java.awt.Color(204, 255, 204));

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel6.setText("Veículo");

        jComboBox_Veiculo.setBackground(new java.awt.Color(204, 255, 204));

        jButton_Incluir.setBackground(new java.awt.Color(0, 153, 0));
        jButton_Incluir.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jButton_Incluir.setForeground(new java.awt.Color(0, 255, 204));
        jButton_Incluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figuras/seta.png"))); // NOI18N
        jButton_Incluir.setText("INCLUIR");
        jButton_Incluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_IncluirActionPerformed(evt);
            }
        });

        jButton_Alterar.setBackground(new java.awt.Color(0, 153, 0));
        jButton_Alterar.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jButton_Alterar.setForeground(new java.awt.Color(0, 255, 204));
        jButton_Alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figuras/troca.png"))); // NOI18N
        jButton_Alterar.setText("ALTERAR");
        jButton_Alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AlterarActionPerformed(evt);
            }
        });

        jButton_Buscar.setBackground(new java.awt.Color(0, 153, 0));
        jButton_Buscar.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jButton_Buscar.setForeground(new java.awt.Color(0, 255, 204));
        jButton_Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figuras/procurar.png"))); // NOI18N
        jButton_Buscar.setText("BUSCAR");
        jButton_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BuscarActionPerformed(evt);
            }
        });

        jTable_Saida.setBackground(new java.awt.Color(153, 255, 204));
        jTable_Saida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Status", "Data Inicio", "Data Fim", "Valor Total", "Valor Pago", "Diferença", "Veiculo"
            }
        ));
        jTable_Saida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_SaidaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Saida);
        if (jTable_Saida.getColumnModel().getColumnCount() > 0) {
            jTable_Saida.getColumnModel().getColumn(0).setMinWidth(50);
            jTable_Saida.getColumnModel().getColumn(0).setMaxWidth(50);
            jTable_Saida.getColumnModel().getColumn(1).setMinWidth(120);
            jTable_Saida.getColumnModel().getColumn(1).setMaxWidth(120);
            jTable_Saida.getColumnModel().getColumn(2).setMinWidth(120);
            jTable_Saida.getColumnModel().getColumn(2).setMaxWidth(120);
            jTable_Saida.getColumnModel().getColumn(3).setMinWidth(120);
            jTable_Saida.getColumnModel().getColumn(3).setMaxWidth(120);
            jTable_Saida.getColumnModel().getColumn(4).setMinWidth(90);
            jTable_Saida.getColumnModel().getColumn(4).setMaxWidth(90);
            jTable_Saida.getColumnModel().getColumn(5).setMinWidth(90);
            jTable_Saida.getColumnModel().getColumn(5).setMaxWidth(90);
            jTable_Saida.getColumnModel().getColumn(6).setMinWidth(90);
            jTable_Saida.getColumnModel().getColumn(6).setMaxWidth(90);
        }

        jButton1_pdf.setBackground(new java.awt.Color(0, 153, 0));
        jButton1_pdf.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton1_pdf.setForeground(new java.awt.Color(51, 255, 204));
        jButton1_pdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figuras/pdf.png"))); // NOI18N
        jButton1_pdf.setText("GERAR RELATORIO");
        jButton1_pdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1_pdfActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel9.setText("Oficina");

        jButton_itensPeca.setBackground(new java.awt.Color(51, 153, 0));
        jButton_itensPeca.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jButton_itensPeca.setForeground(new java.awt.Color(204, 255, 204));
        jButton_itensPeca.setText("ADICIONAR ITENS PECA");
        jButton_itensPeca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_itensPecaActionPerformed(evt);
            }
        });

        jTextField_dataInicio.setBackground(new java.awt.Color(204, 255, 204));
        jTextField_dataInicio.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel7.setText("DATA INICIO:");

        jTextField_dataFim.setBackground(new java.awt.Color(204, 255, 204));
        jTextField_dataFim.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel8.setText("DATA FIM:");

        jButton_itensServico.setBackground(new java.awt.Color(51, 153, 0));
        jButton_itensServico.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jButton_itensServico.setForeground(new java.awt.Color(204, 255, 204));
        jButton_itensServico.setText("ADICIONAR ITENS SERVICO");
        jButton_itensServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_itensServicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_ValorPago))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_ValorTotal))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_Status, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_ID))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton_Incluir, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_Alterar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_Diferenca))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_Veiculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jTextField_dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jTextField_dataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton_itensPeca)
                            .addComponent(jButton_itensServico)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_Oficina, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1_pdf)))
                .addContainerGap(245, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox_Status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField_dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_dataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_ValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_itensPeca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField_ValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_itensServico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField_Diferenca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox_Veiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Incluir)
                    .addComponent(jButton_Alterar)
                    .addComponent(jButton_Buscar))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox_Oficina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1_pdf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_IncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_IncluirActionPerformed
        // TODO add your handling code here:
        try {
            OrdemDeServico objeto = new OrdemDeServico();
            objeto.setIdOrdem(0);
            
            objeto.setDataInicio(Calendar.getInstance().getTime());
            objeto.setDataFim(Calendar.getInstance().getTime());
            
            objeto.setVeiculo(new Veiculo(jComboBox_Veiculo.getSelectedItem().toString().split("-")[0]));
            
            objeto.setValorTotal(0+"");
            objeto.setValorPago(0+"");
            objeto.setDiferenca(0+"");
            
            objeto.setStatus(jComboBox_Status.getSelectedItem().toString());
            
            ordemDB.incluir(objeto);
            limparTela();
            mostrarNaGrid();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao incluir Ordem de Serviço: " + erro.getMessage());
        }
    }//GEN-LAST:event_jButton_IncluirActionPerformed

    private void jButton_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AlterarActionPerformed
        // TODO add your handling code here:
        try {
            jButton_itensPeca.setEnabled(false);
            jButton_itensServico.setEnabled(false);
            jTextField_ValorPago.setEnabled(false);
            OrdemDeServico objeto = new OrdemDeServico();
            OrdemDeServico objeto2 = ordemDB.consultar(new OrdemDeServico(Integer.parseInt(jTextField_ID.getText())));
            
            if(jComboBox_Status.getSelectedItem().toString().compareTo("Pago")==0){
            if(jTextField_ID.getText().isEmpty()) throw new Exception("ID vazio");
            jTextField_ValorPago.setEnabled(true);
            objeto.setIdOrdem(Integer.parseInt(jTextField_ID.getText()));
            objeto.setDataInicio(objeto2.getDataInicio());
            objeto.setDataFim(Calendar.getInstance().getTime());
            
            objeto.setVeiculo(new Veiculo(jComboBox_Veiculo.getSelectedItem().toString().split("-")[0]));
            
            if(!jTextField_ValorTotal.getText().replace(",", ".").matches("\\d+(\\.\\d{1,2})?")) throw new Exception("O Valor Total deve ser um número inteiro ou decimal com até duas casas decimais (ex.: 123 ou 123.45)");
                objeto.setValorTotal(jTextField_ValorTotal.getText().replace(",", "."));
                float valorTotal = Float.parseFloat(jTextField_ValorTotal.getText().replace(",", "."));
                if(!jTextField_ValorPago.getText().replace(",", ".").matches("\\d+(\\.\\d{1,2})?")) throw new Exception("O Valor Pago deve ser um número inteiro ou decimal com até duas casas decimais (ex.: 123 ou 123.45)");
                objeto.setValorPago(jTextField_ValorPago.getText().replace(",", "."));
                float valorPago = Float.parseFloat(jTextField_ValorPago.getText().replace(",", "."));
                float diferenca = valorTotal - valorPago;
                if(diferenca < 0.0) throw new Exception("O Valor Pago não pode ser maior que o valor total");
                objeto.setDiferenca(diferenca+"");

                objeto.setStatus(jComboBox_Status.getSelectedItem().toString());
                ordemDB.alterar(objeto);
                limparTela();
                mostrarNaGrid();
                return;
            }
            
            else if(jComboBox_Status.getSelectedItem().toString().compareTo("Orçamento")==0){
                if(jTextField_ID.getText().isEmpty()) throw new Exception("ID vazio");
                objeto.setIdOrdem(Integer.parseInt(jTextField_ID.getText()));
                objeto.setDataInicio(objeto2.getDataInicio());
                objeto.setDataFim(Calendar.getInstance().getTime());
                objeto.setVeiculo(new Veiculo(jComboBox_Veiculo.getSelectedItem().toString().split("-")[0]));
                if(!jTextField_ValorTotal.getText().replace(",", ".").matches("\\d+(\\.\\d{1,2})?")) throw new Exception("O Valor Total deve ser um número inteiro ou decimal com até duas casas decimais (ex.: 123 ou 123.45)");
                objeto.setValorTotal(jTextField_ValorTotal.getText().replace(",", "."));
                objeto.setValorPago(0+"");
                objeto.setDiferenca(0+"");

                objeto.setStatus(jComboBox_Status.getSelectedItem().toString());
                ordemDB.alterar(objeto);
                limparTela();
                mostrarNaGrid();
                return;
            }
            
            else if(jComboBox_Status.getSelectedItem().toString().compareTo("Finalizado")==0){
                if(jTextField_ID.getText().isEmpty()) throw new Exception("ID vazio");
                objeto.setIdOrdem(Integer.parseInt(jTextField_ID.getText()));
                objeto.setDataInicio(objeto2.getDataInicio());
                objeto.setDataFim(Calendar.getInstance().getTime());

                objeto.setVeiculo(new Veiculo(jComboBox_Veiculo.getSelectedItem().toString().split("-")[0]));

                objeto.setValorTotal(jTextField_ValorTotal.getText().replace(",", "."));
                objeto.setValorPago(jTextField_ValorPago.getText().replace(",", "."));
                objeto.setDiferenca(jTextField_Diferenca.getText().replace(",", "."));

                objeto.setStatus(jComboBox_Status.getSelectedItem().toString());
                ordemDB.alterar(objeto);
                limparTela();
                mostrarNaGrid();
                return;
            }
            
            else{
                if(jTextField_ID.getText().isEmpty()) throw new Exception("ID vazio");
                objeto2.setStatus(jComboBox_Status.getSelectedItem().toString());
                objeto.setDataFim(Calendar.getInstance().getTime());
                objeto.setValorPago(0+"");
                objeto.setDiferenca(0+"");
                limparTela();
                mostrarNaGrid();
                return;
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao alterar Ordem de Serviço: " + erro.getMessage());
        }
    }//GEN-LAST:event_jButton_AlterarActionPerformed

    private void jButton_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BuscarActionPerformed
        // TODO add your handling code here:
        try {
            OrdemDeServico objeto = ordemDB.consultar(new OrdemDeServico(Integer.parseInt(jTextField_ID.getText())));

            jTextField_ID.setText(objeto.getIdOrdem()+"");


            jComboBox_Veiculo.setSelectedItem(objeto.getVeiculo().toString());
            jComboBox_Status.setSelectedItem(objeto.getStatus());
            
            jTextField_ValorTotal.setText(objeto.getValorTotal().replace("R$", "").trim());
            jTextField_ValorPago.setText(objeto.getValorPago().replace("R$", "").trim());
            jTextField_Diferenca.setText(objeto.getDiferenca().replace("R$", "").trim());
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao buscar proprietário: " + erro.getMessage());
        }
    }//GEN-LAST:event_jButton_BuscarActionPerformed

    private void jTable_SaidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_SaidaMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)jTable_Saida.getModel();
        int selectedRowIndex = jTable_Saida.getSelectedRow();
        if(jComboBox_Status.getSelectedItem().toString().compareTo("Pago")==0)
            jTextField_ValorPago.setEnabled(true);
        jTextField_ID.setText(model.getValueAt(selectedRowIndex, 0).toString());
        jComboBox_Status.setSelectedItem(model.getValueAt(selectedRowIndex, 1).toString());
        
        String strDataInicio = (String) model.getValueAt(selectedRowIndex, 2);
        jTextField_dataInicio.setText(strDataInicio);
        
        String strDataFim = (String) model.getValueAt(selectedRowIndex, 3);
        jTextField_dataFim.setText(strDataFim);
        
        jTextField_ValorTotal.setText(model.getValueAt(selectedRowIndex, 4).toString().replace("R$", "").trim());
        jTextField_ValorPago.setText(model.getValueAt(selectedRowIndex, 5).toString().replace("R$", "").trim());
        jTextField_Diferenca.setText(model.getValueAt(selectedRowIndex, 6).toString().replace("R$", "").trim());
        jComboBox_Veiculo.setSelectedItem(model.getValueAt(selectedRowIndex, 7).toString());
        jButton_itensPeca.setEnabled(true);
        jButton_itensServico.setEnabled(true);

    }//GEN-LAST:event_jTable_SaidaMouseClicked

    private void jButton1_pdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1_pdfActionPerformed
        // TODO add your handling code here:
        try {
            if (jTextField_ID.getText().isEmpty()) throw new Exception("ID Vazio");
            GerarPDF pdf = new GerarPDF();
            Cliente cliente = null;
            proprietarioBD = new ProprietarioDAO();
            clienteBD = new ClienteDAO();
            List<Proprietario> listaDeProprietario = null;
            listaDeProprietario = proprietarioBD.listar();
            for(int pos = 0; pos < listaDeProprietario.size(); pos++){
                Proprietario objProprietario = listaDeProprietario.get(pos);
                if(objProprietario.getDataFim()== null && objProprietario.getVeiculo().getPlaca().compareTo(jComboBox_Veiculo.getSelectedItem().toString().split("-")[0])==0){
                    cliente = clienteBD.consultar(new Cliente(objProprietario.getCliente().getIdCliente()));
                }
            }         
            List<Acessorio> listaDeAcessorio = null;
            listaDeAcessorio = new LinkedList<>();
            veiculoAcessorioBD = new VeiculoAcessorioDAO();
            List<VeiculoAcessorio> listaDeVeiculoAcessorio = null;
            listaDeVeiculoAcessorio = veiculoAcessorioBD.listar();
            for(int pos = 0; pos < listaDeVeiculoAcessorio.size(); pos++){
                VeiculoAcessorio objVeiculoAcessorio = listaDeVeiculoAcessorio.get(pos);
                if(objVeiculoAcessorio.getVeiculo().getPlaca().compareTo(jComboBox_Veiculo.getSelectedItem().toString().split("-")[0])==0){
                    listaDeAcessorio.add(objVeiculoAcessorio.getAcessorio());
                }
            }
            List<ItensPeca> listaDePecasOrdemAtual = null;
            listaDePecasOrdemAtual = new LinkedList<>();
            itensPecaBD = new ItensPecaDAO();
            List<ItensPeca> listaDeBuscaOrdemPecas = null;
            listaDeBuscaOrdemPecas = itensPecaBD.listar();
            for(int pos = 0; pos < listaDeBuscaOrdemPecas.size(); pos++){
                ItensPeca objItensPecas = listaDeBuscaOrdemPecas.get(pos);
                if(objItensPecas.getOrdem().getIdOrdem() == Integer.parseInt(jTextField_ID.getText())){
                    listaDePecasOrdemAtual.add(objItensPecas);
                }
            }
            List<ItensServicos> listaDeServicoOrdemAtual = null;
            listaDeServicoOrdemAtual = new LinkedList<>();
            itensServicoBD = new ItensServicosDAO();
            List<ItensServicos> listaDeBuscaOrdemServico = null;
            listaDeBuscaOrdemServico = itensServicoBD.listar();
            for(int pos = 0; pos < listaDeBuscaOrdemServico.size(); pos++){
                ItensServicos objItensServico = listaDeBuscaOrdemServico.get(pos);
                if(objItensServico.getOrdem().getIdOrdem() == Integer.parseInt(jTextField_ID.getText())){
                    listaDeServicoOrdemAtual.add(objItensServico);
                }
            }
            
            Oficina oficina = (oficinaBD.consultar(new Oficina(jComboBox_Oficina.getSelectedItem().toString().split("-")[0])));
            Veiculo veiculo = (veiculoDB.consultar(new Veiculo(jComboBox_Veiculo.getSelectedItem().toString().split("-")[0])));
            OrdemDeServico ordem = new OrdemDeServico(Integer.parseInt(jTextField_ID.getText()));
            if(cliente == null) throw new Exception("Não foi encontrado um cliente que seja propriétário do veículo.");
            pdf.gerarPdf(ordem,veiculo,cliente,listaDeAcessorio,listaDePecasOrdemAtual,listaDeServicoOrdemAtual,oficina);
            JOptionPane.showMessageDialog(rootPane, "PDF gerado com sucesso!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao Gerar PDF: " + erro.getMessage());
        }
    }//GEN-LAST:event_jButton1_pdfActionPerformed

    private void jButton_itensPecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_itensPecaActionPerformed
            try {
            OrdemDeServico ordem = new OrdemDeServico(Integer.parseInt(jTextField_ID.getText()));
            TelaItensPecas telaItensPeca = new TelaItensPecas(ordem);
            telaItensPeca.setVisible(true);
            telaItensPeca.conectarComOrdemServico(this);
            telaItensPeca.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
        atualizarPrecosOrdem(Integer.parseInt(jTextField_ID.getText()));
    }//GEN-LAST:event_jButton_itensPecaActionPerformed

    private void jButton_itensServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_itensServicoActionPerformed
        // TODO add your handling code here:
            try {
            OrdemDeServico ordem = new OrdemDeServico(Integer.parseInt(jTextField_ID.getText()));
            TelaItensServico telaItensServico = new TelaItensServico(ordem);
            telaItensServico.setVisible(true);
            telaItensServico.conectarComOrdemServico(this);
            telaItensServico.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
    }//GEN-LAST:event_jButton_itensServicoActionPerformed

    public void executarAtualizacaoDosPrecosServico(){
        atualizarPrecosOrdem(Integer.parseInt(jTextField_ID.getText()));
    }
    private void jComboBox_StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_StatusActionPerformed
        // TODO add your handling code here:
        try {
        if(jComboBox_Status.getSelectedItem().toString().compareTo("Pago")==0)
            jTextField_ValorPago.setEnabled(true);
        else
        jTextField_ValorPago.setEnabled(false);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
    }//GEN-LAST:event_jComboBox_StatusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1_pdf;
    private javax.swing.JButton jButton_Alterar;
    private javax.swing.JButton jButton_Buscar;
    private javax.swing.JButton jButton_Incluir;
    private javax.swing.JButton jButton_itensPeca;
    private javax.swing.JButton jButton_itensServico;
    private javax.swing.JComboBox<String> jComboBox_Oficina;
    private javax.swing.JComboBox<String> jComboBox_Status;
    private javax.swing.JComboBox<String> jComboBox_Veiculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Saida;
    private javax.swing.JTextField jTextField_Diferenca;
    private javax.swing.JTextField jTextField_ID;
    private javax.swing.JTextField jTextField_ValorPago;
    private javax.swing.JTextField jTextField_ValorTotal;
    private javax.swing.JTextField jTextField_dataFim;
    private javax.swing.JTextField jTextField_dataInicio;
    // End of variables declaration//GEN-END:variables
}
