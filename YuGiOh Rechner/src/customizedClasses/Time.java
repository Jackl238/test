package customizedClasses;

public class Time {

	private int seconds;
	private int minutes;
	private int hours;

	private String timeString = "00:00:00";

	public Time() {
		
	}
	public Time(String time) {
		addTime(time);
	}
	public Time(java.sql.Time time) {
		addTime(time.toString());
	}

	public int getSeconds() {
		return seconds;
	}

	public int getMinutes() {
		return minutes;
	}

	public int getHours() {
		return hours;
	}

	public String getTimeString() {
		if (hours < 10) {
			timeString = "0" + hours + ":";
		}else {
			timeString = hours + ":";
		}
		if (minutes < 10) {
			timeString = timeString + "0" + minutes + ":";
		}else {
			timeString = timeString + minutes + ":";
		}
		if (seconds < 10) {
			timeString = timeString + "0" + seconds;
		}else {
			timeString = timeString + seconds;
		}
		return timeString;
	}

	public void setSeconds(int seconds) {
		int tmpSeconds = this.seconds + seconds;
		if (tmpSeconds >= 60) {
			this.seconds = tmpSeconds % 60;
			minutes += tmpSeconds / 60;
		}else {
			this.seconds = tmpSeconds;
		}

	}

	public void setMinutes(int minutes) {
		int tmpMinutes = this.minutes + minutes;
		if (tmpMinutes >= 60) {
			this.minutes = tmpMinutes % 60;
			hours += tmpMinutes / 60;
		}else {
			this.minutes = tmpMinutes;
		}
	}

	public void setHours(int hours) {
		this.hours = hours;
	}
	
	public void addTime(String addTime) {
		int hoursLocal;
		int minutesLocal;
		int secondsLocal;

		String[] playTimeArray = addTime.split(":");
		hoursLocal = Integer.parseInt(playTimeArray[0]);
		minutesLocal = Integer.parseInt(playTimeArray[1]);
		secondsLocal = Integer.parseInt(playTimeArray[2]);

		String[] timeArray = timeString.split(":");
		hoursLocal += Integer.parseInt(timeArray[0]);
		minutesLocal += Integer.parseInt(timeArray[1]);
		secondsLocal += Integer.parseInt(timeArray[2]);

		setSeconds(secondsLocal);
		setMinutes(minutesLocal);
		setHours(hoursLocal);
	}
    public int getPlaytimeAsMinutes() {
		getTimeString();
		String[] playTimeArray = timeString.split(":");
		int hours = Integer.parseInt(playTimeArray[0]);
		int minutes = Integer.parseInt(playTimeArray[1]);
		
		minutes += hours * 60;
		
		return minutes;
	}
    
    public double getPlaytimeAsMinutesAsDouble() {
		getTimeString();
		String[] playTimeArray = timeString.split(":");
		int hours = Integer.parseInt(playTimeArray[0]);
		double minutes = Integer.parseInt(playTimeArray[1]);
		
		minutes += hours * 60;
		
		minutes += (Double.parseDouble(playTimeArray[2]) / 60);
		
		return minutes;
	}
    
    @Override
    public String toString() {
    	return getTimeString();
    }
}
