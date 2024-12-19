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
import modelos.ICrud;
import modelos.ItensPeca;
import modelos.OrdemDeServico;
import modelos.Pecas;
import persistencia.ItensPecaDAO;
import persistencia.PecaDAO;

/**
 *
 * @author Cliente
 */
public class TelaItensPecas extends javax.swing.JFrame {
    private ICrud<Pecas> pecaDB = null;
    private ICrud<ItensPeca> itensDB = null;
    private OrdemDeServico ordem = null;
    /**
     * Creates new form TelaPecas
     * @param ordem
     */
    public TelaItensPecas(OrdemDeServico ordem) {
        initComponents();
        try {
            this.ordem = ordem;
            pecaDB = new PecaDAO();
            itensDB = new ItensPecaDAO();
          
            List<Pecas> listaDePeca = pecaDB.listar();
            jComboBox_Peca.removeAllItems();
            for(int pos = 0; pos < listaDePeca.size(); pos++){
                jComboBox_Peca.addItem(listaDePeca.get(pos).toString());
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

    
    private void limparTela(){
        jTextField_ID.setText("");
        jComboBox_Peca.setSelectedIndex(0);
        jTextField_Quantidade.setText("");
        jTextField_PrecoUnitario.setText("");
        jTextField_PrecoTotal.setText("");
    }
    
    private void mostrarNaGrid(){
        try {
            List<ItensPeca> listaDeItens = null;
            listaDeItens = itensDB.listar();
            DefaultTableModel model =  (DefaultTableModel) jTable_Saida.getModel();
            model.setNumRows(0); 
            if(listaDeItens.isEmpty()) throw new Exception("Lista de Itens Peça BD Vazia");

            jTable_Saida.setRowHeight(75);
            for(int j = 0; j<6;j++){
                jTable_Saida.getColumnModel().getColumn(j).setCellRenderer(new MultiLineTableCellRenderer());
            }
            for(int pos = 0; pos < listaDeItens.size(); pos++){
                ItensPeca objOrdem = listaDeItens.get(pos);
                if(objOrdem.getOrdem().getIdOrdem() == ordem.getIdOrdem()){
                    String[] saida = new String[6];
                    saida[0] = objOrdem.getIdItensPeca()+"";
                    saida[1] = objOrdem.getPeca().toString();
                    saida[2] = objOrdem.getOrdem().toString();
                    saida[3] = objOrdem.getQuantidade()+"";
                    saida[4] = objOrdem.getValorUnitario();
                    saida[5] = objOrdem.getValorTotal();
                    model.addRow(saida);
                }
            }  
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
        
    }
    
    private void atualizarPrecos(){
        try{
            ItensPeca objeto = new ItensPeca();
            objeto.setPeca(new Pecas(Integer.parseInt(jComboBox_Peca.getSelectedItem().toString().split("-")[0])));
            int quantidade = 0;
            if(!jTextField_Quantidade.getText().isEmpty()){
                quantidade = Integer.parseInt(jTextField_Quantidade.getText());
            }
            jTextField_PrecoUnitario.setText(pecaDB.consultar(objeto.getPeca()).getValorUnitarioPeca().replace("R$", "").trim());
            float precoUnitario = Float.parseFloat(jTextField_PrecoUnitario.getText().replace(",", "."));
            float precoFinal = precoUnitario*quantidade;
            jTextField_PrecoTotal.setText(precoFinal+"");
        } catch(Exception erro){
            JOptionPane.showMessageDialog(rootPane, "Erro: " + erro.getMessage());
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

        jTextField_PrecoTotal = new javax.swing.JTextField();
        jTextField_ID = new javax.swing.JTextField();
        jButton_Incluir = new javax.swing.JButton();
        jButton_Alterar = new javax.swing.JButton();
        jComboBox_Peca = new javax.swing.JComboBox<>();
        jButton_Buscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Saida = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jTextField_Quantidade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField_PrecoUnitario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField_PrecoTotal.setBackground(new java.awt.Color(204, 255, 204));

        jTextField_ID.setBackground(new java.awt.Color(204, 255, 204));

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

        jComboBox_Peca.setBackground(new java.awt.Color(204, 255, 204));
        jComboBox_Peca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_PecaActionPerformed(evt);
            }
        });

        jButton_Buscar.setBackground(new java.awt.Color(0, 153, 0));
        jButton_Buscar.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jButton_Buscar.setForeground(new java.awt.Color(0, 255, 204));
        jButton_Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figuras/procurar.png"))); // NOI18N
        jButton_Buscar.setText("ALTERAR");
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
                "ID", "Peça", "Ordem de Serviço", "Quantidade", "Preço Unitário", "Preço Total"
            }
        ));
        jTable_Saida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_SaidaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Saida);

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel6.setText("Quantidade");

        jTextField_Quantidade.setBackground(new java.awt.Color(204, 255, 204));

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel5.setText("Preço Unitário");

        jTextField_PrecoUnitario.setBackground(new java.awt.Color(204, 255, 204));

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel7.setText("Preço Total");

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setText("Peça");

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setText("ID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox_Peca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField_ID))
                        .addGap(86, 86, 86)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_PrecoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField_PrecoUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField_Quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
            .addGroup(layout.createSequentialGroup()
                .addGap(481, 481, 481)
                .addComponent(jButton_Incluir)
                .addGap(18, 18, 18)
                .addComponent(jButton_Alterar)
                .addGap(18, 18, 18)
                .addComponent(jButton_Buscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addComponent(jComboBox_Peca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField_PrecoUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField_PrecoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Incluir)
                    .addComponent(jButton_Alterar)
                    .addComponent(jButton_Buscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_IncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_IncluirActionPerformed
        // TODO add your handling code here:
        try {
            atualizarPrecos();
            ItensPeca objeto = new ItensPeca();

            objeto.setIdItensPeca(0);
            objeto.setPeca(new Pecas(Integer.parseInt(jComboBox_Peca.getSelectedItem().toString().split("-")[0])));
            objeto.setOrdem(ordem);

            if(jTextField_Quantidade.getText().isEmpty()) throw new Exception("Quantidade Vazia");
            if(!jTextField_Quantidade.getText().matches("\\d+")) throw new Exception("Quantidade precisa ser um inteiro positivo");
            objeto.setQuantidade(Integer.parseInt(jTextField_Quantidade.getText()));
            objeto.setValorUnitario(jTextField_PrecoUnitario.getText().replace(".", "").replace(",", "."));
            objeto.setValorTotal(jTextField_PrecoTotal.getText());

            itensDB.incluir(objeto);
            limparTela();
            mostrarNaGrid();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao incluir Itens de Peça: " + erro.getMessage());
        }
    }//GEN-LAST:event_jButton_IncluirActionPerformed

    private void jButton_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AlterarActionPerformed
        // TODO add your handling code here:
        try {
            atualizarPrecos();
            ItensPeca objeto = new ItensPeca();

            if(jTextField_ID.getText().isEmpty()) throw new Exception("ID vazio");
            objeto.setIdItensPeca(Integer.parseInt(jTextField_ID.getText()));
            objeto.setPeca(new Pecas(Integer.parseInt(jComboBox_Peca.getSelectedItem().toString().split("-")[0])));
            objeto.setOrdem(ordem);

            if(jTextField_Quantidade.getText().isEmpty()) throw new Exception("Quantidade Vazia");
            if(!jTextField_Quantidade.getText().matches("\\d+")) throw new Exception("Quantidade precisa ser um inteiro positivo");
            objeto.setQuantidade(Integer.parseInt(jTextField_Quantidade.getText()));
            objeto.setValorUnitario(jTextField_PrecoUnitario.getText().replace(".", "").replace(",", "."));
            objeto.setValorTotal(jTextField_PrecoTotal.getText());

            itensDB.alterar(objeto);
            limparTela();
            mostrarNaGrid();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao alterar Itens de Peça: " + erro.getMessage());
        }
    }//GEN-LAST:event_jButton_AlterarActionPerformed

    private void jComboBox_PecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_PecaActionPerformed
        // TODO add your handling code here:
        atualizarPrecos();
    }//GEN-LAST:event_jComboBox_PecaActionPerformed

    private void jButton_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BuscarActionPerformed
        // TODO add your handling code here:
        try {
            ItensPeca objeto = itensDB.consultar(new ItensPeca(Integer.parseInt(jTextField_ID.getText())));

            jTextField_ID.setText(objeto.getIdItensPeca()+"");
            jComboBox_Peca.setSelectedItem(objeto.getPeca().toString());
            jTextField_Quantidade.setText(objeto.getQuantidade()+"");
            jTextField_PrecoUnitario.setText(objeto.getValorUnitario().replace("R$", "").trim());
            jTextField_PrecoTotal.setText(objeto.getValorTotal().replace("R$", "").trim());

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao buscar Itens de Peça: " + erro.getMessage());
        }
    }//GEN-LAST:event_jButton_BuscarActionPerformed

    private void jTable_SaidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_SaidaMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)jTable_Saida.getModel();
        int selectedRowIndex = jTable_Saida.getSelectedRow();

        jTextField_ID.setText(model.getValueAt(selectedRowIndex, 0).toString());
        jComboBox_Peca.setSelectedItem(model.getValueAt(selectedRowIndex, 1).toString());
        jTextField_Quantidade.setText(model.getValueAt(selectedRowIndex, 3).toString());
        jTextField_PrecoUnitario.setText(model.getValueAt(selectedRowIndex, 4).toString().replace("R$", "").trim());
        jTextField_PrecoTotal.setText(model.getValueAt(selectedRowIndex, 5).toString().replace("R$", "").trim());
    }//GEN-LAST:event_jTable_SaidaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Alterar;
    private javax.swing.JButton jButton_Buscar;
    private javax.swing.JButton jButton_Incluir;
    private javax.swing.JComboBox<String> jComboBox_Peca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
