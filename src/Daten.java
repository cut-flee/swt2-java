import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Daten
{
    public static Pruefungsordnung generateInfB2009()
    {
        Modul INF_B_110 = new Modul("INF-B-110", "Einführung in die Mathematik für Informatiker", 6, 4, 0,
                new HashSet<Modul>(),
                new HashSet<Pruefung>()
        );
        INF_B_110.getPruefungen().add(new Pruefung(INF_B_110, 1, 90, 1, false));
        INF_B_110.getPruefungen().add(new Pruefung(INF_B_110, 2, 120, 2, false));

        Modul INF_B_120 = new Modul("INF-B-120", "Mathematische Methoden für Informatiker", 6, 4, 0,
                new HashSet<Modul>(Arrays.asList(INF_B_110)),
                new HashSet<Pruefung>()
        );
        INF_B_120.getPruefungen().add(new Pruefung(INF_B_120, 1, 90, 1, false));
        INF_B_120.getPruefungen().add(new Pruefung(INF_B_120, 2, 120, 2, false));

        Modul INF_B_210 = new Modul("INF-B-210", "Algorithmen und Datenstrukturen", 2, 2, 0,
                new HashSet<Modul>(),
                new HashSet<Pruefung>()
        );
        INF_B_210.getPruefungen().add(new Pruefung(INF_B_210, 1, 90, 1, false));

        Modul INF_B_230 = new Modul("INF-B-230", "Einführungspraktikum RoboLab", 0, 0, 4,
                new HashSet<Modul>(),
                new HashSet<Pruefung>()
        );
        INF_B_230.getPruefungen().add(new Pruefung(INF_B_230, 1, 0, 1, false));

        Modul INF_B_240 = new Modul("INF-B-240", "Programmierung", 2, 2, 0,
                new HashSet<Modul>(),
                new HashSet<Pruefung>()
        );
        INF_B_240.getPruefungen().add(new Pruefung(INF_B_240, 1, 90, 1, false));

        Modul INF_B_260 = new Modul("INF-B-260", "Informations- und Kodierungstheorie", 2, 1, 0,
                new HashSet<Modul>(Arrays.asList(INF_B_110)),
                new HashSet<Pruefung>()
        );
        INF_B_260.getPruefungen().add(new Pruefung(INF_B_260, 1, 90, 1, false));

        Modul INF_B_270 = new Modul("INF-B-270", "Formale Systeme", 4, 2, 0,
                new HashSet<Modul>(Arrays.asList(INF_B_120, INF_B_240, INF_B_260)),
                new HashSet<Pruefung>()
        );
        INF_B_270.getPruefungen().add(new Pruefung(INF_B_270, 1, 90, 1, false));

        Modul INF_B_290 = new Modul("INF-B-290", "Theoretische Informatik und Logik", 4, 2, 0,
                new HashSet<Modul>(Arrays.asList(INF_B_270)),
                new HashSet<Pruefung>()
        );
        INF_B_290.getPruefungen().add(new Pruefung(INF_B_290, 1, 90, 1, false));

        Modul INF_B_310 = new Modul("INF-B-310", "Softwaretechnologie", 2, 2, 0,
                new HashSet<Modul>(Arrays.asList(INF_B_210, INF_B_230)),
                new HashSet<Pruefung>()
        );
        INF_B_310.getPruefungen().add(new Pruefung(INF_B_310, 1, 120, 1, false));

        Modul INF_B_320 = new Modul("INF-B-320", "Softwaretechnologie-Projekt", 0, 0, 4,
                new HashSet<Modul>(Arrays.asList(INF_B_310)),
                new HashSet<Pruefung>()
        );
        INF_B_320.getPruefungen().add(new Pruefung(INF_B_320, 1, 0, 1, false));


        Modul INF_B_330 = new Modul("INF-B-330", "Rechnerarchitektur", 4, 4, 0,
                new HashSet<Modul>(Arrays.asList(INF_B_110)),
                new HashSet<Pruefung>()
        );
        INF_B_330.getPruefungen().add(new Pruefung(INF_B_330, 1, 240, 1, false));

        Modul INF_B_370 = new Modul("INF-B-370", "Datenbanken und Rechnernetze", 4, 4, 0,
                new HashSet<Modul>(Arrays.asList(INF_B_120, INF_B_230, INF_B_240, INF_B_260, INF_B_310)),
                new HashSet<Pruefung>()
        );
        INF_B_370.getPruefungen().add(new Pruefung(INF_B_370, 1, 90, 1, false));

        Modul INF_B_380 = new Modul("INF-B-380", "Betriebssysteme und Sicherheit", 4, 2, 0,
                new HashSet<Modul>(Arrays.asList(INF_B_270, INF_B_310, INF_B_330)),
                new HashSet<Pruefung>()
        );
        INF_B_380.getPruefungen().add(new Pruefung(INF_B_380, 1, 90, 1, false));

        Modul INF_B_390 = new Modul("INF-B-390", "Technische Grundlagen und Hardwarepraktikum", 3, 2, 3,
                new HashSet<Modul>(Arrays.asList(INF_B_110)),
                new HashSet<Pruefung>()
        );
        INF_B_390.getPruefungen().add(new Pruefung(INF_B_390, 1, 120, 1, false));
        INF_B_390.getPruefungen().add(new Pruefung(INF_B_390, 2, 0, 1, true));

        Modul INF_B_3A0 = new Modul("INF-B-3A0", "Systemorientierte Informatik/Hardware Software-Codesign", 2, 2, 0,
                new HashSet<Modul>(Arrays.asList(INF_B_120, INF_B_240, INF_B_260, INF_B_310, INF_B_330, INF_B_390)),
                new HashSet<Pruefung>()
        );
        INF_B_3A0.getPruefungen().add(new Pruefung(INF_B_3A0, 1, 90, 1, false));

        Modul INF_B_3B0 = new Modul("INF-B-3B0", "Intelligente Systeme", 2, 2, 0,
                new HashSet<Modul>(Arrays.asList(INF_B_290, INF_B_310)),
                new HashSet<Pruefung>()
        );
        INF_B_3B0.getPruefungen().add(new Pruefung(INF_B_3B0, 1, 90, 1, false));

        Modul INF_B_410 = new Modul("INF-B-410", "Einführung in die Medieninformatik", 2, 2, 0,
                new HashSet<Modul>(),
                new HashSet<Pruefung>()
        );
        INF_B_410.getPruefungen().add(new Pruefung(INF_B_410, 1, 90, 1, false));

        Modul INF_B_420 = new Modul("INF-B-420", "Einführung in die Computergraphik", 2, 1, 1,
                new HashSet<Modul>(Arrays.asList(INF_B_110, INF_B_210, INF_B_230, INF_B_410)),
                new HashSet<Pruefung>()
        );
        INF_B_420.getPruefungen().add(new Pruefung(INF_B_420, 1, 90, 1, false));

        Modul INF_B_510 = new Modul("INF-B-510", "Vertiefung in der Informatik", 0, 0, 0,
                new HashSet<Modul>(Arrays.asList(INF_B_290, INF_B_320, INF_B_330, INF_B_370, INF_B_390)),
                new HashSet<Pruefung>()
        );
        INF_B_510.getPruefungen().add(new Pruefung(INF_B_510, 1, 0, 1, true));

        Modul INF_B_520 = new Modul("INF-B-520", "Spezialisierung in der Informatik", 0, 0, 0,
                new HashSet<Modul>(Arrays.asList(INF_B_380, INF_B_3A0, INF_B_3B0, INF_B_420, INF_B_510)),
                new HashSet<Pruefung>()
        );
        INF_B_520.getPruefungen().add(new Pruefung(INF_B_520, 1, 0, 1, true));

        Modul INF_B_610 = new Modul("INF-B-610", "Überfachliche Qualifikationen zur Informatik", 0, 0, 0,
                new HashSet<Modul>(),
                new HashSet<Pruefung>()
        );
        INF_B_610.getPruefungen().add(new Pruefung(INF_B_610, 1, 0, 1, true));

        Modul Bachelor = new Modul("Bachelor", "Bachelor Arbeit", 0, 0, 0,
                new HashSet<Modul>(),
                new HashSet<Pruefung>()
        );

        Modul Kolloquium = new Modul("Kolloquium", "Kolloquium", 0, 0, 0,
                new HashSet<Modul>(Arrays.asList(Bachelor)),
                new HashSet<Pruefung>()
        );

        Set<Modul> module = new HashSet<Modul>();

        module.add(INF_B_110);
        module.add(INF_B_120);
        module.add(INF_B_210);
        module.add(INF_B_230);
        module.add(INF_B_240);
        module.add(INF_B_260);
        module.add(INF_B_270);
        module.add(INF_B_290);
        module.add(INF_B_310);
        module.add(INF_B_320);
        module.add(INF_B_330);
        module.add(INF_B_370);
        module.add(INF_B_380);
        module.add(INF_B_390);
        module.add(INF_B_3A0);
        module.add(INF_B_3B0);
        module.add(INF_B_410);
        module.add(INF_B_420);
        module.add(INF_B_510);
        module.add(INF_B_520);
        module.add(INF_B_610);
        module.add(Bachelor);
        module.add(Kolloquium);

        return new Pruefungsordnung("INFB2009", module);
    }
}
