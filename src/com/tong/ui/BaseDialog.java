package com.tong.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tong on 2016/11/21.
 */
public class BaseDialog extends DialogWrapper {

    private String message;
    private JTextField classNameField;

    public BaseDialog(@NotNull String message,@Nullable Project project) {
        super(project);
        this.message = message;
        init();
        setTitle("生成MVP结构");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {

        JPanel content = new JPanel(new GridLayout(0, 1));

        JTextPane messageComponent =  Messages.configureMessagePaneUi(new JTextPane(), message);
        content.add(messageComponent);

        classNameField = new JTextField(30);
        content.add(classNameField);

        return content;
    }

    @NotNull
    public String getClassNamePrefix(){
        return classNameField.getText();
    }

    @Override
    protected void doOKAction() {
        String text = classNameField.getText();
        if (text.length() == 0) {
            return;
        }
        if (!text.matches("[a-zA-z$_\u4e00-\u9fa5][0-9a-zA-z$_\u4e00-\u9fa5]*")) {
            JOptionPane.showMessageDialog(classNameField, "Java能这样命名吗？", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        super.doOKAction();
    }

}
