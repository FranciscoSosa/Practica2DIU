import java.awt.Color;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class Converter extends javax.swing.JFrame {

    /**
     * Creates new form Convert
     */
    public Converter(double EURUSD) {
        this.EURUSD = EURUSD;
        initComponents();
        inputField.getDocument().addDocumentListener(new DocumentListener(){
        
            @Override
            public void insertUpdate(DocumentEvent e) {
                convert();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                convert();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                convert();
            }
          
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inputField = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        outputLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Conversor euro-dólar ");
        setResizable(false);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "euro", "dólar" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "euro", "dólar" }));
        jComboBox2.setSelectedIndex(1);
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(outputLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputField)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addComponent(outputLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        jComboBox2.setSelectedIndex(jComboBox1.getSelectedIndex() == 0 ? 1: 0);
        convert();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        jComboBox1.setSelectedIndex(jComboBox2.getSelectedIndex() == 0 ? 1: 0);
        convert();
    }//GEN-LAST:event_jComboBox2ActionPerformed
    
    private void convert(){
        outputLabel.setForeground(Color.black);
        String item = (String)jComboBox1.getSelectedItem();
        double rate = item.equals("euro") ? EURUSD : 1 / EURUSD;
        String format = item.equals("euro") ? " EUR = %.2f USD" : " USD = %.2f EUR";
        String text = inputField.getText();
        if(text.length() > 0){
            //"\\d+[.]?\\d*" expresión regular para comprobar que text sea un número con o sin decimales.
            if(text.matches("\\d+[.]?\\d*")){
                double data = Double.parseDouble(text) * rate;
                outputLabel.setText(String.format(text + format, data));
            }else{
                //Es necesario java.awt.EventQueue.invokeLater , para poder modificar inputField en el DocumentListener.
                java.awt.EventQueue.invokeLater(new Runnable(){
                    @Override
                    public void run(){
                        inputField.setText("");
                        outputLabel.setForeground(Color.red);
                        outputLabel.setText("Debe introducir un número con o sin decimales");
                    }
                });
            }
        }else{
            outputLabel.setText("");
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField inputField;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel outputLabel;
    // End of variables declaration//GEN-END:variables
    private final double EURUSD;
}
