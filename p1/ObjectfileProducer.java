package p1;

import java.io.*;

public class ObjectfileProducer implements MessageProducer
{
    private int times;
    private int delay;
    private int size;
    Message[] messages;

    /**
     * Constructor for the objectFileProducer wich reads data from a dat file wich contains
     * integers and objects of messages
     * @param filename
     */
    public ObjectfileProducer(String filename)
    {
        try(FileInputStream fIS = new FileInputStream(filename);
            BufferedInputStream bIS = new BufferedInputStream(fIS);
            ObjectInputStream oIS = new ObjectInputStream(bIS))
        {
            times = oIS.readInt();
            delay = oIS.readInt();
            size = oIS.readInt();
            messages = new Message[size];

            for (int i = 0; i < size; i++)
            {
                messages[i] = (Message) oIS.readObject();
            }
        }catch(IOException e)
        {
            System.err.println("IOException");
        }catch (ClassNotFoundException e)
        {
            System.err.println("Class not found");
            e.printStackTrace();
        }

    }

    @Override
    public int delay()
    {
        return delay;
    }

    @Override
    public int times()
    {
        return delay;
    }

    @Override
    public int size()
    {
        return (messages == null) ? 0 : messages.length;
    }

    @Override
    public Message nextMessage()
    {
        return null;
    }
}
