package festival.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Artist implements Serializable, Comparable<Artist>,Entity<Integer>  {
    //numele,  data  și  locul  spectacolului,  numărul  de  locuri  disponibile  și  numărul  de  locuri  deja  vândute
    private String numeArtist;
    private String locatie;
    private int locuriDisponibile;
    private int locuriVandute;
    private String data;
    private String ora;
    private int id;

    public Artist(){}
    public Artist(String numeArtist, String locatie, int locuriDisponibile, int locuriVandute, String data, String ora) {
        this.numeArtist = numeArtist;
        this.locatie = locatie;
        this.locuriDisponibile = locuriDisponibile;
        this.locuriVandute = locuriVandute;
        this.data=data;
        this.ora=ora;
    }

    public String getNumeArtist() {
        return numeArtist;
    }

    public void setNumeArtist(String numeArtist) {
        this.numeArtist = numeArtist;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public int getLocuriDisponibile() {
        return locuriDisponibile;
    }

    public void setLocuriDisponibile(int locuriDisponibile) {
        this.locuriDisponibile = locuriDisponibile;
    }

    public int getLocuriVandute() {
        return locuriVandute;
    }

    public void setLocuriVandute(int locuriVandute) {
        this.locuriVandute = locuriVandute;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(getId(), artist.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeArtist, locatie, locuriDisponibile, locuriVandute);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "numeArtist='" + numeArtist +
                ", locatie='" + locatie +
                ", locuriDisponibile=" + locuriDisponibile +
                ", locuriVandute=" + locuriVandute +
                ", dataSpectacol="+data+
                ", oraSpectacol="+ora+
                '}';
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    @Override
    public int compareTo(Artist artist) {
        return numeArtist.compareTo(artist.numeArtist);
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer integer) {
        this.id=integer;
    }
}
