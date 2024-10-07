import java.time.LocalDateTime;

public class Bejegyzesek {
    private String szerzo;
    private String tartalom;
    private int likeok;
    private LocalDateTime letrejott;
    private LocalDateTime szerkesztve;

    public Bejegyzesek(String szerzo, String tartalom) {
        this.szerzo = szerzo;
        this.tartalom = tartalom;
        this.likeok = 0;
        this.letrejott = LocalDateTime.now();
        this.szerkesztve = null;
    }

    public String getSzerzo() {
        return szerzo;
    }

    public String getTartalom() {
        return tartalom;
    }

    public int getLikeok() {
        return likeok;
    }

    public LocalDateTime getLetrejott() {
        return letrejott;
    }

    public LocalDateTime getSzerkesztve() {
        return szerkesztve;
    }

    public void setTartalom(String tartalom) {
        this.tartalom = tartalom;
        this.szerkesztve = LocalDateTime.now();
    }

    public void like() {
        this.likeok++;
    }

    @Override
    public String toString() {
        String szerkesztes = (szerkesztve != null) ? "Szerkesztve: " + szerkesztve : "";
        return String.format("%s – %d – %s\n%s\n%s", szerzo, likeok, letrejott, szerkesztes, tartalom);
    }
}

