package com.snap.business.sdk.api;

import com.snap.business.sdk.ApiCallback;
import com.snap.business.sdk.ApiClient;
import com.snap.business.sdk.ApiException;
import com.snap.business.sdk.model.CapiEvent;
import com.snap.business.sdk.model.Response;
import com.snap.business.sdk.model.ResponseLogs;
import com.snap.business.sdk.model.ResponseStats;
import com.snap.business.sdk.model.TestResponse;
import com.snap.business.sdk.util.CapiConstants;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.logging.Level;

public class ConversionApi {
    private static final Logger logger = LoggerFactory.getLogger(ConversionApi.class);
    private ApiClient client = new ApiClient();
    private DefaultApi capi = new DefaultApi(client);
    private boolean isLaunchPadEnabled = false;
    private boolean isDebugEnabled = false;

    public ConversionApi(String longLivedToken) {
        this(longLivedToken, "");
    }

    public ConversionApi(String longLivedToken, String launchPadUrl) {
        client.setBearerToken(longLivedToken);
        isLaunchPadEnabled = !(launchPadUrl == null || launchPadUrl.trim().isEmpty());
        client.addDefaultHeader(CapiConstants.SDK_VER_HEADER,
                String.format("%s/%s", CapiConstants.SDK_LANGUAGE, CapiConstants.SDK_VERSION));
        client.addDefaultHeader("accept-encoding", "");
        if (isLaunchPadEnabled) {
            client.setUserAgent(CapiConstants.USER_AGENT_WITH_PAD)
                    .setBasePath(launchPadUrl.trim());
        }
        else {
            client.setUserAgent(CapiConstants.USER_AGENT);
        }
    }

    public TestResponse sendTestEvent(CapiEvent capiEvent) {
        return sendTestEvents(Arrays.asList(capiEvent));
    }

    public TestResponse sendTestEvents(List<CapiEvent> capiEvents) {
        TestResponse result;
        try {
            for (CapiEvent capiEvent : capiEvents) {
                // Hardcode the integration field in conversion events
                capiEvent.integration(CapiConstants.INTEGRATION_SDK);
                logEvent(Level.INFO, capiEvent.toString());
            }
            result = capi.sendTestData(capiEvents);
            logEvent("SUCCESS".equals(result.getStatus())? Level.INFO : Level.WARNING, result.toString());
        } catch (ApiException e) {
            result = castToTestResponse(e);
            logEvent(Level.SEVERE, result.toString());
        } catch (Exception e) {
            result = new TestResponse().status("FAILED").reason(e.getMessage());
            logEvent(Level.SEVERE, ExceptionUtils.getStackTrace(e));
        }
        return result;
    }

    public ResponseLogs getTestEventLogs(String assetId) {
        ResponseLogs result;
        try {
            result = capi.conversionValidateLogs(assetId);
            logEvent("SUCCESS".equals(result.getStatus())? Level.INFO : Level.WARNING, result.toString());
        } catch (Exception e) {
            result = new ResponseLogs().status("FAILED").reason(e.getMessage());
            logEvent(Level.SEVERE, ExceptionUtils.getStackTrace(e));
        }
        return result;
    }

    public ResponseStats getTestEventStats(String assetId) {
        ResponseStats result;
        try {
            result = capi.conversionValidateStats(assetId);
            logEvent(Level.INFO, result.toString());
        } catch (Exception e) {
            result = new ResponseStats().status("FAILED").reason(e.getMessage());
            logEvent(Level.SEVERE, ExceptionUtils.getStackTrace(e));
        }
        return result;
    }

    public void setDebugging(boolean isEnabled) {
        this.isDebugEnabled = isEnabled;
        logger.info("[Snap Business SDK] Debug mode is enabled");
    }

    public Logger getLogger() {
        return logger;
    }

    public Response sendEventSync(CapiEvent capiEvent) {
        return sendEventsSync(Arrays.asList(capiEvent));
    }

    public Response sendEventsSync(List<CapiEvent> capiEvents) {
        Response result;
        try {
            for (CapiEvent capiEvent : capiEvents) {
                // Hardcode the integration field in conversion events
                capiEvent.integration(CapiConstants.INTEGRATION_SDK);
                logEvent(Level.INFO, capiEvent.toString());
            }
            result = capi.sendData(capiEvents);
            logEvent("SUCCESS".equals(result.getStatus())? Level.INFO : Level.WARNING, result.toString());
        } catch (ApiException e) {
            result = castToResponse(e);
            logEvent(Level.SEVERE, result.toString());
        } catch (Exception e) {
            result = new Response().status("FAILED").reason(e.getMessage());
            logEvent(Level.SEVERE, ExceptionUtils.getStackTrace(e));
        }

        return result;
    }

    public Response castToResponse(ApiException e) {
        Response result;
        // Parse from response body if possible
        try {
            result = Response.fromJson(e.getResponseBody());
        } catch (Exception ex) {
            result = new Response().status("FAILED").reason(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }

    public TestResponse castToTestResponse(ApiException e) {
        TestResponse result;
        // Parse from response body if possible
        try {
            result = TestResponse.fromJson(e.getResponseBody());
        } catch (Exception ex) {
            result = new TestResponse().status("FAILED").reason(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }

    public okhttp3.Call sendEvent(CapiEvent capiEvent) {
        return sendEvents(Arrays.asList(capiEvent));
    }

    public okhttp3.Call sendEvents(List<CapiEvent> capiEvents) {
        return sendEvents(capiEvents, new ApiCallback<Response>() {

            @Override
            public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                logEvent(Level.SEVERE, castToResponse(e).toString());
            }

            @Override
            public void onSuccess(Response result, int statusCode, Map<String, List<String>> responseHeaders) {
                logEvent("SUCCESS".equals(result.getStatus())? Level.INFO : Level.WARNING, result.toString());
            }

            @Override
            public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

            }

            @Override
            public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

            }
        });
    }

    public okhttp3.Call sendEvents(List<CapiEvent> capiEvents, ApiCallback<Response> callback) {
        try {
            for (CapiEvent capiEvent : capiEvents) {
                // Hardcode the integration field in conversion events
                capiEvent.integration(CapiConstants.INTEGRATION_SDK);
                logEvent(Level.INFO, capiEvent.toString());
            }
            return capi.sendDataAsync(capiEvents, callback);
        } catch (Exception e) {
            logEvent(Level.SEVERE, ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    private void logEvent(Level level, String logMessage) {
        // if debug mode is not enabled
        if (!isDebugEnabled) {
            return;
        }

        String msg = "[Snap Business SDK] " + logMessage;
        if (level.equals(Level.INFO)) {
            logger.info(msg);
        } else if (level.equals(Level.WARNING)) {
            logger.warn(msg);
        } else if (level.equals(Level.SEVERE)) {
            logger.error(msg);
        } else {
            logger.debug(msg);
        }
    }
}
