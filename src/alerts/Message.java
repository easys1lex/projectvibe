package alerts;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


/**
 * 
 */
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

	public Message(long created, String nachricht) {
		// TODO Auto-generated constructor stub
		this.message = nachricht;
		this.type = "AUSGEFÜHRT";
		this.setCreated(created);
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

	public long getCreated() {
		ZoneId zoneId = ZoneId.systemDefault();
		return this.created.atZone(zoneId).toEpochSecond();
	}

	public String getNachricht() {
		// TODO Auto-generated method stub
		return this.message;
	}

	public void setCreated(long created) {
		this.created =  LocalDateTime.ofInstant(Instant.ofEpochMilli(created), ZoneId.systemDefault());
		
	}

}