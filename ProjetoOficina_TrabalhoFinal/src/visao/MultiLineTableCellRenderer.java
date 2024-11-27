package visao;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.TableCellRenderer;

public class MultiLineTableCellRenderer extends JTextArea implements TableCellRenderer {

    public MultiLineTableCellRenderer() {
       setLineWrap(true);
       setWrapStyleWord(true);
    }

    @Override
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
       javax.swing.JTextArea tArea = new javax.swing.JTextArea(){
                public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
                    if ("lineWrap".equals(propertyName) || "wrapStyleWord".equals(propertyName)){
                        super.firePropertyChange(propertyName, oldValue, newValue);
                    }
                }
                
            };
            
            //tArea.setFont(new java.awt.Font("Tahoma", 0, 11));
            tArea.setFont(table.getFont());
            tArea.setLineWrap(true);
            tArea.setWrapStyleWord(true);
            String txt = (value != null) ? value.toString() : "";
            tArea.append(txt);
            return tArea;
            
        }
}