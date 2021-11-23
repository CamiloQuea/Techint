/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Camilo
 */
public class http {

    public static JSONObject getBody(HttpServletRequest request) throws IOException, ParseException,Error {
        try {

            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = request.getReader();

            String line;

            int cont = 0;

            while ((line = reader.readLine()) != null) {

                cont += 1;
                buffer.append(line);
                buffer.append(System.lineSeparator());

            }

            if (cont == 0) {
                return null;
            }

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(buffer.toString());

            return json;

        } catch (ParseException e) {
            throw new Error(e);
        }
    }
}
