package gui.calculatorFrame.logic;

import javax.management.InvalidAttributeValueException;

public class Time {

	private byte minutes;
	private byte seconds;
	private int hours;

	public byte getMinutes() {
		return minutes;
	}
	public byte getSeconds() {
		return seconds;
	}
	public int getHours() {
		return hours;
	}
	public void setMinutes(int minutes) {
		if (minutes >= 60) {
			hours = minutes / 60;
			minutes = (byte) (minutes % 60);
		}else {			
			this.minutes = (byte) minutes;
		}
	}
	public void setSeconds(int seconds) {
		if (seconds >= 60) {
			setMinutes((seconds / 60));
			seconds = (byte) (seconds % 60);
		}else {			
			this.seconds = (byte) seconds;
		}
	}
	public void setHours(int hours) {
		this.hours = hours;
	}

	@Override
	public String toString() {
		String hoursString = "";
		String minutesString = "";
		String secondsString = "";
		if (hours != 0) {
			hoursString = hours + ":";
		}
		
		if (minutes < 10) {
			minutesString = "0" + minutes + ":";
		}else {
			minutesString = minutes + ":";
		}
		
		if (seconds< 10) {
			secondsString = "0" + seconds;
		}else {
			secondsString = seconds + "";
		}
		
		return hoursString + minutesString + secondsString;
	}

	public void addTime(int hours, int minutes, int seconds) {
		int tmpHours = hours + this.hours;
		int tmpMinutes = minutes + this.minutes;
		int tmpSeconds = seconds + this.seconds;

		if (tmpSeconds >= 60) {
			tmpMinutes += tmpSeconds / 60;
			tmpSeconds = tmpSeconds % 60;
		}
		if (tmpMinutes >= 60) {
			tmpHours += tmpMinutes / 60;
			tmpMinutes = tmpMinutes % 60;
		}

		this.hours = tmpHours;
		this.minutes = (byte) tmpMinutes;
		this.seconds = (byte) tmpSeconds;

	}
	/**
	 * 
	 * @param String im Format und nur Zahlen hh:mm:ss, sonst ->
	 * @throws NumberFormatException, InvalidAttributeValueException
	 */
	public void addTime(String string) throws NumberFormatException, InvalidAttributeValueException  {
		String[] splittedString = string.split(":");
		
		if (splittedString.length > 3) {
			throw new InvalidAttributeValueException(string);
		}
		
		addTime(Integer.parseInt(splittedString[0]), Integer.parseInt(splittedString[1]),
				Integer.parseInt(splittedString[2]));
	}
}
