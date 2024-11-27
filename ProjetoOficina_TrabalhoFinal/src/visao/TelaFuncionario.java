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
import persistencia.FuncionarioDAO;

/**
 *
 * @author lusra
 */
public class TelaFuncionario extends javax.swing.JInternalFrame {
    private ICrud<Funcionario> FuncionarioBD = null;
    
    public TelaFuncionario() {
        initComponents();
        try {
          FuncionarioBD = new FuncionarioDAO();
          mostrarFuncionarioNaGrid();
        } catch (Exception erro) {
          JOptionPane.showMessageDialog(null, "Construtor Tela: "+erro.getMessage());
        }
    }
    private void limparTela(){
      jTextField1_ID.setText("");
      jTextField1_nome.setText("");
      jTextField3_email.setText("");
      jFormattedTextField1_telefone.setValue(null);
    }
    
    private void mostrarFuncionarioNaGrid(){
    try {
      List<Funcionario> listaDeFuncionario = new ArrayList<>();
      listaDeFuncionario = null;
      listaDeFuncionario = FuncionarioBD.listar();
      DefaultTableModel model =  (DefaultTableModel) jTableServicos.getModel();
      model.setNumRows(0); 
      if(listaDeFuncionario.isEmpty()) 
        throw new Exception("Lista de Funcionario BD Vazia");
      for(int j = 0; j<4;j++){
          jTableServicos.getColumnModel().getColumn(j);
           }
      for(int pos = 0; pos < listaDeFuncionario.size(); pos++){
        Funcionario objFuncionario = listaDeFuncionario.get(pos);
        String[] saida = new String[4];
          saida[0] = objFuncionario.getIdFuncionario()+ "";
          saida[1] = objFuncionario.getNome() + "";
          saida[2] = objFuncionario.getEmail().toString()+ "";
          saida[3] = objFuncionario.getTelefone().toString()+ "";
        model.addRow(saida);
      }  
    } catch (Exception erro) {
        JOptionPane.showMessageDialog(rootPane, erro.getMessage());
      }    
    }
    
    private void mostrarFuncionarioNaGridBusca(Funcionario objFuncionario){
      try {

        DefaultTableModel model =  (DefaultTableModel) jTableServicos.getModel();
        model.setNumRows(0); 
        if(objFuncionario == null) 
          throw new Exception("Lista de Busca BD Vazia");
        for(int j = 0; j<4;j++){
            jTableServicos.getColumnModel().getColumn(j);
             }
          String[] saida = new String[4];
            saida[0] = objFuncionario.getIdFuncionario()+ "";
            saida[1] = objFuncionario.getNome() + "";
            saida[2] = objFuncionario.getEmail().toString()+ "";
            saida[3] = objFuncionario.getTelefone().toString()+ "";
          model.addRow(saida);
      } catch (Exception erro) {
          JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButtonIncluir = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jTextField3_email = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableServicos = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextField1_telefone = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField1_ID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton_buscar = new javax.swing.JButton();
        jButtonListar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField1_nome = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 3, 36)); // NOI18N
        jLabel1.setText("CADASTRO DE FUNCIONARIO");

        jButtonIncluir.setFont(new java.awt.Font("Helvetica Neue", 3, 18)); // NOI18N
        jButtonIncluir.setText("INCLUIR");
        jButtonIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncluirActionPerformed(evt);
            }
        });

        jButtonAlterar.setFont(new java.awt.Font("Helvetica Neue", 3, 18)); // NOI18N
        jButtonAlterar.setText("ALTERAR");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jTextField3_email.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N

        jTableServicos.setFont(new java.awt.Font("Helvetica Neue", 3, 12)); // NOI18N
        jTableServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "EMAIL", "TELEFONE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableServicos);

        jLabel7.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel7.setText("Email");

        try {
            jFormattedTextField1_telefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("+##(##)#########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField1_telefone.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jFormattedTextField1_telefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1_telefoneActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel5.setText("Telefone");

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

        jLabel6.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel6.setText("Nome");

        jButton_buscar.setFont(new java.awt.Font("Helvetica Neue", 3, 18)); // NOI18N
        jButton_buscar.setText("BUSCAR");
        jButton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_buscarActionPerformed(evt);
            }
        });

        jButtonListar.setFont(new java.awt.Font("Helvetica Neue", 3, 18)); // NOI18N
        jButtonListar.setText("LISTAR");
        jButtonListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel8.setText("ID");

        jTextField1_nome.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jTextField1_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1_nomeActionPerformed(evt);
            }
        });
        jTextField1_nome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1_nomeKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(341, 341, 341)
                .addComponent(jLabel1)
                .addGap(0, 240, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jFormattedTextField1_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonIncluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_buscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonListar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jTextField3_email, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField1_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField1_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jFormattedTextField1_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField3_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonIncluir)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButton_buscar)
                    .addComponent(jButtonListar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirActionPerformed
        try {
            String numeroTele = jFormattedTextField1_telefone.getText().trim();
            String[] telefonePartes = numeroTele.split("[()]+");
            int ddi = Integer.parseInt(telefonePartes[0].substring(1));
            int ddd = Integer.parseInt(telefonePartes[1]);
            int numero = Integer.parseInt(telefonePartes[2]);
            Telefone Telefone = new Telefone(ddi,ddd,numero);
            Funcionario objFuncionario = new Funcionario(0 ,jTextField1_nome.getText().toUpperCase(), jTextField3_email.getText(), Telefone);
            FuncionarioBD.incluir(objFuncionario);
            limparTela();
            mostrarFuncionarioNaGrid();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Incluir Visao: "+erro.getMessage());
        }
    }//GEN-LAST:event_jButtonIncluirActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        try {
            String numeroTele = jFormattedTextField1_telefone.getText().trim();
            String[] telefonePartes = numeroTele.split("[()]+");
            int ddi = Integer.parseInt(telefonePartes[0].substring(1));
            int ddd = Integer.parseInt(telefonePartes[1]);
            int numero = Integer.parseInt(telefonePartes[2]);
            Telefone Telefone = new Telefone(ddi,ddd,numero);
            Funcionario objFuncionario = new Funcionario(Integer.parseInt(jTextField1_ID.getText()), jTextField1_nome.getText().toUpperCase(), jTextField3_email.getText(), Telefone);
            FuncionarioBD.alterar(objFuncionario);
            limparTela();
            mostrarFuncionarioNaGrid();
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
            Funcionario buscarFunc = new Funcionario(Integer.parseInt(jTextField1_ID.getText()));
            mostrarFuncionarioNaGridBusca(FuncionarioBD.consultar(buscarFunc));
            limparTela();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Buscar Visao: "+erro.getMessage());
        }
    }//GEN-LAST:event_jButton_buscarActionPerformed

    private void jButtonListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListarActionPerformed
        // TODO add your handling code here:
        mostrarFuncionarioNaGrid();
    }//GEN-LAST:event_jButtonListarActionPerformed

    private void jTextField1_nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1_nomeActionPerformed

    private void jTextField1_nomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1_nomeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1_nomeKeyReleased

    private void jFormattedTextField1_telefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1_telefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1_telefoneActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonIncluir;
    private javax.swing.JButton jButtonListar;
    private javax.swing.JButton jButton_buscar;
    private javax.swing.JFormattedTextField jFormattedTextField1_telefone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableServicos;
    private javax.swing.JTextField jTextField1_ID;
    private javax.swing.JTextField jTextField1_nome;
    private javax.swing.JTextField jTextField3_email;
    // End of variables declaration//GEN-END:variables
}
