/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import clases.paquete;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alan
 */
public class chatIndividual extends javax.swing.JFrame {

    private String id,NickLocal,nickChat,ipDestino,ipServer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    private ventanaChat parent;

    public ventanaChat getParent() {
        return parent;
    }

    public void setParent(ventanaChat parent) {
        this.parent = parent;
    }

    public String getIpServer() {
        return ipServer;
    }

    public void setIpServer(String ipServer) {
        this.ipServer = ipServer;
    }

    public String getNickLocal() {
        return NickLocal;
    }

    public void setNickLocal(String NickLocal) {
        this.NickLocal = NickLocal;
    }

    public String getNickChat() {
        return nickChat;
    }

    public void setNickChat(String nickChat) {
        this.nickChat = nickChat;
    }

    public String getIpDestino() {
        return ipDestino;
    }

    public void setIpDestino(String ipDestino) {
        this.ipDestino = ipDestino;
    }
    /**
     * Creates new form chatIndividual
     */
    public chatIndividual() {
        initComponents();
        this.enviarMsg.requestFocus();
    }
    
    public chatIndividual(String nicklocal,String Nickchat,String destino){
        initComponents();
        this.setTitle(Nickchat + " - Chat");
        this.NickLocal = nicklocal;
        this.nickChat = Nickchat;
        this.ipDestino = destino;
        this.enviarMsg.requestFocus();
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
        campoChat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        enviarMsg = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        txtDescripcion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        campoChat.setEditable(false);
        campoChat.setColumns(20);
        campoChat.setRows(5);
        jScrollPane1.setViewportView(campoChat);

        enviarMsg.setColumns(20);
        enviarMsg.setRows(1);
        jScrollPane2.setViewportView(enviarMsg);

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtDescripcion.setText("nick");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jScrollPane2)
                            .addGap(18, 18, 18)
                            .addComponent(jButton1)))
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(txtDescripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(444, 305));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            paquete enviar = new paquete();
            enviar.setId(this.getId());
            enviar.setNick(this.NickLocal);
            enviar.setIpDestino(ipDestino);
            enviar.setDescripcion("chatIndividual");
            enviar.setMsg(enviarMsg.getText());
            Socket conexion = new Socket(ipServer, 9999);
            ObjectOutputStream enviarObjeto = new ObjectOutputStream(conexion.getOutputStream());
            enviarObjeto.writeObject(enviar);
            
            enviarObjeto.close();
            conexion.close();
            campoChat.append(NickLocal + " : " + enviarMsg.getText() + "\n");
            enviarMsg.setText("");
        } catch (IOException ex) {
            Logger.getLogger(chatIndividual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(chatIndividual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(chatIndividual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(chatIndividual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(chatIndividual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chatIndividual().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextArea campoChat;
    private javax.swing.JTextArea enviarMsg;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
