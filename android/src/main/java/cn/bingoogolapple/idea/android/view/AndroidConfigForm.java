package cn.bingoogolapple.idea.android.view;

import cn.bingoogolapple.idea.android.persistent.AndroidState;
import com.intellij.openapi.ui.Messages;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;

public class AndroidConfigForm {
    private JTextField usernameEt;
    private JTextField emailEt;
    private JButton confirmBtn;
    private JPanel contentView;

    public AndroidConfigForm() {
        confirmBtn.addActionListener(e -> Messages.showMessageDialog(
                "我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容",
                "我是标题我是标题我是标题我是标题我是标题我是标题",
                Messages.getInformationIcon()
        ));
    }

    public void reset(AndroidState androidState) {
        usernameEt.setText(androidState.getUsername());
        emailEt.setText(androidState.getEmail());
    }

    public AndroidState getState() {
        AndroidState androidState = new AndroidState();
        androidState.setUsername(usernameEt.getText());
        androidState.setEmail(emailEt.getText());
        return androidState;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentView = new JPanel();
        contentView.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        usernameEt = new JTextField();
        contentView.add(usernameEt, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        emailEt = new JTextField();
        contentView.add(emailEt, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        confirmBtn = new JButton();
        confirmBtn.setText("Button");
        contentView.add(confirmBtn, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentView;
    }
}
