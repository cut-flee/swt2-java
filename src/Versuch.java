import java.util.Set;

public class Versuch
{
    public static Versuch naechsterVersuch(Versuch versuch, float note)
    {
        if (versuch != null && versuch.getVersuch() < 3)
        {
            return new Versuch(versuch.Pruefung, versuch.Versuch + 1, note);
        }
        return null;
    }

    public static Versuch letzterVersuch(Set<Versuch> versuche, Pruefung pruefung)
    {
        Versuch letzterVersuch = null;
        for (Versuch versuch : versuche)
        {
            if (versuch.getPruefung().equals(pruefung))
            {
                if (letzterVersuch == null || (letzterVersuch.getVersuch() < versuch.getVersuch()))
                {
                    letzterVersuch = versuch;
                }
            }
        }
        return letzterVersuch;
    }

    private Pruefung Pruefung;
    private int Versuch;
    private float Note;

    public Versuch(Pruefung pruefung, int versuch, float note)
    {
        Pruefung = pruefung;
        Versuch = versuch;
        Note = note;
    }

    public boolean bestanden()
    {
        return Note <= 4;
    }

    public Modul getModul()
    {
        return Pruefung.getModul();
    }

    public Pruefung getPruefung()
    {
        return Pruefung;
    }

    public int getVersuch()
    {
        return Versuch;
    }


    public float getNote()
    {
        return Note;
    }

    @Override
    public String toString()
    {
        return "Versuch{" +
                "Pruefung=" + Pruefung +
                ", Versuch=" + Versuch +
                ", Note=" + Note +
                '}';
    }
}