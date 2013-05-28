package app;

public class MailCounter {
	int numberOfMailsToSend;
	int counter = 0;

	public int getNumberOfMailsToSend() {
		return numberOfMailsToSend;
	}

	public void setNumberOfMailsToSend(int numberOfMailsToSend) {
		this.numberOfMailsToSend = numberOfMailsToSend;
	}

	public void counter() {
		this.counter = this.counter + 1;
	}

	public void revertMailsToSend() {
		this.numberOfMailsToSend = this.numberOfMailsToSend - 1;
	}

	public int getCount() {
		return this.counter;
	}

}
