package edu.umass.cs.MHLClient.sensors;

import org.json.JSONException;
import org.json.JSONObject;

import edu.umass.cs.MHLClient.devices.DeviceType;

/**
 * Wraps a gyroscope reading and defines a JSON structure that allows
 * the reading to be sent to the server.
 *
 * @author Erik Risinger
 *
 * @see SensorReading
 */
public class GyroscopeReading extends SensorReading {

    /** The change in orientation along the x-axis **/
    private double x;

    /** The change in orientation along the y-axis **/
    private double y;

    /** The change in orientation along the z-axis **/
    private double z;

    /**
     * Instantiates a gyroscope reading.
     * @param userID a 10-byte hex string identifying the current user.
     * @param deviceType identifies the device, as defined in {@link DeviceType}.
     * @param t the timestamp at which the event occurred, in Unix time by convention.
     * @param values the x, y, z readings
     */
    public GyroscopeReading(String userID, DeviceType deviceType, long t, float... values){
        super(userID, deviceType, "SENSOR_" + deviceType + "_ACCEL", t);

        this.x = values[0];
        this.y = values[1];
        this.z = values[2];
    }

    @Override
    public JSONObject toJSONObject(){
        JSONObject data = getBaseJSONObjet();
        JSONObject obj = new JSONObject();

        try {
            data.put("t", timestamp);
            data.put("x", x);
            data.put("y", y);
            data.put("z", z);

            obj.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }
}