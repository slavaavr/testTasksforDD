package ava;

import javafx.util.Pair;
import java.util.List;

/**
 * Entity - класс, предназначенный для хранения пути до zip архива, пути до xml файла, а также для хранения номеров строк и url путей.
 */
public class Entity {
    private String wayToZip;
    private String wayToXML;
    private List<Pair<Integer, String>> lineNumberAndUrl; // Коллекция пар: номера строки и url, расположенного на этой строке.

    public Entity(String wayToZip, String wayToXML, List<Pair<Integer, String>> lineNumberAndUrl) {
        this.wayToZip = wayToZip;
        this.wayToXML = wayToXML;
        this.lineNumberAndUrl = lineNumberAndUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Entity {wayToZip=");
        sb.append(wayToZip);
        sb.append(", wayToXML=");
        sb.append(wayToXML);
        sb.append(", lineNumberAndUrl=[");
        for (int i = 0; i < lineNumberAndUrl.size(); i++) {
            sb.append("line=");
            sb.append(lineNumberAndUrl.get(i).getKey());
            sb.append(" url=");
            sb.append(lineNumberAndUrl.get(i).getValue());
            if(i!=lineNumberAndUrl.size()-1)
                sb.append(", ");
        }
        sb.append("]}");
        return sb.toString();
    }
}
