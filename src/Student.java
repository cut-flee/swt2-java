import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Student
{
    private String Name;
    private int MatrikelNr;
    private Set<Versuch> Versuche;
    private Set<Pruefung> BestandenePruefungen;
    private Set<Modul> BestandeneModule;
    private Pruefungsordnung Studiengang;
    private boolean exmatrikuliert;

    public Student(String name, int matrikelNr, Set<Versuch> versuche, Pruefungsordnung studiengang)
    {
        Name = name;
        MatrikelNr = matrikelNr;
        Versuche = versuche;
        BestandenePruefungen = new HashSet<>();
        BestandeneModule = new HashSet<>();
        Studiengang = studiengang;
        exmatrikuliert = false;
    }

    public Set<Pruefung> getMoeglichePruefungen()
    {
        Set<Pruefung> allePruefungen = new HashSet<>();
        Studiengang.getModule().stream().map(Modul::getPruefungen).collect(Collectors.toSet()).forEach(allePruefungen::addAll);
        return allePruefungen.stream().filter(this::fuerPruefungZugelassen).collect(Collectors.toSet());
    }

    public boolean fuerPruefungZugelassen(Pruefung pruefung)
    {
        if (exmatrikuliert) return false;
        if (BestandenePruefungen.contains(pruefung))
        {
            for (Versuch versuch : Versuche)
            {
                if (versuch.getPruefung().equals(pruefung) && versuch.getVersuch() > 0) return false;
            }
        }
        Set<Pruefung> voraussetzungen = new HashSet<Pruefung>();
        pruefung.getModul().getVoraussetzungen().forEach(p -> voraussetzungen.addAll(p.getPruefungen()));
        if (!BestandenePruefungen.containsAll(voraussetzungen)) return false;
        if (pruefung.getModul().getPruefungMitNr(pruefung.getPruefungNr() - 1) == null) return true;
        return BestandenePruefungen.contains(pruefung.getModul().getPruefungMitNr(pruefung.getPruefungNr() - 1));
    }

    public boolean setNaechsterVersuch(String modulId, int pruefungsNr, float note)
    {
        return setNaechsterVersuch(Studiengang.getModul(modulId), pruefungsNr, note);
    }

    public boolean setNaechsterVersuch(Modul modul, int pruefungsNr, float note)
    {
        if (modul.getPruefungMitNr(pruefungsNr) != null)
        {
            return setNaechsterVersuch(modul.getPruefungMitNr(pruefungsNr), note);
        }
        return false;
    }

    public boolean setNaechsterVersuch(Pruefung pruefung, float note)
    {
        Versuch letzterVersuch = Versuch.letzterVersuch(Versuche, pruefung);
        if (letzterVersuch == null || letzterVersuch.getVersuch() == 0)
        {
            return addVersuch(new Versuch(pruefung, 1, note));
        } else
        {
            Versuch naechsterVersuch = Versuch.naechsterVersuch(letzterVersuch, note);
            if (naechsterVersuch == null) return false;
            return addVersuch(naechsterVersuch);
        }
    }

    public boolean freiVersuch(String modulId, int pruefungsNr, float note)
    {
        return freiVersuch(Studiengang.getModul(modulId), pruefungsNr, note);
    }

    public boolean freiVersuch(Modul modul, int pruefungsNr, float note)
    {
        if (modul.getPruefungMitNr(pruefungsNr) != null)
        {
            return freiVersuch(modul.getPruefungMitNr(pruefungsNr), note);
        }
        return false;
    }

    public boolean freiVersuch(Pruefung pruefung, float note)
    {
        for (Versuch versuch : Versuche)
        {
            if (versuch.getPruefung().equals(pruefung) && versuch.getVersuch() > 0) return false;
        }
        return addVersuch(new Versuch(pruefung, 0, note));
    }

    public boolean addVersuch(Versuch versuch)
    {
        if (!fuerPruefungZugelassen(versuch.getPruefung())) return false;
        Versuche.add(versuch);
        if (versuch.bestanden())
        {
            BestandenePruefungen.add(versuch.getPruefung());
            if (versuch.getModul().bestanden(Versuche))
            {
                BestandeneModule.add(versuch.getModul());
            }
        } else if (versuch.getVersuch() == 3)
        {
            exmatrikuliert = true;
        }
        return true;
    }

    public Set<Modul> angefangeneModule()
    {
        Set<Modul> module = new HashSet<>();
        for (Versuch versuch : Versuche)
        {
            module.add(versuch.getModul());
        }
        return module;
    }

    public boolean abschluss()
    {
        return BestandeneModule.containsAll(Studiengang.getModule());
    }

    public Set<Pruefung> getBestandenePruefungen()
    {
        return BestandenePruefungen;
    }

    public boolean isExmatrikuliert()
    {
        return exmatrikuliert;
    }

    public float getNote(String modulId)
    {
        return getNote(Studiengang.getModul(modulId));
    }

    public float getNote(Modul modul)
    {
        return modul.Note(Versuche);
    }

    public boolean bestanden(Modul modul)
    {
        return BestandeneModule.contains(modul);
    }

    public boolean bestanden(Pruefung pruefung)
    {
        return BestandenePruefungen.contains(pruefung);
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public int getMatrikelNr()
    {
        return MatrikelNr;
    }

    public Set<Versuch> getVersuche()
    {
        return Versuche;
    }

    public Set<Modul> getBestandeneModule()
    {
        return BestandeneModule;
    }

    public Pruefungsordnung getStudiengang()
    {
        return Studiengang;
    }

    @Override
    public String toString()
    {
        return "Student{" +
                "Name='" + Name + '\'' +
                ", MatrikelNr=" + MatrikelNr +
                ", Versuche=" + Versuche +
                ", BestandenePruefungen=" + BestandenePruefungen +
                ", BestandeneModule=" + BestandeneModule +
                ", Studiengang=" + Studiengang +
                '}';
    }
}
