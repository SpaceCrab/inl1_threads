package p1;

import javax.swing.*;
import java.io.Serializable;

public class Message implements Serializable
{

    private String text;
    private Icon icon;

    public Message(String text, Icon icon)
    {
        this.icon = icon;
        this.text = text;
    }

    public Icon getIcon()
    {
        return icon;
    }

    public String getText()
    {
        return text;
    }
}
