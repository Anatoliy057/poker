package stud.task.core;

import org.junit.jupiter.api.Test;
import stud.task.core.player.SimpleStorage;
import stud.task.core.player.Storage;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    @Test
    void expectAlmostSaveAllSum() {
        SimpleStorage[] stores = new SimpleStorage[] {
                new SimpleStorage(300),
                new SimpleStorage(300),
                new SimpleStorage(300),
                new SimpleStorage(300),
                new SimpleStorage(300),
                new SimpleStorage(300),
                new SimpleStorage(300),
                new SimpleStorage(300),
        };
        Bank bank = new Bank(Arrays.asList(stores));

        //bet 100
        for (Storage s :
                stores) {
            assertTrue(bank.putBy(s, 100));
        }

        //bet 250 for 0 - 4
        for (int i = 0; i < 4; i++) {
            assertTrue(bank.putBy(stores[i], 150));
        }

        //create list of winners
        List<Storage> winners = new LinkedList<>(Arrays.asList(stores).subList(2, 8));

        //counting all final sum
        bank.bankTaking(winners);
        double sum = 0;
        for (SimpleStorage s :
                stores) {
            sum += s.getPurse();
        }
        assertEquals(2400, sum, 5);
    }

    @Test
    void expectSaveAllSum() {
        SimpleStorage[] stores = new SimpleStorage[] {
                new SimpleStorage(300),
                new SimpleStorage(300),
                new SimpleStorage(300),
                new SimpleStorage(300),
                new SimpleStorage(300),
                new SimpleStorage(300),
                new SimpleStorage(300),
                new SimpleStorage(300),
        };
        Bank bank = new Bank(Arrays.asList(stores));

        //bet 100
        for (Storage s :
                stores) {
            assertTrue(bank.putBy(s, 100));
        }

        //bet 250 for 0 - 4
        for (int i = 0; i < 4; i++) {
            assertTrue(bank.putBy(stores[i], 150));
        }

        //create list of winners
        List<Storage> winners = new LinkedList<>(Arrays.asList(stores).subList(0, 4));

        //counting all final sum
        bank.bankTaking(winners);
        double sum = 0;
        for (SimpleStorage s :
                stores) {
            sum += s.getPurse();
        }
        assertEquals(2400, sum);
    }
}