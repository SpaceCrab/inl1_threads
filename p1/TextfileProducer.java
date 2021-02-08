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

    @Override
    public int delay()
    {
        return delay;
    }

    @Override
    public int times()
    {
        return times;
    }

    @Override
    public int size()
    {
        return (messages == null) ? 0 : messages.length;
    }

    @Override
    public Message nextMessage()
    {
        if(size()==0)
            return null;
        currentIndex = (currentIndex+1) % messages.length;
        return messages[currentIndex];
    }
}
