/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package visao;

import java.util.Calendar;
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
      jYearChooser1_ano.setYear(2024);
    }
    
    private void mostrarAcessorioNaGrid(){
        try {
            List<Acessorio> listaDeAcessorio = AcessorioBD.listar();
            DefaultTableModel model = (DefaultTableModel) jTableServicos.getModel();
            model.setNumRows(0);

            if (listaDeAcessorio.isEmpty()) {
                throw new Exception("Lista de Acessórios BD Vazia");
            }

            for (Acessorio objAcessorio : listaDeAcessorio) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(objAcessorio.getAno());
                int ano = calendar.get(Calendar.YEAR);

                String[] saida = new String[3];
                saida[0] = String.valueOf(objAcessorio.getIdAcessorio());
                saida[1] = String.valueOf(ano); // Ano correto
                saida[2] = objAcessorio.getDescricao();

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
        jButtonIncluir = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableServicos = new javax.swing.JTable();
        jTextField1_ID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton_buscar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField1_descricao = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jYearChooser1_ano = new com.toedter.calendar.JYearChooser();

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
        jTableServicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableServicosMouseClicked(evt);
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
                        .addComponent(jButton_buscar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jYearChooser1_ano, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField1_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonIncluir)
                            .addComponent(jButtonAlterar)
                            .addComponent(jButton_buscar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jYearChooser1_ano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirActionPerformed
        try {
            // Obtém o ano do JYearChooser
            int ano = jYearChooser1_ano.getYear();
            // **Validação do Ano do Acessório**
            int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
            if (ano < 1800 || ano > anoAtual + 1) {
                JOptionPane.showMessageDialog(rootPane, "Ano do Acessório inválido! Deve ser entre 1800 e no máximo " + (anoAtual + 1) + ".");
                return;
            }

            // Inicializa a data como 1º de janeiro do ano selecionado
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, ano);
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Date data = calendar.getTime();

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
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao incluir: " + erro.getMessage());
        }
    }//GEN-LAST:event_jButtonIncluirActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        try {
            // Obtém o ano do JYearChooser
            int ano = jYearChooser1_ano.getYear();

            // Inicializa a data como 1º de janeiro do ano selecionado
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, ano);
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Date data = calendar.getTime();

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
            Acessorio objAcessorio = new Acessorio(Integer.parseInt(jTextField1_ID.getText()));
            objAcessorio = (AcessorioBD.consultar(objAcessorio));
      
            jTextField1_ID.setText(objAcessorio.getIdAcessorio()+"");
            jTextField1_descricao.setText(objAcessorio.getDescricao());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(objAcessorio.getAno());
            int ano = calendar.get(Calendar.YEAR);
            jYearChooser1_ano.setYear(ano);
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
        
        jTextField1_ID.setText((String) model.getValueAt(selectedRowIndex, 0));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt((String) model.getValueAt(selectedRowIndex, 1)));
        jYearChooser1_ano.setYear(calendar.get(Calendar.YEAR));
        
        jTextField1_descricao.setText((String) model.getValueAt(selectedRowIndex, 2));
    }//GEN-LAST:event_jTableServicosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonIncluir;
    private javax.swing.JButton jButton_buscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableServicos;
    private javax.swing.JTextField jTextField1_ID;
    private javax.swing.JTextField jTextField1_descricao;
    private com.toedter.calendar.JYearChooser jYearChooser1_ano;
    // End of variables declaration//GEN-END:variables
}
