package p1;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class MessageManager extends Thread
{
    private PropertyChangeSupport propertyChangeSupport;
    private Buffer<Message> messageBuffer;

    public MessageManager(Buffer<Message> messageBuffer)
    {
        this.messageBuffer = messageBuffer;
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    @Override
    public void run()
    {
        Message message;
        while(!Thread.interrupted())
        {
            try
            {
                message = messageBuffer.get();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
                return;
            }
            propertyChangeSupport.firePropertyChange("text", null,message);
        }
    }

    public void addListener(PropertyChangeListener propertyChangeListener)
    {
        propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }


}
