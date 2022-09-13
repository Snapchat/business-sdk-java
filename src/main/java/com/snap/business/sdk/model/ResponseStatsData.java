/*
 * Snap Conversions API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.snap.business.sdk.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.snap.business.sdk.model.ResponseStatsTest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.snap.business.sdk.JSON;

/**
 * ResponseStatsData
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class ResponseStatsData {
  public static final String SERIALIZED_NAME_TEST = "test";
  @SerializedName(SERIALIZED_NAME_TEST)
  private ResponseStatsTest test;

  public static final String SERIALIZED_NAME_LIVE = "live";
  @SerializedName(SERIALIZED_NAME_LIVE)
  private ResponseStatsTest live;

  public ResponseStatsData() { 
  }

  public ResponseStatsData test(ResponseStatsTest test) {
    
    this.test = test;
    return this;
  }

   /**
   * Get test
   * @return test
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public ResponseStatsTest getTest() {
    return test;
  }


  public void setTest(ResponseStatsTest test) {
    this.test = test;
  }


  public ResponseStatsData live(ResponseStatsTest live) {
    
    this.live = live;
    return this;
  }

   /**
   * Get live
   * @return live
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public ResponseStatsTest getLive() {
    return live;
  }


  public void setLive(ResponseStatsTest live) {
    this.live = live;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseStatsData responseStatsData = (ResponseStatsData) o;
    return Objects.equals(this.test, responseStatsData.test) &&
        Objects.equals(this.live, responseStatsData.live);
  }

  @Override
  public int hashCode() {
    return Objects.hash(test, live);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseStatsData {\n");
    sb.append("    test: ").append(toIndentedString(test)).append("\n");
    sb.append("    live: ").append(toIndentedString(live)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("test");
    openapiFields.add("live");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Object and throws an exception if issues found
  *
  * @param jsonObj JSON Object
  * @throws IOException if the JSON Object is invalid with respect to ResponseStatsData
  */
  public static void validateJsonObject(JsonObject jsonObj) throws IOException {
      if (jsonObj == null) {
        if (ResponseStatsData.openapiRequiredFields.isEmpty()) {
          return;
        } else { // has required fields
          throw new IllegalArgumentException(String.format("The required field(s) %s in ResponseStatsData is not found in the empty JSON string", ResponseStatsData.openapiRequiredFields.toString()));
        }
      }

      Set<Entry<String, JsonElement>> entries = jsonObj.entrySet();
      // check to see if the JSON string contains additional fields
      for (Entry<String, JsonElement> entry : entries) {
        if (!ResponseStatsData.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `ResponseStatsData` properties. JSON: %s", entry.getKey(), jsonObj.toString()));
        }
      }
      // validate the optional field `test`
      if (jsonObj.getAsJsonObject("test") != null) {
        ResponseStatsTest.validateJsonObject(jsonObj.getAsJsonObject("test"));
      }
      // validate the optional field `live`
      if (jsonObj.getAsJsonObject("live") != null) {
        ResponseStatsTest.validateJsonObject(jsonObj.getAsJsonObject("live"));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ResponseStatsData.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ResponseStatsData' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ResponseStatsData> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ResponseStatsData.class));

       return (TypeAdapter<T>) new TypeAdapter<ResponseStatsData>() {
           @Override
           public void write(JsonWriter out, ResponseStatsData value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ResponseStatsData read(JsonReader in) throws IOException {
             JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
             validateJsonObject(jsonObj);
             return thisAdapter.fromJsonTree(jsonObj);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of ResponseStatsData given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ResponseStatsData
  * @throws IOException if the JSON string is invalid with respect to ResponseStatsData
  */
  public static ResponseStatsData fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ResponseStatsData.class);
  }

 /**
  * Convert an instance of ResponseStatsData to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}
