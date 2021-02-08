package p1;

import javax.swing.*;
import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TextfileProducer implements MessageProducer
{
    private int delay;
    private int times;
    private int currentIndex = -1;
    private Message[] messages;

    /**
     * Constructor for TextfileProducer
     * Takes a string for the target filename.
     * creates a BufferedReader to read strings from the target txt file
     * lines 0 through 2 are used as parameters for times, delay and length
     * lines 3 through 23 are alternatingly used as parameters for text and icon, these are stored in Message object in the messages array
     * @param filename
     */
    public TextfileProducer(String filename)
    {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),UTF_8)))
        {
            times = Integer.parseInt(br.readLine());
            delay = Integer.parseInt(br.readLine());
            int length = Integer.parseInt(br.readLine());
            Icon icon;
            String text;
            messages = new Message[length];

            for (int i = 0; i < length; i++)
            {
                text = br.readLine();
                icon = new ImageIcon(br.readLine());

                messages[i] = new Message(text, icon);
            }

        }catch (IOException e)
        {
            System.err.println("The bufferreader failed");
        }


    }

    /**
     *
     * @return
     */
    @Override
    public int delay()
    {
        return delay;
    }

    /**
     *
     * @return
     */
    @Override
    public int times()
    {
        return times;
    }

    /**
     * if messages is null returns zero
     * else returns messages.length
     * @return
     */
    @Override
    public int size()
    {
        return (messages == null) ? 0 : messages.length;
    }

    /**
     *
     * @return
     */
    @Override
    public Message nextMessage()
    {
        if(size()==0)
            return null;
        currentIndex = (currentIndex+1) % messages.length;
        return messages[currentIndex];
    }
}
