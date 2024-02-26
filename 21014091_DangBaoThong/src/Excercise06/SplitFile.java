/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Excercise06;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author PC BAO THONG
 */
public class SplitFile extends javax.swing.JFrame {

    /**
     * Creates new form SplitFile
     */
    public SplitFile() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtInput = new javax.swing.JTextField();
        txtOutput = new javax.swing.JTextField();
        txtNumber = new javax.swing.JTextField();
        btnInput = new javax.swing.JButton();
        btnOutput = new javax.swing.JButton();
        btnSplit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        progressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Split File", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 20, 0, 20, 0, 20, 0};
        jPanel1Layout.rowHeights = new int[] {0, 20, 0, 20, 0};
        jPanel1.setLayout(jPanel1Layout);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Input File");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Output Folder");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Number of file to split into");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel3, gridBagConstraints);

        txtInput.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtInput.setPreferredSize(new java.awt.Dimension(350, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel1.add(txtInput, gridBagConstraints);

        txtOutput.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtOutput.setPreferredSize(new java.awt.Dimension(350, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel1.add(txtOutput, gridBagConstraints);

        txtNumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNumber.setPreferredSize(new java.awt.Dimension(350, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        jPanel1.add(txtNumber, gridBagConstraints);

        btnInput.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnInput.setText("...");
        btnInput.setPreferredSize(new java.awt.Dimension(80, 35));
        btnInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInputActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel1.add(btnInput, gridBagConstraints);

        btnOutput.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnOutput.setText("...");
        btnOutput.setPreferredSize(new java.awt.Dimension(80, 35));
        btnOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOutputActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        jPanel1.add(btnOutput, gridBagConstraints);

        btnSplit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSplit.setText("Split");
        btnSplit.setPreferredSize(new java.awt.Dimension(80, 35));
        btnSplit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSplitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        jPanel1.add(btnSplit, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.BorderLayout());

        progressBar.setPreferredSize(new java.awt.Dimension(350, 20));
        progressBar.setStringPainted(true);
        progressBar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                progressBarPropertyChange(evt);
            }
        });
        jPanel2.add(progressBar, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    class SplitWithThread extends SwingWorker<Long, Integer> {

        private final File inputFile;
        private final File outputFolder;
        private final int numberSplitFile;

        public SplitWithThread(File inputFile, File outputFolder, int numberSplitFile) {
            super();
            this.inputFile = inputFile;
            this.outputFolder = outputFolder;
            this.numberSplitFile = numberSplitFile;
        }

        @Override
        protected Long doInBackground() throws Exception {
            if (!outputFolder.exists()) {
                outputFolder.mkdirs();
            }
            long totalByte = inputFile.length();
            long splitByte = totalByte / numberSplitFile;

            try (FileInputStream in = new FileInputStream(inputFile); BufferedInputStream bin = new BufferedInputStream(in);) {

                for (int i = 0; i < numberSplitFile; i++) {
                    long start = i * splitByte;
                    long end;
                    if (i == numberSplitFile - 1) {
                        end = totalByte;
                    } else {
                        end = (i + 1) * splitByte;
                    }
                    byte[] buffer = new byte[(int) (end - start)];
                    int length = bin.read(buffer);
                    
                    String source = inputFile.getPath();
                    String filename = source.substring(source.lastIndexOf("\\") + 1);
                    String extension = filename.substring(filename.lastIndexOf(".") + 1);

                    String name = filename.substring(0, filename.lastIndexOf("."));
                    String splitFileName = String.format("%s/%s%d.%s", outputFolder, name, i + 1, extension);

                    try (FileOutputStream out = new FileOutputStream(splitFileName); BufferedOutputStream bout = new BufferedOutputStream(out);) {
                        bout.write(buffer, 0, length);
                    }
                    int progress = (int) (i * 100 / numberSplitFile);
                    progressBar.setValue(progress);
                    publish(progress);
                }
            }
            return null;
        }

        @Override
        protected void process(List<Integer> chunks) {
            System.out.println("Processing..." + chunks.get(chunks.size() - 1));
        }

        @Override
        protected void done() {
            try {
                JOptionPane.showMessageDialog(null, "Split completed!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    private void btnInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInputActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose input file");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            txtInput.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_btnInputActionPerformed

    private void btnOutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOutputActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose output folder");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            txtOutput.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_btnOutputActionPerformed

    private void btnSplitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSplitActionPerformed
        // TODO add your handling code here:
        File inputFile = new File(txtInput.getText());
        File outPutFolder = new File(txtOutput.getText());
        int numberOfFile = Integer.parseInt(txtNumber.getText());
        SplitWithThread task = new SplitWithThread(inputFile, outPutFolder, numberOfFile);
        task.execute();

    }//GEN-LAST:event_btnSplitActionPerformed

    private void progressBarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_progressBarPropertyChange
        // TODO add your handling code here:
        if ("progress".equals(evt.getPropertyName())) {
            progressBar.setValue((Integer) evt.getNewValue());
        }
    }//GEN-LAST:event_progressBarPropertyChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SplitFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SplitFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SplitFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SplitFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SplitFile().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInput;
    private javax.swing.JButton btnOutput;
    private javax.swing.JButton btnSplit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JTextField txtInput;
    private javax.swing.JTextField txtNumber;
    private javax.swing.JTextField txtOutput;
    // End of variables declaration//GEN-END:variables
}
