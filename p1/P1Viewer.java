package p1;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class P1Viewer extends Viewer implements PropertyChangeListener
{
    public P1Viewer(MessageManager messageManager, int width, int height)
    {
        super(width, height);
        messageManager.addListener(this);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        Object source = evt.getNewValue();
        if (evt.getPropertyName().equals("text") && source instanceof Message) // om newvalue är en instans av message
        {
            super.setMessage((Message) source);
        }
    }
}
