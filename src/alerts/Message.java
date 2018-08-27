package alerts;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Stack;



public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private LocalDateTime created;
	private String message;

	public Message(String message) {
		this.message = message;
		created = LocalDateTime.now();
	}
	public String toString() {
		return created.getDayOfMonth()+"."+created.getMonthValue()+"."+created.getYear() + " um "+created.getHour()+"Uhr\t"+ message;
		
	}

}
