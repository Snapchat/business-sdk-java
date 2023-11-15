package com.snap.business.sdk.util;

public class CapiConstants {
    public final static String SDK_LANGUAGE = "java";
    public final static String SDK_VERSION = "1.1.8";
    public final static String API_VERSION = "v2";
    public final static String PROD_URL = "https://tr.snapchat.com/" + API_VERSION;
    public final static String STAGING_URL = "https://tr-shadow.snapchat.com/" + API_VERSION;
    public final static String USER_AGENT = String.format("BusinessSDK/Java/%s", SDK_VERSION);
    public final static String USER_AGENT_WITH_PAD = USER_AGENT + " (LaunchPAD)";
    public final static String SDK_VER_HEADER = "X-CAPI-BusinessSDK";
    public final static String INTEGRATION_SDK = "business-sdk";
}
