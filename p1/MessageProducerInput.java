package p1;

public class MessageProducerInput
{
    private Buffer<MessageProducer> messageProducerBuffer;
    public MessageProducerInput(Buffer<MessageProducer> messageProducerBuffer)
    {
        this.messageProducerBuffer = messageProducerBuffer;
    }

    public void addMessageProducer(MessageProducer m)
    {
        messageProducerBuffer.put(m);
    }

}
