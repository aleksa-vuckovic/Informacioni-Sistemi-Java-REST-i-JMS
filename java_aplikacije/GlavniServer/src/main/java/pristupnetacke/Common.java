/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pristupnetacke;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 *
 * @author Aleksa
 */
public class Common {
    public static String delim = "#";
    public static Response ishod(TextMessage res) throws JMSException {
        String[] odgovor = res.getText().split(delim);
        if (odgovor[0].equals("USPEH"))
            return Response.status(Response.Status.OK).entity("YASS").build();
        else
            return Response.status(Response.Status.BAD_REQUEST).entity(odgovor[1]).build();
    }
    public static String decode(String data) {
        return new String(Base64.getDecoder().decode(data), StandardCharsets.UTF_8);
    }
    public static String getKime(HttpHeaders headers) {
        List<String> list = headers.getRequestHeader("Authorization");
        String info = list.get(0).replace("Basic ", "");
        info = decode(info);
        return info.split(":")[0];
    }
}
