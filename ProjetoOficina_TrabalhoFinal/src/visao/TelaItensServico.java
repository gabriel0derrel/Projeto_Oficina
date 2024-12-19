/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package visao;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Funcionario;
import modelos.ICrud;
import modelos.ItensServicos;
import modelos.OrdemDeServico;
import modelos.Servicos;
import persistencia.FuncionarioDAO;
import persistencia.ItensServicosDAO;
import persistencia.ServicoDAO;

/**
 *
 * @author Cliente
 */
public class TelaItensServico extends javax.swing.JFrame {
    private ICrud<Servicos> servicoDB = null;
    private OrdemDeServico ordem = null;
    private ICrud<Funcionario> funcionarioDB = null;
    private ICrud<ItensServicos> itensDB = null;
    /**
     * Creates new form TelaItensServico
     * @param ordem
     */
    public TelaItensServico(OrdemDeServico ordem) {
        initComponents();
        this.setLocationRelativeTo(null);
        try {
            this.ordem = ordem;
            servicoDB = new ServicoDAO();
            funcionarioDB = new FuncionarioDAO();
            itensDB = new ItensServicosDAO();
          
            List<Servicos> listaDeServico = servicoDB.listar();
            jComboBox_Servico.removeAllItems();
            for(int pos = 0; pos < listaDeServico.size(); pos++){
                jComboBox_Servico.addItem(listaDeServico.get(pos).toString());
            }
            
            List<Funcionario> listaDeFuncionario = funcionarioDB.listar();
            jComboBox_Funcionario.removeAllItems();
            for(int pos = 0; pos < listaDeFuncionario.size(); pos++){
                jComboBox_Funcionario.addItem(listaDeFuncionario.get(pos).toString());
            }
            jTextField_Quantidade.setText("0");
            jTextField_PrecoTotal.setEditable(false);
            //jTextField_PrecoUnitario.setEditable(false);
            atualizarPrecos();
            mostrarNaGrid();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Construtor Tela: "+erro.getMessage());
        }
        WindowClosingEventHandler();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
    
    private TelaOrdemDeServico tela;
    
    public void conectarComOrdemServico(TelaOrdemDeServico tela2){
        this.tela=tela2;
    }
    
    public void executar(){
        tela.atualizarPrecosOrdem(ordem.getIdOrdem());
    }
    private void WindowClosingEventHandler(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int resposta = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja fechar?","Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION){
                    try{
                        executar();
                        dispose();
                    }catch(Exception erro){
                        JOptionPane.showMessageDialog(null, erro);
                    }
                }
            }
        });
    }
    
    private void atualizarPrecos(){
        try{
            ItensServicos objeto = new ItensServicos();
            objeto.setServico(new Servicos(Integer.parseInt(jComboBox_Servico.getSelectedItem().toString().split("-")[0])));
            int quantidade = 0;
            if(!jTextField_Quantidade.getText().isEmpty()){
                quantidade = Integer.parseInt(jTextField_Quantidade.getText());
            }
            jTextField_PrecoUnitario.setText(servicoDB.consultar(objeto.getServico()).getPrecoServico().replace("R$", "").trim());
            float precoUnitario = Float.parseFloat(jTextField_PrecoUnitario.getText().replace(".", "").replace(",", "."));
            float precoFinal = precoUnitario*quantidade;
            jTextField_PrecoTotal.setText(precoFinal+"");
        } catch(Exception erro){
            JOptionPane.showMessageDialog(rootPane, "Erro: " + erro.getMessage());
        }
    }
    
    private void limparTela(){
        jTextField_ID.setText("");
        jComboBox_Servico.setSelectedIndex(0);
        jComboBox_Funcionario.setSelectedIndex(0);
        jTextField_Quantidade.setText("");
        jTextField_PrecoUnitario.setText("");
        jTextField_PrecoTotal.setText("");
    }
    
    private void mostrarNaGrid(){
        try {
            List<ItensServicos> listaDeItens = null;
            listaDeItens = itensDB.listar();
            DefaultTableModel model =  (DefaultTableModel) jTable_Saida.getModel();
            model.setNumRows(0); 
            if(listaDeItens.isEmpty()) throw new Exception("Lista de Itens Serviço BD Vazia");

            jTable_Saida.setRowHeight(75);
            for(int j = 0; j<7;j++){
                jTable_Saida.getColumnModel().getColumn(j).setCellRenderer(new MultiLineTableCellRenderer());
            }
            for(int pos = 0; pos < listaDeItens.size(); pos++){
                ItensServicos objOrdem = listaDeItens.get(pos);
                if(objOrdem.getOrdem().getIdOrdem() == ordem.getIdOrdem()){
                    String[] saida = new String[7];
                    saida[0] = objOrdem.getIdItensServico()+"";
                    saida[1] = objOrdem.getServico().toString();
                    saida[2] = objOrdem.getOrdem().toString();
                    saida[3] = objOrdem.getFuncionario().toString();
                    saida[4] = objOrdem.getQuantidade()+"";
                    saida[5] = objOrdem.getPrecoUnitario();
                    saida[6] = objOrdem.getPrecoFinal();
                    model.addRow(saida);
                }
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

        jComboBox_Servico = new javax.swing.JComboBox<>();
        jButton_Incluir = new javax.swing.JButton();
        jButton_Alterar = new javax.swing.JButton();
        jButton_Buscar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Saida = new javax.swing.JTable();
        jComboBox_Funcionario = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTextField_Quantidade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField_ID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField_PrecoTotal = new javax.swing.JTextField();
        jTextField_PrecoUnitario = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jComboBox_Servico.setBackground(new java.awt.Color(204, 255, 204));
        jComboBox_Servico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_ServicoActionPerformed(evt);
            }
        });

        jButton_Incluir.setBackground(new java.awt.Color(0, 153, 0));
        jButton_Incluir.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jButton_Incluir.setForeground(new java.awt.Color(0, 255, 204));
        jButton_Incluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figuras/seta.png"))); // NOI18N
        jButton_Incluir.setText("INCLUIR");
        jButton_Incluir.setToolTipText("");
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

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel4.setText("Funcionário");

        jTable_Saida.setBackground(new java.awt.Color(153, 255, 204));
        jTable_Saida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Serviço", "Ordem de Serviço", "Funcionário", "Quantidade", "Preço Unitário", "Preço Total"
            }
        ));
        jTable_Saida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_SaidaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Saida);

        jComboBox_Funcionario.setBackground(new java.awt.Color(204, 255, 204));

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel6.setText("Quantidade");

        jTextField_Quantidade.setBackground(new java.awt.Color(204, 255, 204));

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel5.setText("Preço Unitário");

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setText("ID");

        jTextField_ID.setBackground(new java.awt.Color(204, 255, 204));

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel7.setText("Preço Total");

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setText("Serviço");

        jTextField_PrecoTotal.setBackground(new java.awt.Color(204, 255, 204));

        jTextField_PrecoUnitario.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField_PrecoUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_Funcionario, 0, 1011, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField_ID))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBox_Servico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGap(16, 16, 16)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField_PrecoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel5)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextField_Quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(305, 305, 305)
                            .addComponent(jButton_Incluir)
                            .addGap(69, 69, 69)
                            .addComponent(jButton_Alterar)
                            .addGap(64, 64, 64)
                            .addComponent(jButton_Buscar)
                            .addGap(396, 396, 396)))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jTextField_PrecoUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox_Funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(470, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jTextField_Quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jComboBox_Servico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jTextField_PrecoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_Incluir)
                        .addComponent(jButton_Alterar)
                        .addComponent(jButton_Buscar))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_ServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_ServicoActionPerformed
        // TODO add your handling code here:
        atualizarPrecos();
    }//GEN-LAST:event_jComboBox_ServicoActionPerformed

    private void jButton_IncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_IncluirActionPerformed
        // TODO add your handling code here:
        try {
            atualizarPrecos();
            ItensServicos objeto = new ItensServicos();

            objeto.setIdItensServico(0);

            objeto.setServico(new Servicos(Integer.parseInt(jComboBox_Servico.getSelectedItem().toString().split("-")[0])));
            objeto.setFuncionario(new Funcionario(Integer.parseInt(jComboBox_Funcionario.getSelectedItem().toString().split("-")[0])));
            objeto.setOrdem(ordem);
            objeto.setQuantidade(Integer.parseInt(jTextField_Quantidade.getText()));
            objeto.setPrecoUnitario(jTextField_PrecoUnitario.getText().replace(".", "").replace(",", "."));
            objeto.setPrecoFinal(jTextField_PrecoTotal.getText());


            itensDB.incluir(objeto);
            limparTela();
            mostrarNaGrid();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao incluir Itens de Serviço: " + erro.getMessage());
        }
    }//GEN-LAST:event_jButton_IncluirActionPerformed

    private void jButton_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AlterarActionPerformed
        // TODO add your handling code here:
        try {
            atualizarPrecos();
            ItensServicos objeto = new ItensServicos();

            if(jTextField_ID.getText().isEmpty()) throw new Exception("ID vazio");
            objeto.setIdItensServico(Integer.parseInt(jTextField_ID.getText()));
            objeto.setOrdem(ordem);
            objeto.setServico(new Servicos(Integer.parseInt(jComboBox_Servico.getSelectedItem().toString().split("-")[0])));
            objeto.setFuncionario(new Funcionario(Integer.parseInt(jComboBox_Funcionario.getSelectedItem().toString().split("-")[0])));

            if(jTextField_Quantidade.getText().isEmpty()) throw new Exception("Quantidade Vazia");
            if(!jTextField_Quantidade.getText().matches("\\d+")) throw new Exception("Quantidade precisa ser um inteiro positivo");
            objeto.setQuantidade(Integer.parseInt(jTextField_Quantidade.getText()));
            objeto.setPrecoUnitario(jTextField_PrecoUnitario.getText().replace(".", "").replace(",", "."));
            objeto.setPrecoFinal(jTextField_PrecoTotal.getText());

            itensDB.alterar(objeto);
                    TelaOrdemDeServico telaOrdem = new TelaOrdemDeServico();
        telaOrdem.atualizarPrecosOrdem(ordem.getIdOrdem());
            limparTela();
            mostrarNaGrid();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao alterar Itens de Serviço: " + erro.getMessage());
        }
    }//GEN-LAST:event_jButton_AlterarActionPerformed

    private void jButton_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BuscarActionPerformed
        // TODO add your handling code here:
        try {
            ItensServicos objeto = itensDB.consultar(new ItensServicos(Integer.parseInt(jTextField_ID.getText())));

            jTextField_ID.setText(objeto.getIdItensServico()+"");
            jComboBox_Servico.setSelectedItem(objeto.getServico().toString());
            jComboBox_Funcionario.setSelectedItem(objeto.getFuncionario().toString());
            jTextField_Quantidade.setText(objeto.getQuantidade()+"");
            jTextField_PrecoUnitario.setText(objeto.getPrecoUnitario().replace("R$", "").trim());
            jTextField_PrecoTotal.setText(objeto.getPrecoFinal().replace("R$", "").trim());

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao buscar Itens de Serviço: " + erro.getMessage());
        }
    }//GEN-LAST:event_jButton_BuscarActionPerformed

    private void jTable_SaidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_SaidaMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)jTable_Saida.getModel();
        int selectedRowIndex = jTable_Saida.getSelectedRow();

        jTextField_ID.setText(model.getValueAt(selectedRowIndex, 0).toString());
        jComboBox_Servico.setSelectedItem(model.getValueAt(selectedRowIndex, 1).toString());
        jComboBox_Funcionario.setSelectedItem(model.getValueAt(selectedRowIndex, 3).toString());
        jTextField_Quantidade.setText(model.getValueAt(selectedRowIndex, 4).toString());
        jTextField_PrecoUnitario.setText(model.getValueAt(selectedRowIndex, 5).toString().replace("R$", "").trim());
        jTextField_PrecoTotal.setText(model.getValueAt(selectedRowIndex, 6).toString().replace("R$", "").trim());
    }//GEN-LAST:event_jTable_SaidaMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        TelaOrdemDeServico telaOrdem = new TelaOrdemDeServico();
        telaOrdem.atualizarPrecosOrdem(ordem.getIdOrdem());
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Alterar;
    private javax.swing.JButton jButton_Buscar;
    private javax.swing.JButton jButton_Incluir;
    private javax.swing.JComboBox<String> jComboBox_Funcionario;
    private javax.swing.JComboBox<String> jComboBox_Servico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Saida;
    private javax.swing.JTextField jTextField_ID;
    private javax.swing.JTextField jTextField_PrecoTotal;
    private javax.swing.JTextField jTextField_PrecoUnitario;
    private javax.swing.JTextField jTextField_Quantidade;
    // End of variables declaration//GEN-END:variables
}
