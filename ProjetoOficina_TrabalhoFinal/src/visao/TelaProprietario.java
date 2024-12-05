/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package visao;

import java.awt.event.ItemEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.ICrud;
import modelos.Proprietario;
import modelos.Veiculo;
import persistencia.ClienteDAO;
import persistencia.ProprietarioDAO;
import persistencia.VeiculoDAO;

/**
 *
 * @author misuka
 */
public class TelaProprietario extends javax.swing.JInternalFrame {
    
    private Date dataAtual = null;
    private ICrud<Cliente> clienteDB = null;
    private ICrud<Veiculo> veiculoDB = null;
    private ICrud<Proprietario> proprietarioDB = null;
    
    public TelaProprietario() {
        initComponents();
        try {
            dataAtual = jCalendar_DataInicio.getDate();
            clienteDB = new ClienteDAO();
            veiculoDB = new VeiculoDAO();
            proprietarioDB = new ProprietarioDAO();
          
            List<Cliente> listaDeCliente = clienteDB.listar();
            jComboBox_Cliente.removeAllItems();
            for(int pos = 0; pos < listaDeCliente.size(); pos++){
                jComboBox_Cliente.addItem(listaDeCliente.get(pos).toString());
            }
            List<Veiculo> listaDeVeiculo = veiculoDB.listar();
            jComboBox_Veiculo.removeAllItems();
            for(int pos = 0; pos < listaDeVeiculo.size(); pos++){
                jComboBox_Veiculo.addItem(listaDeVeiculo.get(pos).toString());
            }
            jCalendar_DataFim.setEnabled(false);
            mostrarNaGrid();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Construtor Tela: "+erro.getMessage());
        }
    }

    private void limparTela(){
        jCheckBox_DataFimConfirmação.setSelected(false);
        jCalendar_DataInicio.setDate(dataAtual);
        jCalendar_DataFim.setDate(dataAtual);
        jComboBox_Cliente.setSelectedIndex(0);
        jComboBox_Veiculo.setSelectedIndex(0);
        jTextField_ID.setText("");
    }
    
    private void mostrarNaGrid(){
        try {
            List<Proprietario> listaDeProprietario = null;
            listaDeProprietario = proprietarioDB.listar();
            DefaultTableModel model =  (DefaultTableModel) jTable_Saida.getModel();
            model.setNumRows(0); 
            if(listaDeProprietario.isEmpty()) throw new Exception("Lista de Proprietário BD Vazia");

            jTable_Saida.setRowHeight(75);
            for(int j = 0; j<5;j++){
                jTable_Saida.getColumnModel().getColumn(j).setCellRenderer(new MultiLineTableCellRenderer());
            }
            for(int pos = 0; pos < listaDeProprietario.size(); pos++){
                Proprietario objProprietario = listaDeProprietario.get(pos);
                String[] saida = new String[5];
                saida[0] = objProprietario.getIdProprietario()+"";
                saida[1] = objProprietario.getDataInicio()+"";
                if(objProprietario.getDataFim() != null){
                    saida[2] = objProprietario.getDataFim()+"";
                }
                else{
                    saida[2] = "";
                }
                saida[3] = objProprietario.getCliente().toString();
                saida[4] = objProprietario.getVeiculo().toString();
                model.addRow(saida);
            }  
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox_Cliente = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBox_Veiculo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextField_ID = new javax.swing.JTextField();
        jCalendar_DataInicio = new com.toedter.calendar.JCalendar();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jCheckBox_DataFimConfirmação = new javax.swing.JCheckBox();
        jCalendar_DataFim = new com.toedter.calendar.JCalendar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Saida = new javax.swing.JTable();
        jButton_Incluir = new javax.swing.JButton();
        jButton_Alterar = new javax.swing.JButton();
        jButton_Buscar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setText("Cliente");

        jComboBox_Cliente.setBackground(new java.awt.Color(204, 255, 204));

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setText("Veículo");

        jComboBox_Veiculo.setBackground(new java.awt.Color(204, 255, 204));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel3.setText("ID");

        jTextField_ID.setBackground(new java.awt.Color(204, 255, 204));

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel4.setText("Data Início");

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel5.setText("Data Fim");

        jCheckBox_DataFimConfirmação.setText("Incluir data de fim");
        jCheckBox_DataFimConfirmação.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox_DataFimConfirmaçãoItemStateChanged(evt);
            }
        });

        jTable_Saida.setBackground(new java.awt.Color(153, 255, 204));
        jTable_Saida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Dada de Inicio", "Data de Fim", "Cliente", "Veículo"
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
            jTable_Saida.getColumnModel().getColumn(1).setMinWidth(150);
            jTable_Saida.getColumnModel().getColumn(1).setMaxWidth(150);
            jTable_Saida.getColumnModel().getColumn(2).setMinWidth(150);
            jTable_Saida.getColumnModel().getColumn(2).setMaxWidth(150);
        }

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox_Veiculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox_Cliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_ID))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton_Incluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Alterar)
                                .addGap(16, 16, 16)
                                .addComponent(jButton_Buscar)))
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(356, 356, 356))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jCalendar_DataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox_DataFimConfirmação))
                            .addComponent(jCalendar_DataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(77, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBox_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox_Veiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_Incluir)
                            .addComponent(jButton_Alterar)
                            .addComponent(jButton_Buscar))
                        .addGap(57, 57, 57))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jCheckBox_DataFimConfirmação)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCalendar_DataFim, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                            .addComponent(jCalendar_DataInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_IncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_IncluirActionPerformed
        try {
            Proprietario objeto = new Proprietario();
            objeto.setIdProprietario(0);
            objeto.setDataInicio(jCalendar_DataInicio.getDate());
            if(jCheckBox_DataFimConfirmação.isSelected()){
                if(jCalendar_DataInicio.getDate().after(jCalendar_DataFim.getDate())) throw new Exception("A data inicial não pode ser maior que a final");
                objeto.setDataFim(jCalendar_DataFim.getDate());
            }else{
                objeto.setDataFim(null);
            }
            objeto.setCliente(new Cliente(Integer.parseInt(jComboBox_Cliente.getSelectedItem().toString().split("-")[0].trim())));
            objeto.setVeiculo(new Veiculo(jComboBox_Veiculo.getSelectedItem().toString().split("-")[0]));
            proprietarioDB.incluir(objeto);
            limparTela();
            mostrarNaGrid();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao incluir proprietário: " + erro.getMessage());
        }
    }//GEN-LAST:event_jButton_IncluirActionPerformed

    private void jButton_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AlterarActionPerformed
        try {
            Proprietario objeto = new Proprietario();
            if(jTextField_ID.getText().isEmpty()) throw new Exception("ID Vazio");
            objeto.setIdProprietario(Integer.parseInt(jTextField_ID.getText()));
            objeto.setDataInicio(jCalendar_DataInicio.getDate());
            if(jCheckBox_DataFimConfirmação.isSelected()){
                objeto.setDataFim(jCalendar_DataFim.getDate());
            }else{
                objeto.setDataFim(null);
            }
            objeto.setCliente(new Cliente(Integer.parseInt(jComboBox_Cliente.getSelectedItem().toString().split("-")[0].trim())));
            objeto.setVeiculo(new Veiculo(jComboBox_Veiculo.getSelectedItem().toString().split("-")[0]));
            proprietarioDB.alterar(objeto);
            limparTela();
            mostrarNaGrid();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao alterar proprietário: " + erro.getMessage());
        }
    }//GEN-LAST:event_jButton_AlterarActionPerformed

    private void jButton_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BuscarActionPerformed
        try {
            Proprietario objeto = proprietarioDB.consultar(new Proprietario(Integer.parseInt(jTextField_ID.getText())));

            jTextField_ID.setText(objeto.getIdProprietario()+"");

            jCalendar_DataInicio.setDate(objeto.getDataInicio());

            if(objeto.getDataFim() == null){
                jCalendar_DataFim.setDate(dataAtual);
                jCheckBox_DataFimConfirmação.setSelected(false);
            }
            else{
                jCalendar_DataInicio.setDate(objeto.getDataFim());
                jCheckBox_DataFimConfirmação.setSelected(true);
            }

            jComboBox_Cliente.setSelectedItem(objeto.getCliente().toString());
            jComboBox_Veiculo.setSelectedItem(objeto.getVeiculo().toString());
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao buscar proprietário: " + erro.getMessage());
        }
    }//GEN-LAST:event_jButton_BuscarActionPerformed

    private void jCheckBox_DataFimConfirmaçãoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox_DataFimConfirmaçãoItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange() == ItemEvent.SELECTED || jCheckBox_DataFimConfirmação.isSelected()){
            jCalendar_DataFim.setEnabled(true);
        }else if(evt.getStateChange() == ItemEvent.DESELECTED || !jCheckBox_DataFimConfirmação.isSelected()){
            jCalendar_DataFim.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox_DataFimConfirmaçãoItemStateChanged

    private void jTable_SaidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_SaidaMouseClicked
        DefaultTableModel model = (DefaultTableModel)jTable_Saida.getModel();
        int selectedRowIndex = jTable_Saida.getSelectedRow();
        
        jTextField_ID.setText(model.getValueAt(selectedRowIndex, 0).toString());
        
        String strDataInicio = (String) model.getValueAt(selectedRowIndex, 1);
        String[] vetDataInicio = strDataInicio.split("-");
        Calendar dataInicio = Calendar.getInstance();
        dataInicio.set(Calendar.YEAR, Integer.parseInt(vetDataInicio[0]));
        dataInicio.set(Calendar.MONTH, Integer.parseInt(vetDataInicio[1]) - 1);
        dataInicio.set(Calendar.DAY_OF_MONTH, Integer.parseInt(vetDataInicio[2]));
        jCalendar_DataInicio.setDate(dataInicio.getTime());
        
        
        String strDataFim = (String) model.getValueAt(selectedRowIndex, 2);
        if(strDataFim.isEmpty()){
            jCalendar_DataFim.setDate(dataAtual);
            jCheckBox_DataFimConfirmação.setSelected(false);
        }else{
            String[] vetDataFim = strDataFim.split("-");
            Calendar dataFim = Calendar.getInstance();
            dataFim.set(Calendar.YEAR, Integer.parseInt(vetDataFim[0]));
            dataFim.set(Calendar.MONTH, Integer.parseInt(vetDataFim[1]) - 1);
            dataFim.set(Calendar.DAY_OF_MONTH, Integer.parseInt(vetDataFim[2]));
            jCalendar_DataFim.setDate(dataFim.getTime());
            jCheckBox_DataFimConfirmação.setSelected(true);
        }
        
        jComboBox_Cliente.setSelectedItem(model.getValueAt(selectedRowIndex, 3).toString());
        jComboBox_Veiculo.setSelectedItem(model.getValueAt(selectedRowIndex, 4).toString());
    }//GEN-LAST:event_jTable_SaidaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Alterar;
    private javax.swing.JButton jButton_Buscar;
    private javax.swing.JButton jButton_Incluir;
    private com.toedter.calendar.JCalendar jCalendar_DataFim;
    private com.toedter.calendar.JCalendar jCalendar_DataInicio;
    private javax.swing.JCheckBox jCheckBox_DataFimConfirmação;
    private javax.swing.JComboBox<String> jComboBox_Cliente;
    private javax.swing.JComboBox<String> jComboBox_Veiculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Saida;
    private javax.swing.JTextField jTextField_ID;
    // End of variables declaration//GEN-END:variables
}
