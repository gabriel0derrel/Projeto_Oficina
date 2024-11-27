/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package visao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.*;
import persistencia.AcessorioDAO;

/**
 *
 * @author lusra
 */
public class TelaAcessorio extends javax.swing.JInternalFrame {
    private ICrud<Acessorio> AcessorioBD = null;
    
    public TelaAcessorio() {
        initComponents();
        try {
          AcessorioBD = new AcessorioDAO();
          mostrarAcessorioNaGrid();
        } catch (Exception erro) {
          JOptionPane.showMessageDialog(null, "Construtor Tela: "+erro.getMessage());
        }
    }
    private void limparTela(){
      jTextField1_ID.setText("");
      jTextField1_descricao.setText("");
      jFormattedTextField1.setValue(null);
    }
    
    private void mostrarAcessorioNaGrid(){
    try {
      List<Acessorio> listaDeAcessorio = new ArrayList<>();
      listaDeAcessorio = null;
      listaDeAcessorio = AcessorioBD.listar();
      DefaultTableModel model =  (DefaultTableModel) jTableServicos.getModel();
      model.setNumRows(0); 
      if(listaDeAcessorio.isEmpty()) 
        throw new Exception("Lista de Acessorio BD Vazia");
      for(int j = 0; j<3;j++){
          jTableServicos.getColumnModel().getColumn(j);
           }
      for(int pos = 0; pos < listaDeAcessorio.size(); pos++){
        Acessorio objAcessorio = listaDeAcessorio.get(pos);
        String[] saida = new String[3];
          saida[0] = objAcessorio.getIdAcessorio()+ "";
          saida[1] = objAcessorio.getAno()+ "";
          saida[2] = objAcessorio.getDescricao()+ "";
        model.addRow(saida);
      }  
    } catch (Exception erro) {
        JOptionPane.showMessageDialog(rootPane, erro.getMessage());
      }    
    }
    
    private void mostrarAcessorioNaGridBusca(Acessorio objAcessorio){
      try {

        DefaultTableModel model =  (DefaultTableModel) jTableServicos.getModel();
        model.setNumRows(0); 
        if(objAcessorio == null) 
          throw new Exception("Lista de Busca BD Vazia");
        for(int j = 0; j<3;j++){
            jTableServicos.getColumnModel().getColumn(j);
             }
          String[] saida = new String[3];
            saida[0] = objAcessorio.getIdAcessorio()+ "";
            saida[1] = objAcessorio.getAno()+ "";
            saida[2] = objAcessorio.getDescricao()+ "";
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableServicos = new javax.swing.JTable();
        jTextField1_ID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton_buscar = new javax.swing.JButton();
        jButtonListar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField1_descricao = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 3, 36)); // NOI18N
        jLabel1.setText("CADASTRO DE ACESSÓRIO");

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

        jTableServicos.setFont(new java.awt.Font("Helvetica Neue", 3, 12)); // NOI18N
        jTableServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "ANO", "DESCRICAO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableServicos);
        if (jTableServicos.getColumnModel().getColumnCount() > 0) {
            jTableServicos.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableServicos.getColumnModel().getColumn(1).setMinWidth(90);
            jTableServicos.getColumnModel().getColumn(1).setMaxWidth(90);
        }

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
        jLabel6.setText("Descrição");

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

        jLabel9.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel9.setText("ANO");

        try {
            jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(341, 341, 341)
                .addComponent(jLabel1)
                .addGap(0, 277, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonIncluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_buscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonListar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField1_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonIncluir)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButton_buscar)
                    .addComponent(jButtonListar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirActionPerformed
        try {
            String dataTexto = jFormattedTextField1.getText().trim();

            // Verifica se o campo de data está vazio
            if (dataTexto.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "O campo de data não pode estar vazio.");
                return; // Sai do método para evitar execução adicional
            }

            // Define o formato esperado (dia/mês/ano)
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            formato.setLenient(false); // Garante validação estrita da data

            // Converte o texto para um objeto Date
            Date data = formato.parse(dataTexto);

            // Verifica se a descrição está vazia
            String descricao = jTextField1_descricao.getText().trim();
            if (descricao.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "O campo de descrição não pode estar vazio.");
                return;
            }

            // Cria o objeto Acessorio
            Acessorio objAcessorio = new Acessorio(0, data, descricao.toUpperCase());

            // Insere no banco de dados
            AcessorioBD.incluir(objAcessorio);

            // Atualiza a interface
            limparTela();
            mostrarAcessorioNaGrid();
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(rootPane, "Data inválida! Use o formato dd/MM/yyyy.");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao incluir: " + erro.getMessage());
        }
    }//GEN-LAST:event_jButtonIncluirActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        try {
            String dataTexto = jFormattedTextField1.getText().trim();

            // Verifica se o campo de data está vazio
            if (dataTexto.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "O campo de data não pode estar vazio.");
                return; // Sai do método para evitar execução adicional
            }

            // Define o formato esperado (dia/mês/ano)
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            formato.setLenient(false); // Garante validação estrita da data

            // Converte o texto para um objeto Date
            Date data = formato.parse(dataTexto);

            // Verifica se a descrição está vazia
            String descricao = jTextField1_descricao.getText().trim();
            if (descricao.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "O campo de descrição não pode estar vazio.");
                return;
            }

            Acessorio objAcessorio = new Acessorio(Integer.parseInt(jTextField1_ID.getText()), data, jTextField1_descricao.getText().toUpperCase());

            AcessorioBD.alterar(objAcessorio);
            limparTela();
            mostrarAcessorioNaGrid();
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(rootPane, "Data inválida! Use o formato dd/MM/yyyy.");
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
            Acessorio buscarAcessorio = new Acessorio(Integer.parseInt(jTextField1_ID.getText()));
            mostrarAcessorioNaGridBusca(AcessorioBD.consultar(buscarAcessorio));
            limparTela();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Buscar Visao: "+erro.getMessage());
        }
    }//GEN-LAST:event_jButton_buscarActionPerformed

    private void jButtonListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListarActionPerformed
        // TODO add your handling code here:
        mostrarAcessorioNaGrid();
    }//GEN-LAST:event_jButtonListarActionPerformed

    private void jTextField1_descricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1_descricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1_descricaoActionPerformed

    private void jTextField1_descricaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1_descricaoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1_descricaoKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonIncluir;
    private javax.swing.JButton jButtonListar;
    private javax.swing.JButton jButton_buscar;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableServicos;
    private javax.swing.JTextField jTextField1_ID;
    private javax.swing.JTextField jTextField1_descricao;
    // End of variables declaration//GEN-END:variables
}
