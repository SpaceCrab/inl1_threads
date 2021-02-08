/**
 *
 */
package p1;

public class Producer
{
    private Buffer<MessageProducer> producerBuffer;
    private Buffer<Message> messageBuffer;
    private Thread thread;

    /**
     *
     * @param producerBuffer
     * @param messageBuffer
     */
    public Producer(Buffer<MessageProducer> producerBuffer, Buffer<Message> messageBuffer)
    {
        this.messageBuffer = messageBuffer;
        this.producerBuffer = producerBuffer;
    }

    /**
     *
     */
    public void start()
    {
        thread = new Worker();
        thread.start();
    }

    /**
     *
     */
    private class Worker extends Thread
    {

        /**
         * @Override
         */
        public void run()
        {
            while(!Thread.interrupted())
            {
                MessageProducer messageProducer;
                try
                {
                    messageProducer = producerBuffer.get();
                }
                catch (InterruptedException e)
                {
                    System.err.println("Producer Interrupted!!!!");
                    break;
                }

                int numberOfElements = messageProducer.size() * messageProducer.times();

                for (int i = 0; i < numberOfElements; i++)
                {
                    messageBuffer.put(messageProducer.nextMessage());

                    try
                    {
                        sleep(messageProducer.delay());
                    }
                    catch (InterruptedException e)
                    {
                        System.err.println("Producer Interrupted!!!!");
                        break;
                    }
                }
            }
        }
    }
}
