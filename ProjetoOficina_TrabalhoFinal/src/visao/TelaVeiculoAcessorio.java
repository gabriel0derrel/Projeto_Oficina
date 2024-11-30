/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package visao;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Acessorio;
import modelos.ICrud;
import modelos.Veiculo;
import modelos.VeiculoAcessorio;
import persistencia.AcessorioDAO;
import persistencia.VeiculoAcessorioDAO;
import persistencia.VeiculoDAO;

/**
 *
 * @author Cliente
 */
public class TelaVeiculoAcessorio extends javax.swing.JInternalFrame {
    private ICrud<Acessorio> AcessorioBD = null;
    private ICrud<Veiculo> VeiculoBD = null;
    private ICrud<VeiculoAcessorio> VeiculoAcessorioBD = null;
    /**
     * Creates new form TelaVeiculo
     */
    public TelaVeiculoAcessorio() {
        initComponents();
        try {
            AcessorioBD = new AcessorioDAO();
            VeiculoBD = new VeiculoDAO();
            VeiculoAcessorioBD = new VeiculoAcessorioDAO();
            List<Acessorio> listaDeAcessorios = new ArrayList<>();
            listaDeAcessorios = null;
            listaDeAcessorios = AcessorioBD.listar();
            jComboBox_acessorio.removeAllItems();
            for(int pos = 0; pos < listaDeAcessorios.size(); pos++){
              jComboBox_acessorio.addItem(listaDeAcessorios.get(pos).toString());
            }
            List<Veiculo> listaDeVeiculos = new ArrayList<>();
            listaDeVeiculos = null;
            listaDeVeiculos = VeiculoBD.listar();
            jComboBox_placa.removeAllItems();
            for(int pos = 0; pos < listaDeVeiculos.size(); pos++){
              jComboBox_placa.addItem(listaDeVeiculos.get(pos).toString());
            }
          mostrarVeiculoAcessorioNaGrid();
        } catch (Exception erro) {
          JOptionPane.showMessageDialog(null, "Construtor Tela: "+erro.getMessage());
        }
    }
    
    private void mostrarVeiculoAcessorioNaGrid(){
    try {
      List<VeiculoAcessorio> listaDeVeiculoAcessorio = new ArrayList<>();
      listaDeVeiculoAcessorio = null;
      listaDeVeiculoAcessorio = VeiculoAcessorioBD.listar();
      DefaultTableModel model =  (DefaultTableModel) jTable_Saida.getModel();
      model.setNumRows(0); 
      if(listaDeVeiculoAcessorio.isEmpty()) 
        throw new Exception("Lista de Acessorio BD Vazia");
      for(int j = 0; j<2;j++){
          jTable_Saida.getColumnModel().getColumn(j);
           }
      for(int pos = 0; pos < listaDeVeiculoAcessorio.size(); pos++){
        VeiculoAcessorio objVeiculoAcessorio = listaDeVeiculoAcessorio.get(pos);
        String[] saida = new String[2];
          saida[0] = objVeiculoAcessorio.getVeiculo().getPlaca()+ "";
          saida[1] = objVeiculoAcessorio.getAcessorio().getIdAcessorio()+ "";
        model.addRow(saida);
      }  
    } catch (Exception erro) {
        JOptionPane.showMessageDialog(rootPane, erro.getMessage());
      }    
    }
    
    private void mostrarVeiculoAcessorioNaGridBusca(VeiculoAcessorio objVeiculoAcessorio){
      try {

        DefaultTableModel model =  (DefaultTableModel) jTable_Saida.getModel();
        model.setNumRows(0); 
        if(objVeiculoAcessorio == null) 
          throw new Exception("Lista de Busca BD Vazia");
        for(int j = 0; j<2;j++){
            jTable_Saida.getColumnModel().getColumn(j);
             }
            String[] saida = new String[2];
          saida[0] = objVeiculoAcessorio.getVeiculo().getPlaca()+ "";
          saida[1] = objVeiculoAcessorio.getAcessorio().getIdAcessorio()+ "";
          model.addRow(saida);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Saida = new javax.swing.JTable();
        jButtonincluir = new javax.swing.JButton();
        jButton_alterar = new javax.swing.JButton();
        jButton_buscar = new javax.swing.JButton();
        jButton_listar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jComboBox_acessorio = new javax.swing.JComboBox<>();
        jComboBox_placa = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1119, 466));

        jTable_Saida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "placa", "idAcessorio"
            }
        ));
        jScrollPane1.setViewportView(jTable_Saida);

        jButtonincluir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonincluir.setText("INCLUIR");
        jButtonincluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonincluirActionPerformed(evt);
            }
        });

        jButton_alterar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_alterar.setText("ALTERAR");
        jButton_alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_alterarActionPerformed(evt);
            }
        });

        jButton_buscar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_buscar.setText("BUSCAR");
        jButton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_buscarActionPerformed(evt);
            }
        });

        jButton_listar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_listar.setText("LISTAR");
        jButton_listar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_listarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel7.setText("Acesssorio");

        jComboBox_acessorio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jComboBox_placa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel8.setText("Placa");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonincluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_alterar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_buscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_listar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_acessorio, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_placa, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 463, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox_placa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox_acessorio, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonincluir)
                    .addComponent(jButton_alterar)
                    .addComponent(jButton_buscar)
                    .addComponent(jButton_listar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonincluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonincluirActionPerformed
        // TODO add your handling code here:
        try {
            String aux = (String)jComboBox_placa.getSelectedItem();
            //System.out.println(aux);
            String[] vetVeiculo = aux.split("-");
            //System.out.println(vetAcessorio[0]);
            String placa = vetVeiculo[0];
            Veiculo veiculo = new Veiculo(placa);
            
            aux = (String)jComboBox_acessorio.getSelectedItem();
            String[] vetAcessorio = aux.split("-");
            //System.out.println(vetAcessorio[0]);
            int idAcessorio = Integer.parseInt(vetAcessorio[0]);
            Acessorio acessorio = new Acessorio(idAcessorio);
            
            VeiculoAcessorio objVeiculoAcesssorio = new VeiculoAcessorio(veiculo,acessorio);
            VeiculoAcessorioBD.incluir(objVeiculoAcesssorio);
            mostrarVeiculoAcessorioNaGrid();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Incluir Visao: "+erro.getMessage());
        }
    }//GEN-LAST:event_jButtonincluirActionPerformed

    private void jButton_alterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_alterarActionPerformed
        // TODO add your handling code here:
        try {
            String aux = (String)jComboBox_placa.getSelectedItem();
            //System.out.println(aux);
            String[] vetVeiculo = aux.split("-");
            //System.out.println(vetAcessorio[0]);
            String placa = vetVeiculo[0];
            Veiculo veiculo = new Veiculo(placa);
            
            aux = (String)jComboBox_acessorio.getSelectedItem();
            String[] vetAcessorio = aux.split("-");
            //System.out.println(vetAcessorio[0]);
            int idAcessorio = Integer.parseInt(vetAcessorio[0]);
            Acessorio acessorio = new Acessorio(idAcessorio);
            
            VeiculoAcessorio objVeiculoAcesssorio = new VeiculoAcessorio(veiculo,acessorio);
            VeiculoAcessorioBD.alterar(objVeiculoAcesssorio);
            mostrarVeiculoAcessorioNaGrid();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Incluir Visao: "+erro.getMessage());
        }
    }//GEN-LAST:event_jButton_alterarActionPerformed

    private void jButton_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_buscarActionPerformed
        // TODO add your handling code here:
        try {
            String aux = (String)jComboBox_placa.getSelectedItem();
            //System.out.println(aux);
            String[] vetVeiculo = aux.split("-");
            //System.out.println(vetAcessorio[0]);
            String placa = vetVeiculo[0];
            Veiculo veiculo = new Veiculo(placa);
            
            aux = (String)jComboBox_acessorio.getSelectedItem();
            String[] vetAcessorio = aux.split("-");
            //System.out.println(vetAcessorio[0]);
            int idAcessorio = Integer.parseInt(vetAcessorio[0]);
            Acessorio acessorio = new Acessorio(idAcessorio);
            
            VeiculoAcessorio objVeiculoAcesssorioBusca = new VeiculoAcessorio(veiculo,acessorio);
            mostrarVeiculoAcessorioNaGridBusca(VeiculoAcessorioBD.consultar(objVeiculoAcesssorioBusca));
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Incluir Visao: "+erro.getMessage());
        }
    }//GEN-LAST:event_jButton_buscarActionPerformed

    private void jButton_listarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_listarActionPerformed
        // TODO add your handling code here:
        mostrarVeiculoAcessorioNaGrid();
    }//GEN-LAST:event_jButton_listarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_alterar;
    private javax.swing.JButton jButton_buscar;
    private javax.swing.JButton jButton_listar;
    private javax.swing.JButton jButtonincluir;
    private javax.swing.JComboBox<String> jComboBox_acessorio;
    private javax.swing.JComboBox<String> jComboBox_placa;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Saida;
    // End of variables declaration//GEN-END:variables
}
