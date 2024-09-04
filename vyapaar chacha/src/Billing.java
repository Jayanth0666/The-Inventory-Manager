/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.event.DocumentEvent;
import javax.swing.Timer;
import javax.swing.event.DocumentListener;
import dao.ConnectionProvider;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jayanth
 */
public class Billing extends javax.swing.JFrame {

    /**
     * Creates new form Billing
     */
    private Timer timer;
    private int productPk = 0;
    private int finalTotalPrice = 0;
    private String orderId = "";
    private Map<String, Integer> initialQuantities = new HashMap<>();

    public Billing() {
        initComponents();
        setLocationRelativeTo(null);
        // Allow the user to input their desired quantity
        txtProductQuantity.setEditable(true);
        
         DefaultTableModel productModel = new DefaultTableModel(
        new Object[]{"ID", "Name", "Quantity", "Price", "Barcode"}, 0);
    tableProduct.setModel(productModel);

        // Add a DocumentListener to the txtProductBarcode field
        txtProductBarcode.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleBarcodeInput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleBarcodeInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleBarcodeInput();
            }
        });

    }

    private void handleBarcodeInput() {
        // Check if the barcode length is 13 characters
        String itemCode = txtProductBarcode.getText().trim();
        if (itemCode.length() == 13) {
            // Stop the timer if the barcode length is 13
            if (timer != null && timer.isRunning()) {
                timer.stop();
            }
            // Fetch product details if barcode length is 13
            fetchProductDetails();
        } else {
            // Start or restart the timer if barcode length is not 13
            if (timer != null && timer.isRunning()) {
                timer.restart();
            } else {
                timer = new Timer(300, e -> fetchProductDetails());
                timer.setRepeats(false);
                timer.start();
            }
        }
    }

    private void fetchProductDetails() {
        String itemCode = txtProductBarcode.getText().trim();
        System.out.println("Barcode Scanned: " + itemCode);

        // Return if itemCode is empty
        if (itemCode.isEmpty()) {
            return;
        }

        try {
            Connection con = ConnectionProvider.getCon();  // Assuming you have a connection provider class
            PreparedStatement ps = con.prepareStatement("SELECT * FROM product WHERE barcode = ?");
            ps.setString(1, itemCode);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String productName = rs.getString("name");
                double productPrice = rs.getDouble("price");
                int productQuantity = rs.getInt("quantity");

                // Check if productName is empty
                if (productName == null || productName.isEmpty()) {
                    System.out.println("Product name is empty or null.");
                    JOptionPane.showMessageDialog(null, "Product name is empty.");
                } else {
                    System.out.println("Product Found: " + productName + ", Price: " + productPrice + ", Available Quantity: " + productQuantity);

                    txtProductName.setText(productName);
                    txtProductQuantity.requestFocus(); // Move focus to quantity field for user input

                    // Store price and quantity in client properties
                    txtProductName.putClientProperty("price", productPrice);
                    txtProductName.putClientProperty("availableQuantity", productQuantity);
                }
            } else {
                // Product not found
                JOptionPane.showMessageDialog(null, "Product not found.");
                clearProductFields();
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Product not listed in the database. Kindly first add the product.");
        }

    }

    private void updateTotalPrice() {
        DefaultTableModel cartModel = (DefaultTableModel) tableCart.getModel();
        int rowCount = cartModel.getRowCount();
        double total = 0.0;

        for (int i = 0; i < rowCount; i++) {
            Object subtotalObj = cartModel.getValueAt(i, 4);
            if (subtotalObj instanceof Number) {
                double subtotal = ((Number) subtotalObj).doubleValue();
                total += subtotal;
            }
        }

        lblFinalTotalPrice.setText(String.format("%.2f", total));
    }

    public void addItem(String itemName, int quantity, double price) {
        try {
            // Check if the item already exists in the inventory
            String checkItemQuery = "SELECT quantity FROM inventory WHERE item_name = ?";
            Connection con = ConnectionProvider.getCon();
            PreparedStatement checkItemStmt = con.prepareStatement(checkItemQuery);
            checkItemStmt.setString(1, itemName);
            ResultSet rs = checkItemStmt.executeQuery();

            if (rs.next()) {
                // Item exists, update the quantity
                int existingQuantity = rs.getInt("quantity");
                int newQuantity = existingQuantity + quantity;

                String updateQuery = "UPDATE inventory SET quantity = ? WHERE item_name = ?";

                PreparedStatement updateStmt = con.prepareStatement(updateQuery);
                updateStmt.setInt(1, newQuantity);
                updateStmt.setString(2, itemName);
                updateStmt.executeUpdate();

                System.out.println("Item updated successfully: " + itemName + " - New Quantity: " + newQuantity);
            } else {
                // Item does not exist, insert it as a new item
                String insertQuery = "INSERT INTO inventory (item_name, quantity, price) VALUES (?, ?, ?)";
                PreparedStatement insertStmt = con.prepareStatement(insertQuery);
                insertStmt.setString(1, itemName);
                insertStmt.setInt(2, quantity);
                insertStmt.setDouble(3, price);
                insertStmt.executeUpdate();

                System.out.println("Item added successfully: " + itemName + " - Quantity: " + quantity);
            }

            // Close resources
            rs.close();
            checkItemStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   private void clearProductFields() {
        txtProductBarcode.setText("");
        txtProductName.setText("");
        txtProductQuantity.setText("");
     
        
       
    }
    
    
    private void clearProductFields1() {
   
        lblFinalTotalPrice.setText("00000");
        txtCustomerMobileNumber.setText("");
        
         DefaultTableModel cartModel = (DefaultTableModel) tableCart.getModel();
    cartModel.setRowCount(0); // This will remove all rows from the cart table
    }
    // other existing methods

    public String getUniqueId(String prefix) {
        return prefix + System.nanoTime();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProduct = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtProductBarcode = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtProductQuantity = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCart = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtCustomerMobileNumber = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        lblFinalTotalPrice = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 36)); // NOI18N
        jLabel1.setText("Billing");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, -1, 57));

        tableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Quantity", "Price", "Barcode"
            }
        ));
        jScrollPane1.setViewportView(tableProduct);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 101, 487, 424));

        jLabel2.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        jLabel2.setText("Inventory Overview");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 67, -1, -1));

        jLabel3.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        jLabel3.setText("Scan the barcode to enter the product details");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 536, -1, -1));

        jLabel4.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        jLabel4.setText("Barcode :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 560, -1, 30));

        txtProductBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductBarcodeActionPerformed(evt);
            }
        });
        getContentPane().add(txtProductBarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 560, 354, 30));

        jLabel5.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        jLabel5.setText("Product Name :");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 614, -1, -1));
        getContentPane().add(txtProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 608, 354, 30));

        jLabel6.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        jLabel6.setText("Quantity :");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 656, -1, 30));
        getContentPane().add(txtProductQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 656, 354, 30));

        jButton1.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 14)); // NOI18N
        jButton1.setText("Add to Cart");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 715, 160, 30));

        jLabel7.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        jLabel7.setText("Cart");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(972, 67, -1, -1));

        tableCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Quantity", "Price", "Sub Total"
            }
        ));
        jScrollPane2.setViewportView(tableCart);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(881, 101, -1, 424));

        jLabel8.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 14)); // NOI18N
        jLabel8.setText("Mobile Number :");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(881, 596, 129, -1));
        getContentPane().add(txtCustomerMobileNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(1042, 590, 291, 30));

        jButton2.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 14)); // NOI18N
        jButton2.setText("Print Bill");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(881, 631, 129, 30));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg.png"))); // NOI18N
        jLabel15.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg.png"))); // NOI18N
        jLabel15.setIconTextGap(0);
        jLabel15.setVerifyInputWhenFocusTarget(false);
        jLabel15.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 290, 920));

        jButton3.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 14)); // NOI18N
        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1042, 631, 130, 30));

        jButton4.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 14)); // NOI18N
        jButton4.setText("Close");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1204, 631, 129, 30));

        jLabel9.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 14)); // NOI18N
        jLabel9.setText("Total :");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(881, 562, 70, -1));

        lblFinalTotalPrice.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 14)); // NOI18N
        lblFinalTotalPrice.setText("00000");
        getContentPane().add(lblFinalTotalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(1042, 562, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/All_page_Background.png"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 430, -1, 340));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/All_page_Background.png"))); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 0, 520, 570));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/All_page_Background.png"))); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 430, 850, 340));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/All_page_Background.png"))); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        txtProductName.setEditable(true);
        txtProductQuantity.setEditable(true);
        txtProductBarcode.setEditable(true); // You may want to keep this editable to allow rescan
        DefaultTableModel productModel = (DefaultTableModel) tableProduct.getModel();

        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from product");

            while (rs.next()) {
                String productName = rs.getString("name");
                int quantity = rs.getInt("quantity");

                // Store initial quantities
                initialQuantities.put(productName, quantity);

                productModel.addRow(new Object[]{
                    rs.getString("product_pk"),
                    productName,
                    quantity,
                    rs.getString("price"),
                    rs.getString("Barcode")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }


    }//GEN-LAST:event_formComponentShown

    private int productPkCounter = 0;

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        // Get the cart model
        DefaultTableModel cartModel = (DefaultTableModel) tableCart.getModel();
        String productName = txtProductName.getText().trim();
        String quantityText = txtProductQuantity.getText().trim();

        if (productName.isEmpty() || quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter all product details.");
            return;
        }

        int quantityToAdd;
        try {
            quantityToAdd = Integer.parseInt(quantityText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid quantity entered.");
            return;
        }

        // Get product details from client properties
        double productPrice = (double) txtProductName.getClientProperty("price");
        int availableQuantity = (int) txtProductName.getClientProperty("availableQuantity");

        if (quantityToAdd > availableQuantity) {
            JOptionPane.showMessageDialog(null, "Insufficient stock available.");
            return;
        }

        // Deduct the quantity from the product table
        try {
            Connection con = ConnectionProvider.getCon();
            String updateProductQuery = "UPDATE product SET quantity = quantity - ? WHERE name = ?";
            PreparedStatement ps = con.prepareStatement(updateProductQuery);
            ps.setInt(1, quantityToAdd);
            ps.setString(2, productName);
            ps.executeUpdate();
            ps.close();

            // Update the quantity in the UI
            for (int i = 0; i < tableProduct.getRowCount(); i++) {
                if (tableProduct.getValueAt(i, 1).equals(productName)) {
                    int newQuantity = availableQuantity - quantityToAdd;
                    tableProduct.setValueAt(newQuantity, i, 2);  // Update the displayed quantity
                    break;
                }
            }

            // Add the item to the cart
            boolean itemExists = false;
            for (int i = 0; i < cartModel.getRowCount(); i++) {
                String existingProductName = (String) cartModel.getValueAt(i, 1);
                if (existingProductName.equals(productName)) {
                    // Update the existing item in the cart
                    int existingQuantity = (int) cartModel.getValueAt(i, 2);
                    double existingSubtotal = (double) cartModel.getValueAt(i, 4);
                    int newQuantity = existingQuantity + quantityToAdd;
                    double newSubtotal = newQuantity * productPrice;

                    cartModel.setValueAt(newQuantity, i, 2);
                    cartModel.setValueAt(newSubtotal, i, 4);

                    itemExists = true;
                    break;
                }
            }

            if (!itemExists) {
                // Add new item to the cart
                double subtotal = quantityToAdd * productPrice;
                cartModel.addRow(new Object[]{productPk++, productName, quantityToAdd, productPrice, subtotal});
            }

            updateTotalPrice();

            JOptionPane.showMessageDialog(null, "Item added to cart successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating product quantity: " + e.getMessage());
        }

        clearProductFields();  // Clear fields for the next entryf
    }//GEN-LAST:event_jButton1ActionPerformed


    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
          // TODO add your handling code here:
    // Get the cart model
    DefaultTableModel cartModel = (DefaultTableModel) tableCart.getModel();

    // Restore quantities in the product table
    Connection connection = null;
    PreparedStatement stmt = null;

    try {
        connection = ConnectionProvider.getCon(); // Use your connection provider

        // Loop through the cart table to get the quantities to restore
        for (int i = 0; i < cartModel.getRowCount(); i++) {
            String productName = (String) cartModel.getValueAt(i, 1);
            int quantityInCart = (int) cartModel.getValueAt(i, 2);

            // Get the original quantity from the stored map
            Integer initialQuantity = initialQuantities.get(productName);
            if (initialQuantity != null) {
                // Update the product quantity by adding back the quantity in the cart
                String updateProductQuery = "UPDATE product SET quantity = ? WHERE name = ?";
                stmt = connection.prepareStatement(updateProductQuery);
                stmt.setInt(1, initialQuantity);
                stmt.setString(2, productName);
                stmt.executeUpdate();

                System.out.println("Restored quantity for product: " + productName);
            }
        }

        // Clear the cart table
        cartModel.setRowCount(0);

        // Update the total price label
        lblFinalTotalPrice.setText("00000");

        // Refresh the product table
        refreshProductTable();

        // Show a confirmation message
        JOptionPane.showMessageDialog(null, "Cart has been reset and quantities restored.");
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error resetting cart: " + e.getMessage());
    } finally {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_jButton3ActionPerformed

    // Method to refresh the product table
private void refreshProductTable() {
    DefaultTableModel productModel = (DefaultTableModel) tableProduct.getModel();
    Connection connection = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
        connection = ConnectionProvider.getCon(); // Use your connection provider
        stmt = connection.createStatement();
        String query = "SELECT product_pk, name, quantity, price, barcode FROM product"; // Adjust query to include id and barcode
        rs = stmt.executeQuery(query);

        // Clear the existing rows in the product table
        productModel.setRowCount(0);

        // Populate the product table with updated data
        while (rs.next()) {
            int id = rs.getInt("product_pk");
            String name = rs.getString("name");
            int quantity = rs.getInt("quantity");
            double price = rs.getDouble("price");
            String barcode = rs.getString("barcode");
            productModel.addRow(new Object[]{id, name, quantity, price, barcode});
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error refreshing product table: " + e.getMessage());
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtProductBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductBarcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductBarcodeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
          // Get the mobile number from the text field
    String mobileNumber = txtCustomerMobileNumber.getText().trim();

    // Check if mobile number is not empty
    if (mobileNumber.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter a mobile number.");
        return;
    }

    Connection connection = null;
    PreparedStatement stmt = null;

    try {
        connection = ConnectionProvider.getCon(); // Use your connection provider

        // Store the mobile number in the customer table
        String insertCustomerQuery = "INSERT INTO customer (phone_number) VALUES (?)";
        stmt = connection.prepareStatement(insertCustomerQuery);
        stmt.setString(1, mobileNumber);
        stmt.executeUpdate();

        // Print the bill
        printBill();

        JOptionPane.showMessageDialog(null, "Bill printed and mobile number stored successfully.");
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error processing bill: " + e.getMessage());
    } finally {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_jButton2ActionPerformed

private void printBill() {
    DefaultTableModel cartModel = (DefaultTableModel) tableCart.getModel();
    StringBuilder billText = new StringBuilder();

    billText.append("---------- Bill ----------\n");

    // Loop through the cart table to build the bill text
    for (int i = 0; i < cartModel.getRowCount(); i++) {
        String productName = (String) cartModel.getValueAt(i, 1);
        int quantity = (int) cartModel.getValueAt(i, 2);
        double price = (double) cartModel.getValueAt(i, 3); // Assuming price is in column 3

        billText.append(String.format("%s x %d @ %.2f\n", productName, quantity, price));
    }

    // Add total price
    double totalPrice = calculateTotalPrice(); // You need to implement this method
    billText.append(String.format("Total: %.2f\n", totalPrice));

    billText.append("--------------------------\n");

    // Print the bill (for demonstration purposes, printing to console)
    System.out.println(billText.toString());

    // Optionally, send the bill to a printer
    // For example: use Java Printing API to print billText.toString()
       clearProductFields1();
}

private double calculateTotalPrice() {
    double total = 0;
    DefaultTableModel cartModel = (DefaultTableModel) tableCart.getModel();

    for (int i = 0; i < cartModel.getRowCount(); i++) {
        double price = (double) cartModel.getValueAt(i, 3); // Assuming price is in column 3
        int quantity = (int) cartModel.getValueAt(i, 2);
        total += price * quantity;
    }

    return total;
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
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Billing().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblFinalTotalPrice;
    private javax.swing.JTable tableCart;
    private javax.swing.JTable tableProduct;
    private javax.swing.JTextField txtCustomerMobileNumber;
    private javax.swing.JTextField txtProductBarcode;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtProductQuantity;
    // End of variables declaration//GEN-END:variables
}
