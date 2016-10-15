/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psbd.projekt.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michal
 */
public class Utils {

    public static Date dataOd;
    public static Date dataDo;
    public static Map<Integer, String> mapStandard = new HashMap<Integer, String>();

    public static void initMap() {
        mapStandard.put(1, "Budżetowy");
        mapStandard.put(2, "Standard");
        mapStandard.put(3, "Lux");
        mapStandard.put(4, "Exclusive");
    }

    /**
     *
     * @param toConv
     * @return
     */
    static public String getMD5Sting(String toConv) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }

        byte[] passBytes = toConv.getBytes();
        byte[] passmd5 = md5.digest(passBytes);
        BigInteger bigInt = new BigInteger(1, passmd5);
        return bigInt.toString(16);
    }
}
