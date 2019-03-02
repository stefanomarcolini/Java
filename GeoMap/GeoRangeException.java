package ***.***.geomap.exceptions;

/**
 * To be thrown when a value is not in between a specific range of values
 */
public class GeoRangeException extends IllegalArgumentException {

    private static final long serialVersionUID = -***_***_***_***_***_***_*L;

    /**
     * Default Constructor
     */
    public GeoRangeException() {
        super();
    }

    /**
     * Constructor with message
     * @param s String
     */
    public GeoRangeException(String s) {
        super(s);
    }

    static GeoRangeException forInputString(String s) {
        return new GeoRangeException("For input string: \"" + s + "\"");
    }
}
