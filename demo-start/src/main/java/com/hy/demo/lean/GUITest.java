package com.hy.demo.lean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * GUITest
 *
 * @author silent
 * @date 2018/4/21
 */
public class GUITest {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setBounds(500, 200, 500, 500);
        jFrame.setLayout(new FlowLayout());

        JButton jButton = new JButton("我是一个button");
        jFrame.add(jButton);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });

        jFrame.setVisible(true);
    }
}
