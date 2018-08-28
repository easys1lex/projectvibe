package alerts;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Stack;

public class Message implements Serializable, Comparable {
	private static final long serialVersionUID = 1L;
	protected String type;

	private LocalDateTime created;
	private String message;

	public Message(String message) {
		this.message = message;
		created = LocalDateTime.now();
		type = "NACHRICHT";
	}

	public String toString() {
		return created.getDayOfMonth() + "." + created.getMonthValue() + "." + created.getYear() + " um "
				+ created.getHour() + ":" + created.getMinute() + " Uhr \t [" + type + "] \t " + message;
	}

	@Override
	public int compareTo(Object o) {
		Message temp = (Message) o;
		ZoneId zoneId = ZoneId.systemDefault();
		long thisTime = this.created.atZone(zoneId).toEpochSecond();
		long tempTime = temp.created.atZone(zoneId).toEpochSecond();
		if (thisTime > tempTime) {
			return -1;
		} else if (thisTime < tempTime) {
			return 1;
		} else {
			return 0;
		}

	}

}