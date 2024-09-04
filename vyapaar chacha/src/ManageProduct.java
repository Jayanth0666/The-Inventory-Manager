/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JTable;
import java.sql.*;
import dao.ConnectionProvider;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;






/**
 *
 * @author jayanth
 */
public class ManageProduct extends javax.swing.JFrame {

    private int productPk = 0;
    private int totalQuantity = 0;



    /**
     * Creates new form ManageProduct
     */
    public ManageProduct() {
        initComponents();
        setLocation(0, 0);
        
    }

    private boolean validateFields(String formType) {
     if (formType.equals("edit") && (!txtName.getText().equals("") && !txtPrice.getText().equals("") && !txtBarcode.getText().equals(""))) {
    return false;
} else if (formType.equals("new") && (!txtName.getText().equals("") && !txtPrice.getText().equals("") && !txtBarcode.getText().equals("") && !txtQuantity.getText().equals(""))) {
    return false;
} else {
    return true;
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

        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProduct = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        lblQuantity = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtBarcode = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/All_page_Background.png"))); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/All_page_Background.png"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Quantity", "Price", "Barcode"
            }
        ));
        tableProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableProduct);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 500, 520));

        jLabel1.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 36)); // NOI18N
        jLabel1.setText("Manage Product");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 390, 31));

        jLabel2.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        jLabel2.setText("Name");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, -1, -1));
        getContentPane().add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 270, 30));

        btnSave.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 370, 100, 30));

        lblQuantity.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        lblQuantity.setText("Quantity");
        getContentPane().add(lblQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, -1, -1));
        getContentPane().add(txtQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 100, 30));

        jLabel4.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        jLabel4.setText("Price");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 250, -1, -1));

        txtPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPriceActionPerformed(evt);
            }
        });
        getContentPane().add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 270, 100, 30));

        jLabel5.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        jLabel5.setText("Barcode");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 310, -1, -1));
        getContentPane().add(txtBarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, 270, 30));

        jLabel6.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        jLabel6.setText("rupees");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 280, 77, -1));

        jLabel7.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        jLabel7.setText("units");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 220, 77, 20));

        btnUpdate.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 370, 100, 30));

        btnReset.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        getContentPane().add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 410, 100, 30));

        btnClose.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        getContentPane().add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 450, 210, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/All_page_Background.png"))); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/All_page_Background.png"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 840, 260));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/All_page_Background.png"))); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 60, 250));

        jButton1.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 410, 100, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/All_page_Background.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 800, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tableProduct.getModel();
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from product ");
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("product_pk"), rs.getString("name"), rs.getString("quantity"), rs.getString("price"), rs.getString("barcode")});
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

        btnUpdate.setEnabled(false);


    }//GEN-LAST:event_formComponentShown

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
 // Retrieve input values
    String name = txtName.getText();
    String quantity = txtQuantity.getText();
    String price = txtPrice.getText();
    String barcode = txtBarcode.getText();

    // Validate fields
    if (validateFields("new")) {
        JOptionPane.showMessageDialog(null, "All fields are required");
        return; // Exit the method if validation fails
    }

    try {
        Connection con = ConnectionProvider.getCon();
        // Check if the product already exists
        PreparedStatement checkStmt = con.prepareStatement("SELECT * FROM product WHERE barcode = ?");
        checkStmt.setString(1, barcode);
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            // Product exists, show a message and suggest updating
            JOptionPane.showMessageDialog(null, "Product already exists. Please update the quantity.");
        } else {
            // Product does not exist, insert new product
            PreparedStatement insertStmt = con.prepareStatement("INSERT INTO product (name, quantity, price, barcode) VALUES (?, ?, ?, ?)");
            insertStmt.setString(1, name);
            insertStmt.setString(2, quantity);
            insertStmt.setString(3, price);
            insertStmt.setString(4, barcode);
            insertStmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Product added successfully");
            setVisible(false);
            new ManageProduct().setVisible(true);
        }

        // Close resources
        rs.close();
        checkStmt.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPriceActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new ManageProduct().setVisible(true);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
  String name = txtName.getText();
    String quantityText = txtQuantity.getText();
    String price = txtPrice.getText();
    String barcode = txtBarcode.getText();

    if (validateFields("edit")) {
        JOptionPane.showMessageDialog(null, "All fields are required");
    } else {
        try {
            Connection con = ConnectionProvider.getCon();
            
            int newQuantity = totalQuantity;  // Start with the existing quantity
            if (!quantityText.equals("")) {
                newQuantity += Integer.parseInt(quantityText);  // Add new quantity to the existing quantity if provided
            }

            // Update the product details
            PreparedStatement psUpdate = con.prepareStatement("update product set name=?, quantity=?, price=?, barcode=? where product_pk=?");
            psUpdate.setString(1, name);
            psUpdate.setInt(2, newQuantity);
            psUpdate.setString(3, price);
            psUpdate.setString(4, barcode);
            psUpdate.setInt(5, productPk);  // Use the correct productPk

            psUpdate.executeUpdate();

            JOptionPane.showMessageDialog(null, "Product updated successfully");

            setVisible(false);
            new ManageProduct().setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tableProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductMouseClicked
        // TODO add your handling code here:
        int index = tableProduct.getSelectedRow();  // Get the selected row from the table
        TableModel model = tableProduct.getModel();  // Get the table model

        // Retrieve and set the ID
        String id = model.getValueAt(index, 0).toString();
        productPk = Integer.parseInt(id); // Correctly setting productPk to the selected product's ID

        // Retrieve and set the product name
        String name = model.getValueAt(index, 1).toString();
        txtName.setText(name);

        // Retrieve and set the quantity
        String quantity = model.getValueAt(index, 2).toString();
        txtQuantity.setText("");  // Clear the quantity field for the user to input new quantity if needed
        totalQuantity = Integer.parseInt(quantity); // Store the total quantity for later use
        lblQuantity.setText("Add Quantity");

        // Retrieve and set the price
        String price = model.getValueAt(index, 3).toString();
        txtPrice.setText(price);

        // Retrieve and set the barcode
        String barcode = model.getValueAt(index, 4).toString();
        txtBarcode.setText(barcode);

        btnSave.setEnabled(false);
        btnUpdate.setEnabled(true);
    }//GEN-LAST:event_tableProductMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        // Get the JTable component. Replace 'productTable' with your actual JTable variable name.
    JTable productTable = this.tableProduct; // Replace 'this.productTable' with your actual table reference

    // Get the selected row index from the table
    int selectedRowIndex = productTable.getSelectedRow(); // Use the actual JTable variable name

    if (selectedRowIndex == -1) {
        // No row selected
        JOptionPane.showMessageDialog(null, "Please select a product to delete.");
        return;
    }

    // Get the barcode of the selected row. Assume the barcode is in the 4th column (index 3)
    String barcode = productTable.getValueAt(selectedRowIndex, 4).toString(); // Adjust index as needed

    // Confirm deletion
    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this product?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        try {
            // Establish database connection
            Connection con = ConnectionProvider.getCon(); // Ensure you have a ConnectionProvider class
            PreparedStatement ps = con.prepareStatement("DELETE FROM product WHERE barcode = ?");
            ps.setString(1, barcode);


            // Execute delete statement
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                // Notify user and refresh table
                JOptionPane.showMessageDialog(null, "Product deleted successfully.");
                refreshTableData(); // Refresh table data
            } else {
                // Notify user if no rows were affected
                JOptionPane.showMessageDialog(null, "Product not deleted.");
            }

            // Close resources
            ps.close();
        } catch (Exception e) {
            // Handle exceptions
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void refreshTableData() {
    DefaultTableModel model = (DefaultTableModel) tableProduct.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        Connection con = ConnectionProvider.getCon();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM product");

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("product_pk"),
                rs.getString("name"),
                rs.getString("quantity"),
                rs.getString("price"),
                rs.getString("barcode")
            });
        }

        // Close resources
        rs.close();
        st.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}

    
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
            java.util.logging.Logger.getLogger(ManageProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageProduct().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JTable tableProduct;
    private javax.swing.JTextField txtBarcode;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQuantity;
    // End of variables declaration//GEN-END:variables
}
