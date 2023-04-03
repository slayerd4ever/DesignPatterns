package com.slayerd.utils.impl;

import com.google.common.annotations.VisibleForTesting;
import com.slayerd.exceptions.IdGenerateFailureException;
import com.slayerd.utils.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * (what) generate a random id
 * <p>
 * (why) we need a duplicate id to find the log in trace_log file
 * <p>
 * (how) through the method generate()
 *
 * @author slayerd
 * @version 1.0
 * @since 2023-04-02
 */
public class LogTraceIdGeneratorImpl implements IdGenerator {
    private static final Logger logger = LoggerFactory.getLogger(LogTraceIdGeneratorImpl.class);

    /**
     * generate a randomId
     *
     * @return randomId
     */
    @Override
    public String generate() throws IdGenerateFailureException {
        String subStrOfHostName = null;
        try {
            subStrOfHostName = lastFieldOfHostName();
        } catch (UnknownHostException e) {
            throw new IdGenerateFailureException("hostname is empty");
        }
        long currentTimeMillis = System.currentTimeMillis();
        String randomStr = generateRandomAlphameric(8);
        String randomId = String.format("%s-%d-%s", subStrOfHostName, currentTimeMillis, randomStr);
        return randomId;
    }

    /**
     * generate a randomStr
     *
     * @param length length of randomStr,length > 0
     * @return randomStr
     */
    @VisibleForTesting
    protected String generateRandomAlphameric(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("argument is illegal");
        }
        char[] chars = new char[length];
        int count = 0;
        int maxAscii = 'z';
        Random random = new Random();
        while (count < length) {
            int randomAscii = random.nextInt(maxAscii);
            boolean isDigit = randomAscii >= '0' && randomAscii <= '9';
            boolean isUppercase = randomAscii >= 'A' && randomAscii <= 'Z';
            boolean isLowercase = randomAscii >= 'a' && randomAscii <= 'z';
            if (isDigit || isUppercase || isLowercase) {
                chars[count] = (char) randomAscii;
                count++;
            }
        }
        return String.valueOf(chars);
    }

    /**
     * get the last field of hostname
     *
     * @return last file of hostname in String
     */
    private String lastFieldOfHostName() throws UnknownHostException {
        String hostName = InetAddress.getLocalHost().getHostName();
        return getLastFieldOfHostNameByDot(hostName);
    }

    /**
     * get last field of hostname by dot
     *
     * @param hostName hostname
     * @return last file of hostname in String
     */
    @VisibleForTesting
    protected String getLastFieldOfHostNameByDot(String hostName) {
        if (hostName == null || hostName.isEmpty()) {
            throw new IllegalArgumentException("hostname is empty");
        }
        String[] split = hostName.split("\\.");
        return split[split.length - 1];
    }
}
