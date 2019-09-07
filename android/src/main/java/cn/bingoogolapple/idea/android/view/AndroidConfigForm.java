package cn.bingoogolapple.idea.android.view;

import cn.bingoogolapple.idea.android.persistent.AndroidState;
import com.intellij.openapi.ui.Messages;

import javax.swing.*;

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

    public JPanel getContentView() {
        return contentView;
    }
}
