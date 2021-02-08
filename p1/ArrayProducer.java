package p1;

public class ArrayProducer implements MessageProducer {
	private Message[] messages;
	private int delay = 0;
	private int times = 0;
	private int currentIndex = -1;

	/**
	 *
	 * @param messages
	 * @param times
	 * @param delay
	 */
	public ArrayProducer(Message[] messages, int times, int delay) {
		this.messages = messages;
		this.times = times;
		this.delay = delay;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public int delay() {
		return delay;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public int times() {
		return times;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public int size() {
		return (messages==null) ? 0 : messages.length;
	}

	/**
	 * @Override
	 * @return
	 */
	public Message nextMessage() {
		if(size()==0)
		    return null;
		currentIndex = (currentIndex+1) % messages.length;
		return messages[currentIndex];
	}
}

