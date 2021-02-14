package gui.optionsFrame;

public class OptionsData {

	//TODO: In diesem Objekt werden die Einstellungen aus den OptionsFrame geseichert und geladen
	// und auf das Programm angewendet
	
	private boolean shortnamePreffered;
	private boolean fullscreen = true;
	private boolean automaticTimerStart;
	
	private int cubeThrowCount = 99;
	private int coinThrowCount = 77;
	private int waitingMultiplikator = 55;
	private int lastMatchesParam = 44;
	
	public OptionsData() {
		loadOptions();
	}
	
	public boolean isShortnamePreffered() {
		return shortnamePreffered;
	}

	public void setShortnamePreffered(boolean shortnamePreffered) {
		this.shortnamePreffered = shortnamePreffered;
	}

	public int getCubeThrowCount() {
		return cubeThrowCount;
	}

	public void setCubeThrowCount(int cubeThrowCount) {
		this.cubeThrowCount = cubeThrowCount;
	}

	public int getCoinThrowCount() {
		return coinThrowCount;
	}

	public void setCoinThrowCount(int coinThrowCount) {
		this.coinThrowCount = coinThrowCount;
	}

	public boolean isFullscreen() {
		return fullscreen;
	}

	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	public boolean isAutomaticTimerStart() {
		return automaticTimerStart;
	}

	public void setAutomaticTimerStart(boolean automaticTimerStart) {
		this.automaticTimerStart = automaticTimerStart;
	}

	public int getWaitingMultiplikator() {
		return waitingMultiplikator;
	}

	public void setWaitingMultiplikator(int waitingMultiplikator) {
		this.waitingMultiplikator = waitingMultiplikator;
	}

	public int getLastMatchesParam() {
		return lastMatchesParam;
	}

	public void setLastMatchesParam(int lastMatchesParam) {
		this.lastMatchesParam = lastMatchesParam;
	}

	public void loadOptions() {
		//TODO: Gespeicherten Einstellungen aus PropertiesFile laden
	}
	public void saveOptions() {
		//TODO: Einstellungen in PropertiesFile speichern
	}
}
