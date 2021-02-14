package gui.optionsFrame;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.PropertyEditor;

import javafx.beans.value.ObservableValue;


public class OptionItems implements PropertySheet.Item {

	/**
	 * Volage der Komponenten
	 * customDataMap.put("basic.My Text", "Same text");  Creates a TextField in property sheet
	 * customDataMap.put("misc.My Boolean", false); // Creates a CheckBox
	 * customDataMap.put("misc.My Number", 500); // Creates a NumericField
	 * customDataMap.put("misc.My Color", Color.ALICEBLUE); // Creates a ColorPicker
	 * customDataMap.put("misc.My Enum", OptionTestEnum.WEIN); // Creates a ChoiceBox
	 * customDataMap.put("basic.My Date", LocalDate.of(2016, Month.JANUARY, 1)); // Creates a DatePicker
	 * 
	 * mapp.put("Kategorie.Name", ÜbergebenerDatentypLegEingabeGuiFest);
	 */
	
	public static final String SHORTNAME_PREFFERED = "Spieleinstellungen.Spitzname anzeigen";
	public static final String AUTOMATIC_TIMER_START = "Spieleinstellungen.Automatischer Timerstart";
	public static final String FULLSCREN = "Spieleinstellungen.Vollbild";
	public static final String CUBE_LOOP_COUNT = "Konfigurationen.Schleifenwert Würfel";
	public static final String COIN_LOOP_COUNT = "Konfigurationen.Schleifenwert Münzwurf";
	public static final String WAITING_TIME_MULTIPLYER = "Konfigurationen.Wartezeit Multiplikator";
	public static final String LAST_MATCHES_COUNT = "Parameter.Anzahl Last Matches in Statistiken";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Map<String, Object> customDataMap = new LinkedHashMap();
	static OptionsData data = new OptionsData();
	static {
		customDataMap.put(SHORTNAME_PREFFERED, data.isShortnamePreffered());
		customDataMap.put(FULLSCREN, data.isFullscreen());
		customDataMap.put(AUTOMATIC_TIMER_START, data.isAutomaticTimerStart());
		customDataMap.put(CUBE_LOOP_COUNT, data.getCubeThrowCount());
		customDataMap.put(COIN_LOOP_COUNT, data.getCoinThrowCount());
		customDataMap.put(WAITING_TIME_MULTIPLYER, data.getWaitingMultiplikator());
		customDataMap.put(LAST_MATCHES_COUNT, data.getLastMatchesParam());
	}

	private String key;
	private String category, name;

	public OptionItems(String key) 
	{
		this.key = key;
		String[] skey = key.split("\\.", 2);
		category = skey[0];
		name = skey[1];
	}

	@Override
	public Class<?> getType() {
		return customDataMap.get(key).getClass();
	}

	@Override
	public String getCategory() {
		return category;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		// doesn't really fit into the map
		return null;
	}

	@Override
	public Object getValue() {
		return customDataMap.get(key);
	}

	@Override
	public void setValue(Object value) {
		customDataMap.put(key, value);
	}

	@Override
	public Optional<ObservableValue<? extends Object>> getObservableValue() {
		return Optional.empty();
	}

	@Override
	public Optional<Class<? extends PropertyEditor<?>>> getPropertyEditorClass() {


		return Optional.empty();
	}

	public static Map<String, Object> getMap(){
		return customDataMap;
	}
	
}
