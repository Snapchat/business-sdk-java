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
import com.snap.business.sdk.model.ResponseErrorRecords;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Response
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class Response {
  public static final String SERIALIZED_NAME_STATUS = "status";
  @SerializedName(SERIALIZED_NAME_STATUS)
  private String status;

  public static final String SERIALIZED_NAME_REASON = "reason";
  @SerializedName(SERIALIZED_NAME_REASON)
  private String reason;

  public static final String SERIALIZED_NAME_ERROR_RECORDS = "error_records";
  @SerializedName(SERIALIZED_NAME_ERROR_RECORDS)
  private List<ResponseErrorRecords> errorRecords = null;

  public Response() { 
  }

  public Response status(String status) {
    
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getStatus() {
    return status;
  }


  public void setStatus(String status) {
    this.status = status;
  }


  public Response reason(String reason) {
    
    this.reason = reason;
    return this;
  }

   /**
   * Get reason
   * @return reason
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getReason() {
    return reason;
  }


  public void setReason(String reason) {
    this.reason = reason;
  }


  public Response errorRecords(List<ResponseErrorRecords> errorRecords) {
    
    this.errorRecords = errorRecords;
    return this;
  }

  public Response addErrorRecordsItem(ResponseErrorRecords errorRecordsItem) {
    if (this.errorRecords == null) {
      this.errorRecords = new ArrayList<>();
    }
    this.errorRecords.add(errorRecordsItem);
    return this;
  }

   /**
   * Get errorRecords
   * @return errorRecords
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<ResponseErrorRecords> getErrorRecords() {
    return errorRecords;
  }


  public void setErrorRecords(List<ResponseErrorRecords> errorRecords) {
    this.errorRecords = errorRecords;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Response response = (Response) o;
    return Objects.equals(this.status, response.status) &&
        Objects.equals(this.reason, response.reason) &&
        Objects.equals(this.errorRecords, response.errorRecords);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, reason, errorRecords);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Response {\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
    sb.append("    errorRecords: ").append(toIndentedString(errorRecords)).append("\n");
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
    openapiFields.add("status");
    openapiFields.add("reason");
    openapiFields.add("error_records");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Object and throws an exception if issues found
  *
  * @param jsonObj JSON Object
  * @throws IOException if the JSON Object is invalid with respect to Response
  */
  public static void validateJsonObject(JsonObject jsonObj) throws IOException {
      if (jsonObj == null) {
        if (Response.openapiRequiredFields.isEmpty()) {
          return;
        } else { // has required fields
          throw new IllegalArgumentException(String.format("The required field(s) %s in Response is not found in the empty JSON string", Response.openapiRequiredFields.toString()));
        }
      }

      Set<Entry<String, JsonElement>> entries = jsonObj.entrySet();
      // check to see if the JSON string contains additional fields
      for (Entry<String, JsonElement> entry : entries) {
        if (!Response.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `Response` properties. JSON: %s", entry.getKey(), jsonObj.toString()));
        }
      }
      if (jsonObj.get("status") != null && !jsonObj.get("status").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `status` to be a primitive type in the JSON string but got `%s`", jsonObj.get("status").toString()));
      }
      if (jsonObj.get("reason") != null && !jsonObj.get("reason").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `reason` to be a primitive type in the JSON string but got `%s`", jsonObj.get("reason").toString()));
      }
      JsonArray jsonArrayerrorRecords = jsonObj.getAsJsonArray("error_records");
      if (jsonArrayerrorRecords != null) {
        // ensure the json data is an array
        if (!jsonObj.get("error_records").isJsonArray()) {
          throw new IllegalArgumentException(String.format("Expected the field `error_records` to be an array in the JSON string but got `%s`", jsonObj.get("error_records").toString()));
        }

        // validate the optional field `error_records` (array)
        for (int i = 0; i < jsonArrayerrorRecords.size(); i++) {
          ResponseErrorRecords.validateJsonObject(jsonArrayerrorRecords.get(i).getAsJsonObject());
        };
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!Response.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'Response' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<Response> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(Response.class));

       return (TypeAdapter<T>) new TypeAdapter<Response>() {
           @Override
           public void write(JsonWriter out, Response value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public Response read(JsonReader in) throws IOException {
             JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
             validateJsonObject(jsonObj);
             return thisAdapter.fromJsonTree(jsonObj);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of Response given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of Response
  * @throws IOException if the JSON string is invalid with respect to Response
  */
  public static Response fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, Response.class);
  }

 /**
  * Convert an instance of Response to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

