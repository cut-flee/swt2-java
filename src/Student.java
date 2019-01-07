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
            for (Versuch versuch:Versuche)
            {
                if (versuch.getPruefung().equals(pruefung) && versuch.getVersuch()>0) return false;
            }
        }
        Set<Pruefung> voraussetzungen = new HashSet<Pruefung>();
        pruefung.getModul().getVoraussetzungen().forEach(p -> voraussetzungen.addAll(p.getPruefungen()));
        if (!BestandenePruefungen.containsAll(voraussetzungen)) return false;
        if (pruefung.getModul().getPruefungMitNr(pruefung.getPruefungNr() - 1) == null) return true;
        return BestandenePruefungen.contains(pruefung.getModul().getPruefungMitNr(pruefung.getPruefungNr() - 1));
    }

    public void setNaechsterVersuch(String modulId, float note)
    {
        setNaechsterVersuch(Studiengang.getModul(modulId), note);
    }

    public void setNaechsterVersuch(Modul modul, float note)
    {
        Versuch letzterVersuch = null;
        for (Pruefung pruefung : modul.getPruefungen())
        {
            Versuch letzterPruefungsVersuch = Versuch.letzterVersuch(Versuche,pruefung);

            if (letzterVersuch == null || (letzterPruefungsVersuch != null && letzterPruefungsVersuch.getPruefung().getPruefungNr() > letzterVersuch.getPruefung().getPruefungNr()))
            {
                letzterVersuch = letzterPruefungsVersuch;
            }

        }
        int naechstePruefungsNr = 1;
        if (letzterVersuch != null)
        {
            if (letzterVersuch.bestanden())
            {
                naechstePruefungsNr = letzterVersuch.getPruefung().getPruefungNr() + 1;
            } else
            {
                addVersuch(Versuch.naechsterVersuch(letzterVersuch, note));
                return;
            }
        }
        Pruefung naechstePruefung = null;
        for (Pruefung pruefung : modul.getPruefungen())
        {
            if (pruefung.getPruefungNr() == naechstePruefungsNr)
            {
                naechstePruefung = pruefung;
            }
        }

        if (naechstePruefung != null)
        {
            addVersuch(new Versuch(naechstePruefung, 1, note));
        }
    }

    public void freiVersuch(String modulId, int pruefungsNr, float note)
    {
        freiVersuch(Studiengang.getModul(modulId), pruefungsNr, note);
    }

    public void freiVersuch(Modul modul, int pruefungsNr, float note)
    {
        if (modul.getPruefungMitNr(pruefungsNr) != null)
        {
            freiVersuch(modul.getPruefungMitNr(pruefungsNr),note);
        }
    }

    public void freiVersuch(Pruefung pruefung, float note) {
        for (Versuch versuch:Versuche)
        {
            if (versuch.getPruefung().equals(pruefung) && versuch.getVersuch() > 0) return;
        }
        addVersuch(new Versuch(pruefung,0,note));
    }

    public void addVersuch(Versuch versuch)
    {
        if (!fuerPruefungZugelassen(versuch.getPruefung())) return;
        Versuche.add(versuch);
        if (versuch.bestanden())
        {
            BestandenePruefungen.add(versuch.getPruefung());
            if (versuch.getModul().bestanden(Versuche))
            {
                BestandeneModule.add(versuch.getModul());
            }
        }else if (versuch.getVersuch() == 3)
        {
            exmatrikuliert = true;
        }
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
