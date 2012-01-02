package utils;

import net.zemberek.erisim.Zemberek;
import net.zemberek.tr.yapi.TurkiyeTurkcesi;
import play.Logger;

public class Utils {

    private static final Zemberek ZEMBEREK = new Zemberek(new TurkiyeTurkcesi());

    private Utils() {

    }

    public static String temizle(final String k) {

        return k.replaceAll("[^0-9a-zA-ZğşıçüöĞÖÇŞÜİ]", " ").replaceAll("\\s+", " ");
    }

    public static String kokBul(final String kelime) {

        String[] kokler = ZEMBEREK.kokBulucu().stringKokBul(kelime);
        if (kokler == null || kokler.length < 1) {
            Logger.debug("%1$s için kök bulunamadı", kelime);
            return kelime;
        }
        return ZEMBEREK.dilBilgisi().alfabe().ayikla(kokler[0]);
    }

}
