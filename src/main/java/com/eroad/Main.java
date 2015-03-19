package com.eroad;

import com.eroad.service.LocationService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Sidney on 15-03-09.
 */
public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException, ParseException {
        LocationService ls = new LocationService();

        URL url = Main.class.getClass().getResource("/input.csv");
        PrintWriter writer = new PrintWriter("output.csv", "UTF-8");

        File csvData = new File(url.toURI());
        CSVParser parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.RFC4180);
        for (CSVRecord csvRecord : parser) {
            Map<String, Object> readUtf = ls.readUtf(csvRecord.get(1), csvRecord.get(2));

            String timeZoneId = null;
            String localizedTime = null;

            timeZoneId = (String) readUtf.get("timeZoneId");
            if(timeZoneId != null){
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dtLocalizedTime = df.parse(csvRecord.get(0));
                df.setTimeZone(TimeZone.getTimeZone(timeZoneId));

                localizedTime = df.format(dtLocalizedTime);
            }
            writer.println(csvRecord.get(0) + "," + csvRecord.get(1) + "," + csvRecord.get(2) + "," + timeZoneId + "," + localizedTime);
        }

        writer.close();
    }
}
