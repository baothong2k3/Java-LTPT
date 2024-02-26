/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Excercise04;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author PC BAO THONG
 */
public class CopyFile extends javax.swing.JFrame {

    /**
     * Creates new form CopyFile
     */
    public CopyFile() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    class CopyFileWithThread extends SwingWorker<Long, Integer> {

        private final String source;
        private final String destination;

        public CopyFileWithThread(String source, String destination) {
            super();
            this.source = source;
            this.destination = destination;
        }

        @Override
        protected Long doInBackground() throws Exception {
            File file = new File(source);
            long byteTotal = file.length();
            long byteRead = 0;
            try (
                    FileInputStream in = new FileInputStream(source);
                    FileOutputStream out = new FileOutputStream(destination, true);

                    BufferedInputStream bin = new BufferedInputStream(in);
                    BufferedOutputStream bout = new BufferedOutputStream(out);)
            {
                byte[] buffer = new byte[1024];
                int length = 0;
                while (bin.available() > 0) {

                    length = bin.read(buffer);
                    bout.write(buffer, 0, length);
                    byteRead += length;
                    int progress = (int) (byteRead * 100 / byteTotal);
                    int t = (int) (byteRead * 100);
                    progressBar.setValue(progress);
                    publish((int)byteRead);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return (long) byteRead;
        }

        @Override
        protected void process(java.util.List<Integer> chunks) {
            System.out.println("Processing..." + chunks.get(chunks.size()-1));
        }

        

        @Override
        protected void done() {
            try {
                long byteTotal = get();
                JOptionPane.showMessageDialog(null, "File copy completed! Total bytes read: " + byteTotal);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        txtForm = new javax.swing.JTextField();
        txtTo = new javax.swing.JTextField();
        btnCopy = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CopyFile");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(560, 350));
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0};
        jPanel1Layout.rowHeights = new int[] {0, 20, 0, 20, 0, 20, 0};
        jPanel1.setLayout(jPanel1Layout);

        txtForm.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtForm.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Form", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        txtForm.setPreferredSize(new java.awt.Dimension(450, 60));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(txtForm, gridBagConstraints);

        txtTo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "To", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        txtTo.setPreferredSize(new java.awt.Dimension(450, 60));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel1.add(txtTo, gridBagConstraints);

        btnCopy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCopy.setText("Copy");
        btnCopy.setPreferredSize(new java.awt.Dimension(100, 40));
        btnCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jPanel1.add(btnCopy, gridBagConstraints);

        progressBar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        progressBar.setPreferredSize(new java.awt.Dimension(550, 40));
        progressBar.setRequestFocusEnabled(false);
        progressBar.setStringPainted(true);
        progressBar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                progressBarPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        jPanel1.add(progressBar, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopyActionPerformed
        // TODO add your handling code here:
        String source = txtForm.getText();
        String destination = txtTo.getText();
        CopyFileWithThread copyFile = new CopyFileWithThread(source, destination);
        copyFile.execute();
    }//GEN-LAST:event_btnCopyActionPerformed

    private void progressBarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_progressBarPropertyChange
        // TODO add your handling code here:
        if ("progress".equals(evt.getPropertyName())) {
            progressBar.setValue((int) evt.getNewValue());
        }
    }//GEN-LAST:event_progressBarPropertyChange

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(CopyFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CopyFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CopyFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CopyFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CopyFile().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCopy;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JTextField txtForm;
    private javax.swing.JTextField txtTo;
    // End of variables declaration//GEN-END:variables
}
