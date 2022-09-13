package com.snap.business.sdk.model;

import com.snap.business.sdk.util.CapiHash;

public interface CapiEventExt<T extends CapiEventExt> {
    public void setHashedEmail(String hashedEmail);
    public void setHashedMobileAdId(String hashedMobileAdId);
    public void setHashedIdfv(String hashedIdfv);
    public void setHashedPhoneNumber(String hashedPhoneNumber);
    public void setHashedIpAddress(String hashedIpAddress);
    public void setHashedFirstNameSha(String hashedFirstNameSha);
    public void setHashedFirstNameSdx(String hashedFirstNameSdx);
    public void setHashedMiddleNameSha(String hashedMiddleNameSha);
    public void setHashedMiddleNameSdx(String hashedMiddleNameSdx);
    public void setHashedLastNameSha(String hashedLastNameSha);
    public void setHashedLastNameSdx(String hashedLastNameSdx);
    public void setHashedCitySha(String hashedCitySha);
    public void setHashedCitySdx(String hashedCitySdx);
    public void setHashedStateSha(String hashedStateSha);
    public void setHashedStateSdx(String hashedStateSdx);
    public void setHashedZip(String hashedZip);
    public void setHashedDobMonth(String hashedDobMonth);
    public void setHashedDobDay(String hashedDobDay);

    default T email(String unhashedEmail) {
        setHashedEmail(CapiHash.normAndHashStr(unhashedEmail));
        return (T) this;
    }

    default T mobileAdId(String unhashedMobileAdId) {
        setHashedMobileAdId(CapiHash.normAndHashStr(unhashedMobileAdId));
        return (T) this;
    }

    default T idfv(String unhashedIdfv) {
        setHashedIdfv(CapiHash.normAndHashStr(unhashedIdfv));
        return (T) this;
    }

    default T phoneNumber(String unhashedPhoneNumber) {
        setHashedPhoneNumber(CapiHash.normAndHashPhoneNum(unhashedPhoneNumber));
        return (T) this;
    }

    default T ipAddress(String unhashedIpAddress) {
        setHashedIpAddress(CapiHash.normAndHashStr(unhashedIpAddress));
        return (T) this;
    }

    default T firstName(String unhashedFirstName) {
        setHashedFirstNameSha(CapiHash.normAndHashStr(unhashedFirstName));
        setHashedFirstNameSdx(CapiHash.normAndSoundexStr(unhashedFirstName));
        return (T) this;
    }

    default T middleName(String unhashedMiddleName) {
        setHashedMiddleNameSha(CapiHash.normAndHashStr(unhashedMiddleName));
        setHashedMiddleNameSdx(CapiHash.normAndSoundexStr(unhashedMiddleName));
        return (T) this;
    }

    default T lastName(String unhashedLastName) {
        setHashedLastNameSha(CapiHash.normAndHashStr(unhashedLastName));
        setHashedLastNameSdx(CapiHash.normAndSoundexStr(unhashedLastName));
        return (T) this;
    }

    default T city(String unhashedCity) {
        setHashedCitySha(CapiHash.normAndHashStr(unhashedCity));
        setHashedCitySdx(CapiHash.normAndSoundexStr(unhashedCity));
        return (T) this;
    }

    default T state(String unhashedState) {
        setHashedStateSha(CapiHash.normAndHashStr(unhashedState));
        setHashedStateSdx(CapiHash.normAndSoundexStr(unhashedState));
        return (T) this;
    }

    default T zip(String unhashedZip) {
        setHashedZip(CapiHash.normAndHashStr(unhashedZip));
        return (T) this;
    }

    default T dob(String unhashedDobStr) {
        // TODO Parse and hash DD and MM after the format of unhashedDobStr is finalized
        return (T) this;
    }
}
