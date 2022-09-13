package com.snap.business.sdk.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.language.Soundex;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CapiHash {
    private static final Soundex soundexInstance = new Soundex();

    public static String sha256(String input) {
        return DigestUtils.sha256Hex(input);
    }

    public static String soundex(String input) {
        // thread-safe
        // https://commons.apache.org/proper/commons-codec/apidocs/org/apache/commons/codec/language/Soundex.html
        return soundexInstance.soundex(input);
    }

    public static String normAndHashStr(String s) {
        if (s == null || (s = s.trim()).isEmpty()) {
            return null;
        }
        return sha256(s.toLowerCase());
    }

    public static String normAndSoundexStr(String s) {
        if (s == null || (s = s.trim()).isEmpty()) {
            return null;
        }
        return soundex(s.toLowerCase());
    }

    public static String normAndHashPhoneNum(String phoneNum) {
        String normalized = normalizePhoneNum(phoneNum);
        if (normalized == null) {
            return null;
        }
        return sha256(normalized);
    }

    private static boolean isBlank(String input) {
        return input == null || input.trim().isEmpty();
    }

    private static String normalizePhoneNum(String phoneNum) {
        if (isBlank(phoneNum)) {
            return null;
        }
        final String ptnStr1 = "^((\\+|00)(\\d+)[\\-\\s])?0?(.+)";
        final String ptnStr2 = "[^\\d.]";

        Pattern r = Pattern.compile(ptnStr1);
        Matcher m = r.matcher(phoneNum);

        if (m.find()) {
            String countryCode = m.group(3);
            String num = m.group(4);

            if (countryCode != null) {
                countryCode = countryCode.replaceAll(ptnStr2, "");
            }

            if (num != null) {
                num = num.replaceAll(ptnStr2, "");
            }

            if (isBlank(countryCode)) {
                countryCode = "1";
            }

            if (!isBlank(num)) {
                return countryCode + num;
            }
        }
        return null;
    }
}
