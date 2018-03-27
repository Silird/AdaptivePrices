package ru.SilirdCo.AdaptivePrices.Util;

import java.text.SimpleDateFormat;

public class Structure {
    public boolean admin = false;

    public final static SimpleDateFormat formatTimeMillisecondTZ = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss.SSSZ");
    public final static SimpleDateFormat formatDateTime = new SimpleDateFormat("yyyy.MM.dd_HH-mm-ss");
}
