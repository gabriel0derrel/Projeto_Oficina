/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package visao;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.*;
import persistencia.MarcaDAO;

/**
 *
 * @author lusra
 */
public class TelaMarca extends javax.swing.JInternalFrame {
    private ICrud<Marca> MarcaBD = null;
    
    public TelaMarca() {
        initComponents();
        try {
          MarcaBD = new MarcaDAO();
          mostrarMarcaNaGrid();
        } catch (Exception erro) {
          JOptionPane.showMessageDialog(null, "Construtor Tela: "+erro.getMessage());
        }
    }
    private void limparTela(){
      jTextField1_ID.setText("");
      jTextField1_descricao.setText("");
    }
    
    private void mostrarMarcaNaGrid(){
    try {
      List<Marca> listaDeMarca = new ArrayList<>();
      listaDeMarca = null;
      listaDeMarca = MarcaBD.listar();
      DefaultTableModel model =  (DefaultTableModel) jTableServicos.getModel();
      model.setNumRows(0); 
      if(listaDeMarca.isEmpty()) 
        throw new Exception("Lista de Marca BD Vazia");
      for(int j = 0; j<2;j++){
          jTableServicos.getColumnModel().getColumn(j);
           }
      for(int pos = 0; pos < listaDeMarca.size(); pos++){
        Marca objMarca = listaDeMarca.get(pos);
        String[] saida = new String[2];
          saida[0] = objMarca.getIdMarca()+ "";
          saida[1] = objMarca.getDescricao()+ "";
        model.addRow(saida);
      }  
    } catch (Exception erro) {
        JOptionPane.showMessageDialog(rootPane, erro.getMessage());
      }    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1_ID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1_descricao = new javax.swing.JTextField();
        jButtonIncluir = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButton_buscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableServicos = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel1.setText("CADASTRO DE MARCA");

        jTextField1_ID.setBackground(new java.awt.Color(204, 255, 204));
        jTextField1_ID.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jTextField1_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1_IDActionPerformed(evt);
            }
        });
        jTextField1_ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1_IDKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel6.setText("Descrição:");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel8.setText("ID");

        jTextField1_descricao.setBackground(new java.awt.Color(204, 255, 204));
        jTextField1_descricao.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jTextField1_descricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1_descricaoActionPerformed(evt);
            }
        });
        jTextField1_descricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1_descricaoKeyReleased(evt);
            }
        });

        jButtonIncluir.setBackground(new java.awt.Color(0, 153, 0));
        jButtonIncluir.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButtonIncluir.setForeground(new java.awt.Color(0, 255, 204));
        jButtonIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figuras/seta.png"))); // NOI18N
        jButtonIncluir.setText("INCLUIR");
        jButtonIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncluirActionPerformed(evt);
            }
        });

        jButtonAlterar.setBackground(new java.awt.Color(0, 153, 0));
        jButtonAlterar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButtonAlterar.setForeground(new java.awt.Color(0, 255, 204));
        jButtonAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figuras/troca.png"))); // NOI18N
        jButtonAlterar.setText("ALTERAR");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButton_buscar.setBackground(new java.awt.Color(0, 153, 0));
        jButton_buscar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton_buscar.setForeground(new java.awt.Color(0, 255, 204));
        jButton_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figuras/procurar.png"))); // NOI18N
        jButton_buscar.setText("BUSCAR");
        jButton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_buscarActionPerformed(evt);
            }
        });

        jTableServicos.setBackground(new java.awt.Color(153, 255, 204));
        jTableServicos.setFont(new java.awt.Font("Helvetica Neue", 3, 12)); // NOI18N
        jTableServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "DESCRICAO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableServicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableServicosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableServicos);
        if (jTableServicos.getColumnModel().getColumnCount() > 0) {
            jTableServicos.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(376, 376, 376))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField1_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 375, Short.MAX_VALUE)
                        .addComponent(jButtonIncluir)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAlterar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_buscar)
                        .addGap(443, 443, 443))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField1_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonIncluir)
                            .addComponent(jButtonAlterar)
                            .addComponent(jButton_buscar)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirActionPerformed
        try {
            String descricao = jTextField1_descricao.getText().toUpperCase();
            if (descricao.isEmpty()) {
                throw new Exception("O campo de descrição não pode estar vazio.");
            }
            Marca objMarca = new Marca(0 , descricao);
            MarcaBD.incluir(objMarca);
            limparTela();
            mostrarMarcaNaGrid();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Incluir Visao: "+erro.getMessage());
        }
    }//GEN-LAST:event_jButtonIncluirActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        try {
            String descricao = jTextField1_descricao.getText().toUpperCase();
            if (descricao.isEmpty()) {
                throw new Exception("O campo de descrição não pode estar vazio.");
            }
            Marca objMarca = new Marca(Integer.parseInt(jTextField1_ID.getText()), descricao);
            MarcaBD.alterar(objMarca);
            limparTela();
            mostrarMarcaNaGrid();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Alterar Visao: "+erro.getMessage());
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jTextField1_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1_IDActionPerformed

    }//GEN-LAST:event_jTextField1_IDActionPerformed

    private void jTextField1_IDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1_IDKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1_IDKeyReleased

    private void jButton_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_buscarActionPerformed
        // TODO add your handling code here:
        try {
        if(jTextField1_ID.getText().isEmpty()) throw new Exception("Id vazio");
            Marca objMarca = new Marca(Integer.parseInt(jTextField1_ID.getText()));
            objMarca = (MarcaBD.consultar(objMarca));
      
            jTextField1_ID.setText(objMarca.getIdMarca()+"");
            jTextField1_descricao.setText(objMarca.getDescricao());
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Buscar Visao: "+erro.getMessage());
        }
    }//GEN-LAST:event_jButton_buscarActionPerformed

    private void jTextField1_descricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1_descricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1_descricaoActionPerformed

    private void jTextField1_descricaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1_descricaoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1_descricaoKeyReleased

    private void jTableServicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableServicosMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)jTableServicos.getModel();
        int selectedRowIndex = jTableServicos.getSelectedRow();
        
        jTextField1_ID.setText(model.getValueAt(selectedRowIndex, 0).toString());
        jTextField1_descricao.setText(model.getValueAt(selectedRowIndex, 1).toString());
    }//GEN-LAST:event_jTableServicosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonIncluir;
    private javax.swing.JButton jButton_buscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableServicos;
    private javax.swing.JTextField jTextField1_ID;
    private javax.swing.JTextField jTextField1_descricao;
    // End of variables declaration//GEN-END:variables
}
