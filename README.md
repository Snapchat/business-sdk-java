# business-sdk-java


## Requirements

Building the API client library requires:
1. Java 1.8+
2. Maven (3.8.3+)/Gradle (7.2+)

## Installation

The CAPI Business SDK will be available on maven central. (For pilot users, please contact us for the pre-build jar before the official release). Please add the code snippet below to import the dependencies.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>com.snap.business.sdk</groupId>
  <artifactId>capi-java</artifactId>
  <version>1.1.9</version>
  <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
  repositories {
    mavenCentral()     // Needed if the 'capi-java' jar has been published to maven central.
    mavenLocal()       // Needed if the 'capi-java' jar has been published to the local maven repo.
  }

  dependencies {
     implementation "com.snap.business.sdk:capi-java:1.1.9"
  }
```

## Getting Started

Please use the code example below as a template on sending your conversion events to Snap Conversion API. More conversion parameters are expected to be provided in practice.

```java

// Import classes:
import com.snap.business.sdk.api.ConversionApi;
import com.snap.business.sdk.model.CapiEvent;

import java.util.Arrays;

public class SendEvents {
    private final static String API_TOKEN = "YOUR_API_TOKEN";
    private final static String PIXEL_ID = "YOUR_PIXEL_ID";
    private final static String LAUNCH_PAD_URL = "YOUR_LAUNCH_PAD_URL";

    public static void main(String[] args) {
        ConversionApi capi = new ConversionApi(API_TOKEN);
        // Please use the constructor below if the LaunchPad is available.
        // ConversionApi capi = new ConversionApi(API_TOKEN, LAUNCH_PAD_URL);

        // (Optional) Enable logging
        capi.setDebugging(true);

        // Use case 1: Send a single event
// build capi events
        CapiEvent e1 = new CapiEvent()
                .pixelId(PIXEL_ID)
                .eventConversionType(CapiEvent.EventConversionTypeEnum.WEB)
                .eventType(CapiEvent.EventTypeEnum.CUSTOM_EVENT_1)
                .timestamp("1662655468")
                .uuidC1("mocking-cookie1")
                // The following PII fields are hashed by SHA256 before being sent to CAPI.
                .email("usr1@gmail.com")
                .ipAddress("202.117.0.20")
                .phoneNumber("123-456-7890");

        // Send an event asynchronously
        capi.sendEvent(e1);

        // Use case 2: Send batching events (up to 2000 events per request)
        CapiEvent e2 = new CapiEvent()
                .pixelId(PIXEL_ID)
                .eventConversionType(CapiEvent.EventConversionTypeEnum.WEB)
                .eventType(CapiEvent.EventTypeEnum.CUSTOM_EVENT_2)
                .timestamp("1662655468")
                .email("usr1@gmail.com")
                .ipAddress("202.117.0.20")
                .phoneNumber("123-456-7890");

        // Send batching events asynchronously
        capi.sendEvents(Arrays.asList(e1, e2));

      // Use case 3: Send test events
      TestResponse res1 = capi.sendTestEvent(e2);
      System.out.println(res1);

      TestResponse res2 = capi.sendTestEvents(Arrays.asList(e1, e2));
      System.out.println(res2);

      // Get the logs on test events in last 24hrs
      ResponseLogs res3 = capi.getTestEventLogs(PIXEL_ID);
      System.out.println(res3);

      // Get the stats on test events in past hour
      ResponseStats res4 = capi.getTestEventStats(PIXEL_ID);
      System.out.println(res4);

    }
}



```

### Initiate ConversionApi
- Please use ConversionApi(String longLivedToken, String launchPadUrl) if the Launch Pad has been set up under your domain. Conversion events will be forwarded to Snap transparently. (Other MPC features will be introduced in later versions).
- Otherwise, you can initiate the instance using ConversionApi(String longLivedToken).Conversion events are sent back to Snap from the business SDK directly.
- It’s recommended to create a dedicated instance per thread to avoid any potential issues.

### API Token
- To use the Conversions API, you need to use the access token for auth. See here to generate the token.

### Build CapiEvent
- Please check with the section [Conversion Parameters](https://marketingapi.snapchat.com/docs/conversion.html#additional-data-formatting-guidelines) and provide as much information as possible when creating the CapiEvent object.
- Every CAPI attribute has a corresponding setter in the CapiEvent class following the camelcase naming convention.
- At least one of the following parameters is required in order to successfully send events via the Conversions API. When possible, we recommend passing all of the below parameters to improve performance:

  - hashed_email
  - hashed_phone
  - hashed_ip and user_agent
  - hashed_mobile_ad_id

- Any setter starting with the prefix of “hashed” (e.g. hashedEmail()) accepts the hashing PII value only (see [Data Hygiene](https://marketingapi.snapchat.com/docs/conversion.html#data-hygiene)). Please use the unhashed setter (e.g. email()) if you want the business sdk to normalize and hash the PII field on your behalf.
- We highly recommend passing cookie1 uuid_c1, if available, as this will increase your match rate. You can access a 1st party cookie by looking at the _scid value under your domain if you are using the Pixel SDK.

### Send event(s) asynchronously
- Conversion events can be sent individually via sendEvent(CapiEvent capiEvent).
- Conversion events can be reported in batch using sendEvents(List<CapiEvent> capiEvents) if they are buffered in your application.
- Events are encapsulated in an asynchronous request in both solutions by which your application won’t be blocked. The response is logged by a default callback (under debugging mode)
- Please use sendEvents(List<CapiEvent> capiEvents, ApiCallback<Response> callback)if a custom callback is preferred.
- We recommend a 1000 QPS limit for sending us requests. You may send up to 2000 events per batch request, and can thus send up to 2M events/sec. Sending more than 2000 events per batch will result in a 400 error.

### Test Events, Logs, and Stats
Snap’s Conversion API provides the validate, log, and stats endpoints for advertisers to test their events and obtain more information on how each of the test event was processed.
- Conversion events can be sent for testing and validation via the sendTestEvent(event).
- Conversion API also provides a logging endpoint. It returns a summary of test CAPI events sent to the test endpoint within the past day.
- Conversion API’s stats endpoint provides basic stats and summary of the test events sent.
- Please check example/SendTestEvents.java for more information.

### Debugging Mode
- When debugging mode is turned on, we will log the events, api call response and exceptions using [SLF4J](https://www.slf4j.org/manual.html) logger.
  - By default, SLF4J logger will bind to [java.util.logging](https://www.slf4j.org/api/org/slf4j/jul/JDK14LoggerAdapter.html) (JUL) and log to console.
  - SLF4J logger can detect the logging framework from your class path and bind a logging framework at deployment time. It can support various logging frameworks (e.g. log4j, reload4j, JUL, logback, etc).

### Customize the http client
- When you create an instance of ConversionApi, it utilizes a default OkHttpClient by default. However, you can choose to provide a custom client by passing it as an argument to the constructor. For more details, please refer to the example in SendEventsSync.

Please open a GitHub issue if you want to record a bug report, enhancement proposal, or give any other product feedback.

