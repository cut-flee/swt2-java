import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class Test
{
    private Student student;

    static private Pruefungsordnung pruefungsordnung;
    static private Modul modul1;
    static private Modul modul2;
    static private Modul modul3;
    static private Pruefung pruefung1;
    static private Pruefung pruefung21;
    static private Pruefung pruefung22;
    static private Pruefung pruefung3;

    private Versuch versuch1(int nr)
    {
        return new Versuch(pruefung1, nr, 1);
    }

    private Versuch versuch21(int nr)
    {
        return new Versuch(pruefung21, nr, 1);
    }

    private Versuch versuch22(int nr)
    {
        return new Versuch(pruefung22, nr, 1);
    }

    private Versuch versuch3(int nr)
    {
        return new Versuch(pruefung3, nr, 1);
    }

    private Versuch fehlVersuch1(int nr)
    {
        return new Versuch(pruefung1, nr, 5);
    }

    private Versuch fehlVersuch21(int nr)
    {
        return new Versuch(pruefung21, nr, 5);
    }

    private Versuch fehlVersuch22(int nr)
    {
        return new Versuch(pruefung22, nr, 5);
    }

    private Versuch fehlVersuch3(int nr)
    {
        return new Versuch(pruefung3, nr, 5);
    }

    @org.junit.jupiter.api.BeforeAll
    static void before()
    {
        modul1 = new Modul("1", "Modul1", 1, 1, 1,
                new HashSet<>(), new HashSet<>());
        pruefung1 = new Pruefung(modul1, 1, 45, 1, false);
        modul1.getPruefungen().add(pruefung1);

        modul2 = new Modul("2", "Modul2", 2, 2, 0,
                new HashSet<>(), new HashSet<>());
        pruefung21 = new Pruefung(modul2, 1, 30, 1, false);
        pruefung22 = new Pruefung(modul2, 2, 0, 4, true);
        modul2.getPruefungen().add(pruefung21);
        modul2.getPruefungen().add(pruefung22);

        modul3 = new Modul("3", "Modul3", 2, 1, 0,
                new HashSet<Modul>(Arrays.asList(modul1)), new HashSet<>());
        pruefung3 = new Pruefung(modul3, 1, 120, 1, false);
        modul3.getPruefungen().add(pruefung3);

        pruefungsordnung = new Pruefungsordnung("testpruefungsordnung",
                new HashSet<>(Arrays.asList(modul1, modul2, modul3)));
    }

    @org.junit.jupiter.api.BeforeEach
    void beforeTest()
    {
        student = new Student("Max Mustermann", 111111,
                new HashSet<>(), pruefungsordnung);

    }

    @org.junit.jupiter.api.Test
    void studentGetMoeglichePruefungen()
    {
        assertEquals(new HashSet<Pruefung>(Arrays.asList(pruefung1, pruefung21)),
                student.getMoeglichePruefungen());

        student.addVersuch(fehlVersuch21(0));

        assertEquals(new HashSet<Pruefung>(Arrays.asList(pruefung1, pruefung21)),
                student.getMoeglichePruefungen());

        student.addVersuch(versuch21(0));

        assertEquals(new HashSet<Pruefung>(Arrays.asList(pruefung1, pruefung21, pruefung22)),
                student.getMoeglichePruefungen());

        student.addVersuch(versuch21(1));

        assertEquals(new HashSet<Pruefung>(Arrays.asList(pruefung1, pruefung22)),
                student.getMoeglichePruefungen());

        student.addVersuch(versuch1(1));

        assertEquals(new HashSet<Pruefung>(Arrays.asList(pruefung3, pruefung22)),
                student.getMoeglichePruefungen());

        student.addVersuch(fehlVersuch22(3));

        assertEquals(new HashSet<Pruefung>(),
                student.getMoeglichePruefungen());
    }

    @org.junit.jupiter.api.Test
    void studentSetNaechsterVersuch()
    {

        Set<Versuch> expected = new HashSet<>();

        assertEquals(expected, student.getVersuche());

        student.setNaechsterVersuch(pruefung3, 1);

        assertEquals(expected, student.getVersuche());

        student.setNaechsterVersuch(pruefung22, 1);

        assertEquals(expected, student.getVersuche());

        student.setNaechsterVersuch(pruefung21, 1);
        expected.add(versuch21(1));

        assertEquals(expected, student.getVersuche());

        student.setNaechsterVersuch(pruefung22, 1);
        expected.add(versuch22(1));

        assertEquals(expected, student.getVersuche());

        student.setNaechsterVersuch(pruefung1, 5);
        expected.add(fehlVersuch1(1));

        assertEquals(expected, student.getVersuche());

        student.setNaechsterVersuch(pruefung1, 1);
        expected.add(versuch1(2));

        assertEquals(expected, student.getVersuche());

        student.setNaechsterVersuch(pruefung3, 5);
        expected.add(fehlVersuch3(1));

        assertEquals(expected, student.getVersuche());

        student.setNaechsterVersuch(pruefung3, 5);
        expected.add(fehlVersuch3(2));

        assertEquals(expected, student.getVersuche());

        student.setNaechsterVersuch(pruefung3, 1);
        expected.add(versuch3(3));

        assertEquals(expected, student.getVersuche());
    }

    @org.junit.jupiter.api.Test
    void studentFreiVersuch()
    {
        Set<Versuch> expected = new HashSet<>();

        assertEquals(expected, student.getVersuche());

        student.freiVersuch(pruefung21, 1);
        expected.add(versuch21(0));

        assertEquals(expected, student.getVersuche());

        student.setNaechsterVersuch(pruefung21, 5);
        expected.add(fehlVersuch21(1));
        student.freiVersuch(pruefung21, 2);

        assertEquals(expected, student.getVersuche());
    }

    @org.junit.jupiter.api.Test
    void studentAddVersuch()
    {
        Set<Pruefung> expectedP = new HashSet<>();
        Set<Modul> expectedM = new HashSet<>();

        assertEquals(expectedP, student.getBestandenePruefungen());
        assertEquals(expectedM, student.getBestandeneModule());

        student.addVersuch(versuch1(1));
        expectedP.add(pruefung1);
        expectedM.add(modul1);

        assertEquals(expectedP, student.getBestandenePruefungen());
        assertEquals(expectedM, student.getBestandeneModule());

        student.addVersuch(fehlVersuch3(1));

        assertEquals(expectedP, student.getBestandenePruefungen());
        assertEquals(expectedM, student.getBestandeneModule());

        student.addVersuch(versuch21(1));
        expectedP.add(pruefung21);

        assertEquals(expectedP, student.getBestandenePruefungen());
        assertEquals(expectedM, student.getBestandeneModule());

        student.addVersuch(fehlVersuch3(3));

        assertEquals(expectedP, student.getBestandenePruefungen());
        assertEquals(expectedM, student.getBestandeneModule());

        student.addVersuch(versuch22(1));

        assertEquals(expectedP, student.getBestandenePruefungen());
        assertEquals(expectedM, student.getBestandeneModule());

    }
}