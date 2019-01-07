import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main
{
    public static void main(String[] args)
    {
        Pruefungsordnung pruefungsordnung = Daten.generateInfB2009();
        Set<Student> studenten = new HashSet<>();
        boolean quit = false;
        Scanner in = new Scanner(System.in);
        int matrikelNr = 111;

        while (!quit)
        {
            System.out.print(">> ");
            switch (in.next())
            {
                case ("q"):
                case ("exit"):
                case ("quit"):
                {
                    quit = true;
                }
                break;
                case ("n"):
                case ("neuer_Student"):
                {
                    Student student = new Student(in.next(), matrikelNr, new HashSet<>(), pruefungsordnung);
                    matrikelNr++;
                    studenten.add(student);
                    System.out.print("neuer Student: ");
                    System.out.print(student.getName());
                    System.out.print(" ");
                    System.out.println(student.getMatrikelNr());
                }
                break;
                case ("S"):
                case ("Studenten"):
                {
                    for (Student student : studenten)
                    {
                        System.out.print(student.getName());
                        System.out.print(" ");
                        System.out.println(student.getMatrikelNr());

                    }
                }
                break;
                case ("p"):
                case ("moegliche_Pruefungen"):
                {
                    int matrkl = Integer.valueOf(in.next());
                    for (Student student : studenten)
                    {
                        if (student.getMatrikelNr() == matrkl)
                        {
                            for (Pruefung pruefung : student.getMoeglichePruefungen())
                            {
                                System.out.print(pruefung.getModul().getId());
                                System.out.print(" ");
                                System.out.print(pruefung.getPruefungNr());
                                System.out.print(" ");
                                System.out.print(pruefung.getLaenge());
                                System.out.print(" ");
                                System.out.println(pruefung.getModul().getName());

                            }
                        }
                    }
                }
                break;
                case ("v"):
                case ("Versuch"):
                {
                    int matrkl = Integer.valueOf(in.next());
                    String id = in.next();
                    float note = Float.valueOf(in.next());
                    if (id.length() == 3)
                    {
                        id = "INF-B-" + id;
                    }
                    for (Student student : studenten)
                    {
                        if (student.getMatrikelNr() == matrkl)
                        {
                            for (Pruefung pruefung : student.getMoeglichePruefungen())
                            {
                                if (pruefung.getModul().getId().equals(id))
                                {
                                    student.setNaechsterVersuch(id, note);
                                    System.out.print(id);
                                    System.out.print(" Modulnote: ");
                                    System.out.println(student.getNote(id));
                                }
                            }
                        }
                    }
                }
                break;
                case ("m"):
                case ("Modulnoten"):
                {
                    int matrkl = Integer.valueOf(in.next());
                    for (Student student : studenten)
                    {
                        if (student.getMatrikelNr() == matrkl)
                        {
                            for (Modul modul:student.angefangeneModule())
                            {
                                System.out.print(modul.getId());
                                System.out.print(" ");
                                System.out.print(student.getNote(modul));
                                System.out.print(" ");
                                System.out.println(modul.getName());
                                for (Versuch versuch:student.getVersuche())
                                {
                                    if (versuch.getModul().equals(modul))
                                    {
                                        System.out.print("Prüfung: ");
                                        System.out.print(versuch.getPruefung().getPruefungNr());
                                        System.out.print(" Versuch: ");
                                        System.out.print(versuch.getVersuch());
                                        System.out.print(" Note: ");
                                        System.out.println(versuch.getNote());
                                    }
                                }
                                System.out.println();
                            }
                        }
                    }


                }
                break;
                case ("s"):
                case ("status"):
                {
                    int matrkl = Integer.valueOf(in.next());
                    for (Student student : studenten)
                    {
                        if (student.getMatrikelNr() == matrkl)
                        {
                            System.out.print(student.getMatrikelNr());
                            System.out.print(" ");
                            System.out.print(student.getName());

                            if (student.isExmatrikuliert())
                            {
                                System.out.println(" ist exmatrikuliert.");
                            }
                            else if (student.abschluss())
                            {
                                System.out.print(" hat das Studium abgeschlossen.");
                            }
                            else
                            {
                                System.out.print(" hat ");
                                System.out.print(student.getBestandeneModule().size());
                                System.out.print(" von ");
                                System.out.print(student.getStudiengang().getModule().size());
                                System.out.println(" Module bestanden.");
                            }
                        }
                    }
                }
                break;
                default:
                {
                    System.out.println("Komandos: ");
                    System.out.println("------------------------------------------------------------");
                    System.out.println("help: zeigt diese Hilfe an");
                    System.out.println();
                    System.out.println("q, exit, quit: Programm verlassen");
                    System.out.println();
                    System.out.println("S, Studenten: listet alle Studenten auf");
                    System.out.println();
                    System.out.println("n [name], neuer_Student [name]: ");
                    System.out.println();
                    System.out.println("    legt ein neuen Studenten mit dem Namen [name] an");
                    System.out.println();
                    System.out.println("p [mtrkl], moegliche_Pruefungen [mtrkl]:");
                    System.out.println();
                    System.out.println("    listet alle möglichen Prüfungen für den Studenten mit der");
                    System.out.println("    MatrikelNr. [mtrkl] auf");
                    System.out.println();
                    System.out.println("v [mtrkl] [modul] [note], Versuch [mtrkl] [modul] [note]:");
                    System.out.println();
                    System.out.println("    fügt einen neuen Versuch für den Studenten mit der");
                    System.out.println("    MatrikelNr. [mtrkl] im Modul mit der Bezeichnung [modul]");
                    System.out.println("    ein");
                    System.out.println("    [modul] kann auch nur die letzten drei Ziffern der");
                    System.out.println("    Modulbezeichnung  sein");
                    System.out.println();
                    System.out.println("m [mtrkl], Modulnoten [mtrkl]:");
                    System.out.println();
                    System.out.println("    listet alle Modulnoten und Versuche für den Studenten mit");
                    System.out.println("    mit der MatrikelNr. [mtrkl] auf");
                    System.out.println();
                    System.out.println("s [mtrkl], Status [matrkl]:");
                    System.out.println();
                    System.out.println("    gibt den Studienfortschritt des Studenten mit der");
                    System.out.println("    MatrikelNr. [mtrkl] aus");
                    System.out.println("------------------------------------------------------------");
                }
                break;
            }
        }
    }
}
