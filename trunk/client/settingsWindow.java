/*
 * settingsWindow.java
 *
 * Created on Dec 1, 2009, 5:25:45 PM
 */

package client;

/**
 *
 * @author Jason
 */
public class settingsWindow extends javax.swing.JFrame {

    //make private?
    public clientSettings settings = new clientSettings();

    /** Creates new form settingsWindow */
    public settingsWindow() {
        initComponents();
        //settings = new clientSettings();

        //initialize controls to current setting states

        //swear filter
        if (settings.isSwearFilterOn() == true) rb_swearFilterOn.setSelected(true);
        else if (settings.isSwearFilterOn() == false) rb_swearFilterOff.setSelected(true);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbgroup_swearFilter = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        rb_swearFilterOn = new javax.swing.JRadioButton();
        rb_swearFilterOff = new javax.swing.JRadioButton();
        btn_cancel = new javax.swing.JButton();
        btn_ok = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jColorChooser1 = new javax.swing.JColorChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QuickChat Settings");

        jLabel1.setText("Swearing Filter:");
        jLabel1.setName("jLabel1"); // NOI18N

        rbgroup_swearFilter.add(rb_swearFilterOn);
        rb_swearFilterOn.setText("On");
        rb_swearFilterOn.setName("rb_swearFilterOn"); // NOI18N
        rb_swearFilterOn.setNextFocusableComponent(rb_swearFilterOff);

        rbgroup_swearFilter.add(rb_swearFilterOff);
        rb_swearFilterOff.setText("Off");
        rb_swearFilterOff.setName("rb_swearFilterOff"); // NOI18N

        btn_cancel.setText("Cancel");
        btn_cancel.setName("btn_cancel"); // NOI18N
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        btn_ok.setText("OK");
        btn_ok.setName("btn_ok"); // NOI18N
        btn_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_okActionPerformed(evt);
            }
        });

        jLabel2.setText("Font Colour:");
        jLabel2.setName("jLabel2"); // NOI18N

        jColorChooser1.setName("jColorChooser1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(321, Short.MAX_VALUE)
                .addComponent(btn_ok)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cancel)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rb_swearFilterOn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rb_swearFilterOff)
                .addContainerGap(276, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(379, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jColorChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(rb_swearFilterOn)
                    .addComponent(rb_swearFilterOff))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jColorChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancel)
                    .addComponent(btn_ok))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        //CANCEL - don't save changes
        dispose();
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_okActionPerformed
        //OK - save settings

        //swear filter
        if (rb_swearFilterOn.isSelected()) settings.setSwearFilterOn(true);
        else if (rb_swearFilterOff.isSelected()) settings.setSwearFilterOn(false);

        //colour chooser
        
        dispose();
    }//GEN-LAST:event_btn_okActionPerformed

    
    public static void settings() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new settingsWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_ok;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton rb_swearFilterOff;
    private javax.swing.JRadioButton rb_swearFilterOn;
    private javax.swing.ButtonGroup rbgroup_swearFilter;
    // End of variables declaration//GEN-END:variables

}
