package com.testapp.cws.googlerouteplanner;

import org.json.JSONObject;

/**
 * Created by cws on 25/7/17.
 */

public interface GoogleRoutePlannerAdapterDataReceived {
    void getGoogleMapDirectionData(JSONObject jsonObject);

}
