/**
 * Eric Lundin
 * al3214
 * 2021-02-08
 */
package p1;

/**
 * this class fetches messageProducer instances and uses them to place Message instances in the messageBuffer
 * @author Eric Lundin
 * @version 1.0
 */
public class Producer
{
    private Buffer<MessageProducer> producerBuffer;
    private Buffer<Message> messageBuffer;
    private Thread thread;

    /**
     *
     * constructor initializes the producerBuffer and the messageBuffer
     * @param producerBuffer Buffer for MessageProducers
     * @param messageBuffer Buffer for Messages
     */
    public Producer(Buffer<MessageProducer> producerBuffer, Buffer<Message> messageBuffer)
    {
        this.messageBuffer = messageBuffer;
        this.producerBuffer = producerBuffer;
    }

    /**
     *Starts a new worker instance
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
         * Uses the created worker to move the Message instance from the messageProducer instance in to the messageBuffer
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
