package gui.calculatorFrame.logic;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.concurrent.Task;

public class TimerTask extends Task<Void>{
	 
	boolean run = true;
	
	private Date dateSaver;
	private static long milis;
	
	@SuppressWarnings("deprecation")
	@Override
	protected Void call() throws Exception {
		Date startDate = new Date();
		long milisTmp = 0;
		while (run) {
			Date actualDate = new Date();
			milisTmp = actualDate.getTime() - startDate.getTime();
			dateSaver = new Date(milis + milisTmp);
			dateSaver.setHours(dateSaver.getHours() - 1);
			updateMessage("Spielzeit: " + getTieStringFromDateSaver());
			System.out.println("Zeit: " + getTieStringFromDateSaver());
			Thread.sleep(500);
		}
		milis += milisTmp;
		return null;
	}

	public void setRunning(boolean run){
		this.run = run;
	}

	public String getTimeAsString() {
		return "Spielzeit: " + getTieStringFromDateSaver();
	}

	@SuppressWarnings("deprecation")
	private String getTieStringFromDateSaver() {
		String hours = "";
		if (dateSaver.getHours() != 0 ) {
			if (dateSaver.getHours() < 10) {
				hours = "0" + dateSaver.getHours() + ":";
			}else {
				hours = dateSaver.getHours() + ":";
			}
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
		System.out.println("DateSaver: hh = " + (dateSaver.getHours()) + ", mm = " + dateSaver.getMinutes() + 
				", ss = " + dateSaver.getSeconds());
		return hours + formatter.format(dateSaver);
	}
	public Date getTimeAsDate() {
		return dateSaver;
	}

	public void reset() {
		dateSaver = new Date(0);
		milis = 0;
	}
}
