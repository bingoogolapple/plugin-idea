package cn.bingoogolapple.idea.android.view;

import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import java.util.Calendar;

public class AndroidToolWindow {
    private JPanel contentView;
    private JLabel currentDateTv;
    private JLabel currentTimeTv;
    private JLabel timeZoneTv;
    private JButton hideBtn;
    private JButton refreshBtn;

    public AndroidToolWindow(ToolWindow toolWindow) {
        hideBtn.addActionListener(e -> toolWindow.hide(null));
        refreshBtn.addActionListener(e -> createDateTime());
        this.createDateTime();
    }

    private void createDateTime() {
        // 填充当前日期
        Calendar instance = Calendar.getInstance();
        currentDateTv.setText(instance.get(Calendar.DAY_OF_MONTH) + "/"
                + (instance.get(Calendar.MONTH) + 1) + "/" +
                instance.get(Calendar.YEAR));
        currentDateTv.setIcon(new ImageIcon(getClass().getResource("/toolwindow/Calendar-icon.png")));
        // 填充当前时间
        int min = instance.get(Calendar.MINUTE);
        String strMin;
        if (min < 10) {
            strMin = "0" + min;
        } else {
            strMin = String.valueOf(min);
        }
        currentTimeTv.setText(instance.get(Calendar.HOUR_OF_DAY) + ":" + strMin);
        currentTimeTv.setIcon(new ImageIcon(getClass().getResource("/toolwindow/Time-icon.png")));
        // 填充当前时区
        long gmtOffset = instance.get(Calendar.ZONE_OFFSET);
        String gmtOffsetStr = String.valueOf(gmtOffset / 3600000);
        gmtOffsetStr = (gmtOffset > 0) ? "GMT + " + gmtOffsetStr : "GMT - " + gmtOffsetStr;
        timeZoneTv.setText(gmtOffsetStr);
        timeZoneTv.setIcon(new ImageIcon(getClass().getResource("/toolwindow/Time-zone-icon.png")));
    }

    public JPanel getContentView() {
        return contentView;
    }
}
