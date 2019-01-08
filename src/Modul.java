import java.util.*;
import java.util.stream.Collectors;

public class Modul
{
    private String Id;
    private String Name;
    private int SWSVorlesung;
    private int SWSUebung;
    private int SWSPraktikum;
    private Set<Modul> Voraussetzungen;
    private Set<Pruefung> Pruefungen;

    public Modul(String id, String name, int SWSVorlesung, int SWSUebung, int SWSPraktikum, Set<Modul> voraussetzungen, Set<Pruefung> pruefungen)
    {
        Id = id;
        Name = name;
        this.SWSVorlesung = SWSVorlesung;
        this.SWSUebung = SWSUebung;
        this.SWSPraktikum = SWSPraktikum;
        Voraussetzungen = voraussetzungen;
        Pruefungen = pruefungen;
    }

    public boolean bestanden(Set<Versuch> versuche)
    {
        return Note(versuche) <= 4;
    }

    public float Note(Set<Versuch> versuche)
    {
        List<Float> noten = new LinkedList<>();
        Versuch letzterVersuch;
        for (Pruefung pruefung : Pruefungen)
        {
            letzterVersuch = Versuch.letzterVersuch(versuche, pruefung);
            float teilNote = 5;
            if (letzterVersuch != null)
            {
                teilNote = letzterVersuch.getNote();
                if (letzterVersuch.getVersuch() > 0)
                {
                    for (Versuch versuch : versuche)
                    {
                        if (versuch.getVersuch() == 0 && versuch.getPruefung().equals(pruefung) && versuch.getNote() < teilNote)
                        {
                            teilNote = versuch.getNote();
                        }
                    }
                }
            }
            for (int i = 0; i < pruefung.getWichtung(); i++)
            {
                noten.add(teilNote);
            }
        }
        float summe = 0;
        for (Float teilNote : noten)
        {
            summe += teilNote;
        }
        return summe / noten.size();
    }

    public String getId()
    {
        return Id;
    }

    public String getName()
    {
        return Name;
    }

    public int getSWSVorlesung()
    {
        return SWSVorlesung;
    }

    public int getSWSUebung()
    {
        return SWSUebung;
    }

    public int getSWSPraktikum()
    {
        return SWSPraktikum;
    }

    public Set<Modul> getVoraussetzungen()
    {
        Set<Modul> alleVoraussetzungen = new HashSet<>();
        Set<Modul> folgendeVoraussetzungen = new HashSet<Modul>(Voraussetzungen);
        Set<Modul> neueVoraussetzungen;
        while (!folgendeVoraussetzungen.isEmpty())
        {
            neueVoraussetzungen = new HashSet<>();
            for (Modul voraussetzung : folgendeVoraussetzungen)
            {
                neueVoraussetzungen.addAll(voraussetzung.Voraussetzungen);
            }
            alleVoraussetzungen.addAll(folgendeVoraussetzungen);
            folgendeVoraussetzungen = new HashSet<>(neueVoraussetzungen);

        }
        return alleVoraussetzungen;
    }

    public Set<Pruefung> getPruefungen()
    {
        return Pruefungen;
    }

    public Pruefung getPruefungMitNr(int Nummer)
    {
        for (Pruefung pruefung : Pruefungen)
        {
            if (pruefung.getPruefungNr() == Nummer) return pruefung;
        }
        return null;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Modul modul = (Modul) o;
        return SWSVorlesung == modul.SWSVorlesung &&
                SWSUebung == modul.SWSUebung &&
                SWSPraktikum == modul.SWSPraktikum &&
                Id.equals(modul.Id) &&
                Name.equals(modul.Name);
    }

    @Override
    public String toString()
    {
        return "Modul{" +
                "Id='" + Id + '\'' +
                ", Name='" + Name + '\'' +
                ", SWSVorlesung=" + SWSVorlesung +
                ", SWSUebung=" + SWSUebung +
                ", SWSPraktikum=" + SWSPraktikum +
                ", Voraussetzungen=" + getVoraussetzungen().stream().map(Modul::getId).collect(Collectors.toList()) +
                ", Pruefungen=" + Pruefungen +
                '}';
    }
}
