package catering.businesslogic.kitchen;

import java.sql.Date;
import java.sql.Time;
import java.util.Random;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.event.Event;
import catering.businesslogic.event.Service;
import catering.businesslogic.shift.Shift;
import catering.businesslogic.staff.Assegnazione;
import catering.businesslogic.staff.Lavoratore;
import catering.businesslogic.staff.PersonnelRegistrar;
import catering.businesslogic.staff.StaffManager;
import catering.businesslogic.user.User;
import catering.persistence.PersistenceManager;
import catering.util.LogManager;
//-----------//

@TestMethodOrder(OrderAnnotation.class)
public class SummarySheetTest {

    private static final Logger LOGGER = LogManager.getLogger(SummarySheetTest.class);
    private static final Random random = new Random();

    private static CatERing app;
    private static User chef;
    private static User cook;
    private static Event testEvent;
    private static Service testService;

    private static String[] nomiCasuali = {"Marco", "Luca", "Elena", "Francesco", "Sofia", "Daniel", 
                                           "Giulia", "Alessandro", "Martina", "Lorenzo", "Chiara", "Matteo"};
    private static String[] eventiCasuali = {"Evento Test", "Evento Formale", "Conferenza IT", "Evento Demo",
                                             "Gala di Fine Anno", "Meeting Aziendale", "Festa di Laurea"};
    private static String[] commentiPositivi = {"Molto brava", "Eccellente", "Ottimo lavoro", "Prestazione superba",
                                                "Tutto perfetto", "Ben fatto", "Sopra le aspettative"};
    private static String[] motiviFerie = {"Motivi personali", "Visita medica", "Vacanza", "Impegni familiari",
                                           "Appuntamento importante", "Necessita familiare"};

    @BeforeAll
    static void init() {
        PersistenceManager.initializeDatabase("database/catering_init_sqlite.sql");
        app = CatERing.getInstance();
    }

    @BeforeEach
    void setup() {

        try {
            // Set up the chef user
            chef = User.load("Antonio");
            assertNotNull(chef, "Chef user should be loaded");
            assertTrue(chef.isChef(), "User should have chef role");

            // Set up the cook user
            cook = User.load("Luca");
            assertNotNull(cook, "Cook user should be loaded");

            // Set up event and service
            testEvent = Event.loadByName("Gala Aziendale Annuale");
            assertNotNull(testEvent, "Test event should be loaded");

            testService = Service.loadByName("Pranzo Buffet Aziendale");
            assertNotNull(testService, "Test service should be loaded");

            // Login as chef
            app.getUserManager().fakeLogin(chef.getUserName());

            assertEquals(chef, app.getUserManager().getCurrentUser(), "Current user should be the chef");

        } catch (UseCaseLogicException e) {
            LOGGER.severe(e.getMessage());
        }

    }

    @Test
    @Order(1)
    void testSummarySheetCreation() {
        LOGGER.info("Testing summary sheet creation");

        try {
            // Create summary sheet
            SummarySheet sheet = app.getKitchenTaskManager().generateSummarySheet(testEvent, testService);

            // Verify summary sheet was created properly
            assertNotNull(sheet, "Summary sheet should not be null");
            assertEquals(chef, sheet.getOwner(), "Sheet owner should be the chef who created it");
            assertNotNull(sheet.getTaskList(), "Task list should not be null");
            assertTrue(sheet.getTaskList().size() > 0, "Task list should contain tasks");

            LOGGER.info("Created summary sheet: " + sheet.toString());
        } catch (UseCaseLogicException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    @Order(2)
    void testTaskAssignment() {
        LOGGER.info("Testing task assignment to cook");

        try {
            // Create summary sheet
            SummarySheet sheet = app.getKitchenTaskManager().generateSummarySheet(testEvent, testService);
            assertNotNull(sheet, "Summary sheet should not be null");
            assertTrue(sheet.getTaskList().size() > 0, "Task list should contain tasks");

            // Get the first task
            KitchenTask taskToAssign = sheet.getTaskList().get(0);
            assertNotNull(taskToAssign, "Task to assign should not be null");

            // Create a shift
            Date shiftDate = Date.valueOf("2025-04-07");
            Time startTime = Time.valueOf("09:00:00");
            Time endTime = Time.valueOf("14:00:00");
            Shift shift = new Shift(shiftDate, startTime, endTime);
            shift.addBooking(cook);

            // Assign the task
            Assignment assignment = app.getKitchenTaskManager().assignTask(taskToAssign, shift, cook);

            // Verify assignment
            assertNotNull(assignment, "Assignment should not be null");
            assertEquals(taskToAssign, assignment.getTask(), "Assignment should reference the correct task");
            assertEquals(cook, assignment.getCook(), "Assignment should reference the correct cook");
            assertEquals(shift, assignment.getShift(), "Assignment should reference the correct shift");

            // Verify assignment is in sheet's assignment list
            assertTrue(sheet.getAssignments().contains(assignment),
                    "Sheet's assignment list should contain the new assignment");

            LOGGER.info("Successfully assigned task. Assignment details: " +
                    "Task: " + assignment.getTask().getDescription() +
                    ", Cook: " + assignment.getCook().getUserName() +
                    ", Shift: " + assignment.getShift().getDate() + " " +
                    assignment.getShift().getStartTime() + "-" +
                    assignment.getShift().getEndTime());

            LOGGER.info(sheet.toString());
        } catch (UseCaseLogicException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    @Order(3)
    void testRegistrazioneLavoratore() {
        StaffManager manager = new StaffManager();
        String nomeCasuale = nomiCasuali[random.nextInt(nomiCasuali.length)];
        Lavoratore l = manager.registraLavoratore(nomeCasuale);

        assertNotNull(l);
        assertEquals(nomeCasuale, l.getNome());
        assertFalse(l.isFerieRichieste());
        assertFalse(l.isFerieApprovate());
        
        LOGGER.info("Registrato lavoratore: " + nomeCasuale);
    }

    @Test
    @Order(4)
    void testRichiestaEApprovazioneFerie() {
        StaffManager manager = new StaffManager();
        String nomeCasuale = nomiCasuali[random.nextInt(nomiCasuali.length)];
        String motivoCasuale = motiviFerie[random.nextInt(motiviFerie.length)];
        Lavoratore l = manager.registraLavoratore(nomeCasuale);

        manager.richiediFerie(l, motivoCasuale);
        assertTrue(l.isFerieRichieste());
        assertEquals(motivoCasuale, l.getMotivoFerie());

        manager.approvaFerie(l);
        assertTrue(l.isFerieApprovate());
        
        LOGGER.info("Ferie richieste e approvate per " + nomeCasuale + ": " + motivoCasuale);
    }

    @Test
    @Order(5)
    void testAssegnazioneEvento() {
        StaffManager manager = new StaffManager();
        String nomeCasuale = nomiCasuali[random.nextInt(nomiCasuali.length)];
        String eventoCasuale = eventiCasuali[random.nextInt(eventiCasuali.length)];
        Lavoratore l = manager.registraLavoratore(nomeCasuale);

        manager.assegnaLavoratoreEvento(l, eventoCasuale);

        assertEquals(1, manager.getAssegnazioni().size());
        Assegnazione a = manager.getAssegnazioni().get(0);

        assertEquals(eventoCasuale, a.getNomeEvento());
        assertEquals(nomeCasuale, a.getLavoratore().getNome());
        
        LOGGER.info("Assegnato " + nomeCasuale + " all'evento: " + eventoCasuale);
    }

    @Test
    @Order(6)
    void testValutazioneLavoratore() {
        StaffManager manager = new StaffManager();
        String nomeCasuale = nomiCasuali[random.nextInt(nomiCasuali.length)];
        String eventoCasuale = eventiCasuali[random.nextInt(eventiCasuali.length)];
        String commentoCasuale = commentiPositivi[random.nextInt(commentiPositivi.length)];
        int punteggioCasuale = 3 + random.nextInt(3);
        Lavoratore l = manager.registraLavoratore(nomeCasuale);

        manager.valutaLavoratore(l, eventoCasuale, punteggioCasuale, commentoCasuale);

        assertEquals(1, manager.getValutazioniLavoratori().size());
        assertEquals(nomeCasuale, manager.getValutazioniLavoratori().get(0).getLavoratore().getNome());
        assertEquals(punteggioCasuale, manager.getValutazioniLavoratori().get(0).getPunteggio());
        
        LOGGER.info("Valutazione lavoratore: " + nomeCasuale + " per evento " + eventoCasuale + 
                    ", punteggio: " + punteggioCasuale + ", commento: " + commentoCasuale);
    }

    @Test
    @Order(7)
    void testValutazioneEvento() {
        StaffManager manager = new StaffManager();
        String eventoCasuale = eventiCasuali[random.nextInt(eventiCasuali.length)];
        String commentoCasuale = commentiPositivi[random.nextInt(commentiPositivi.length)];
        int punteggioCasuale = 4 + random.nextInt(2);

        manager.valutaEvento(eventoCasuale, punteggioCasuale, commentoCasuale);

        assertEquals(1, manager.getValutazioniEvento().size());
        assertEquals(eventoCasuale, manager.getValutazioniEvento().get(0).getNomeEvento());
        assertEquals(punteggioCasuale, manager.getValutazioniEvento().get(0).getPunteggio());
        
        LOGGER.info("Valutazione evento: " + eventoCasuale + 
                    ", punteggio: " + punteggioCasuale + ", commento: " + commentoCasuale);
    }

    @Test
    @Order(8)
    void testRegistrarIntegrazione() {
        PersonnelRegistrar registrar = new PersonnelRegistrar();
        String nomeCasuale = nomiCasuali[random.nextInt(nomiCasuali.length)];
        String eventoCasuale = eventiCasuali[random.nextInt(eventiCasuali.length)];
        String motivoCasuale = motiviFerie[random.nextInt(motiviFerie.length)];
        String commentoCasuale = commentiPositivi[random.nextInt(commentiPositivi.length)];
        int punteggioCasuale = 4 + random.nextInt(2);
        Lavoratore l = registrar.registraLavoratore(nomeCasuale);

        registrar.assegnaLavoratoreEvento(l, eventoCasuale);
        registrar.richiediFerie(l, motivoCasuale);
        registrar.approvaFerie(l);
        registrar.valutaLavoratore(l, eventoCasuale, punteggioCasuale, commentoCasuale);

        assertTrue(l.isFerieApprovate());
        assertEquals(1, registrar.getStaffManager().getAssegnazioni().size());
        assertEquals(1, registrar.getStaffManager().getValutazioniLavoratori().size());
        
        LOGGER.info("Test integrazione completato per " + nomeCasuale + 
                    ": assegnato a " + eventoCasuale + ", ferie approvate per " + motivoCasuale + 
                    ", valutazione " + punteggioCasuale + " con commento: " + commentoCasuale);
    }
}

