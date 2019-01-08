import java.util.Set;

public class Pruefungsordnung
{
    private String Name;
    private Set<Modul> Module;

    public Pruefungsordnung(String name, Set<Modul> module)
    {
        Name = name;
        Module = module;
    }

    public Modul getModul(String modulId)
    {
        for (Modul modul : Module)
        {
            if (modul.getId().equals(modulId))
            {
                return modul;
            }
        }
        return null;
    }

    public String getName()
    {
        return Name;
    }

    public Set<Modul> getModule()
    {
        return Module;
    }
}
