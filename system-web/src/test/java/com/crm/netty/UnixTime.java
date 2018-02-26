package com.crm.netty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jason_moo on 2018/1/29.
 */
public class UnixTime implements Serializable{

    private final long value;

    public UnixTime() {
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }

    public UnixTime(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }

    @Override
    public String toString() {
        return new Date((value() - 2208988800L) * 1000L).toString();
    }
}
