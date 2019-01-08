import java.util.Objects;

public class Pruefung
{
    private Modul Modul;
    private int PruefungNr;
    private int Laenge;
    private int Wichtung;
    private boolean Schein;

    public Pruefung(Modul modul, int pruefungNr, int laenge, int wichtung, boolean schein)
    {
        Modul = modul;
        PruefungNr = pruefungNr;
        Laenge = laenge;
        Wichtung = wichtung;
        Schein = schein;
    }

    public Modul getModul()
    {
        return Modul;
    }

    public int getPruefungNr()
    {
        return PruefungNr;
    }

    public int getLaenge()
    {
        return Laenge;
    }

    public int getWichtung()
    {
        return Wichtung;
    }

    public boolean hasSchein()
    {
        return Schein;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pruefung pruefung = (Pruefung) o;
        return PruefungNr == pruefung.PruefungNr &&
                Laenge == pruefung.Laenge &&
                Wichtung == pruefung.Wichtung &&
                Schein == pruefung.Schein &&
                Modul.getId().equals(pruefung.Modul.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(Modul.getId(), PruefungNr, Laenge, Wichtung, Schein);
    }

    @Override
    public String toString()
    {
        return "Pruefung{" +
                "Modul=" + Modul.getId() + ", " + Modul.getName() +
                ", PruefungNr=" + PruefungNr +
                ", Laenge=" + Laenge +
                ", Wichtung=" + Wichtung +
                ", Schein=" + Schein +
                '}';
    }
}
