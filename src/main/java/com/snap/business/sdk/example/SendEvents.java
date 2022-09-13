package com.snap.business.sdk.example;

import com.snap.business.sdk.api.ConversionApi;
import com.snap.business.sdk.model.CapiEvent;
import com.snap.business.sdk.util.CapiConstants;

import java.util.Arrays;

public class SendEvents {
    private final static String API_TOKEN = "INSERT_YOUR_CAPI_TOKEN";
    private final static String PIXEL_ID = "INSERT_YOUR_PIXEL_ID";
    private final static String LAUNCH_PAD_URL = "INSERT_YOUR_LAUNCH_PAD_URL";

    public static void main(String[] args) {
        ConversionApi capi = new ConversionApi(API_TOKEN);
        // Please use the other constructor if the LaunchPad is available.
        // ConversionApi capi = new ConversionApi(API_TOKEN, LAUNCH_PAD_URL);

        // (Optional) Enable logging
        capi.setDebugging(true);

        // build capi events
        CapiEvent e1 = new CapiEvent()
                .pixelId(PIXEL_ID)
                .eventConversionType("WEB")
                .eventType("CUSTOM_EVENT_1")
                .timestamp("1656022510346")
                // The following PII feilds are hashed by SHA256 before being sent to CAPI.
                // Alternatively, you can use hashed-field setters (e.g. hashEmail()) to set the hashed value directly.
                .email("usr1@gmail.com")
                .ipAddress("202.117.0.20")
                .phoneNumber("123-456-7890");

        CapiEvent e2 = new CapiEvent()
                .pixelId(PIXEL_ID)
                .eventConversionType("WEB")
                .eventType("CUSTOM_EVENT_2")
                .timestamp("1656022510346")
                .email("usr1@gmail.com")
                .ipAddress("202.117.0.20")
                .phoneNumber("123-456-7890");

        // Send an event asynchronously
        capi.sendEvent(e2);

        // Send batching events asynchronously (up to 2000 events per request)
        capi.sendEvents(Arrays.asList(e1, e2));
    }
}
