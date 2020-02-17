package stud.task.core.component;

import org.json.*;
import stud.task.core.player.Storage;

import java.util.*;

public class Bank implements JSONString {

    private Map<UUID, Long> party;
    private long bank = 0;

    public Bank(Collection<UUID> ids) {
        party = new HashMap<>();
        ids.forEach(id -> party.put(id, 0L));
    }

    public void reload(Collection<UUID> ids) {
        party = new HashMap<>();
        ids.forEach(id -> party.put(id, 0L));
    }

    public Bank(JSONObject o) {
        party = new HashMap<>();

        bank = o.getInt("bank");
        JSONArray arr = o.getJSONArray("party");
        for (int i = 0; i < arr.length(); i++) {
            JSONObject s = arr.getJSONObject(i);
            UUID id = UUID.fromString(s.getString("id"));
            long bet = s.getInt("bet");
            party.put(id, bet);
        }

    }

    public long get() {
        return bank;
    }

    public void betBy(UUID id, long bet) {
        long oldBet = party.get(id);
        bank += (bet - oldBet);
        party.put(id, bet);
    }

    public long getBetBy(UUID id) {
        return party.get(id);
    }

    public void commit(Map<UUID, Storage> players) {
        party.forEach((key, value) -> players.get(key).take(value));
    }

    public void distribute(Map<UUID, Storage> winners) {
        long prize = bank/winners.size();
        Map.Entry<UUID, Storage> first = winners.entrySet().iterator().next();
        for (Map.Entry<UUID, Storage> w:
                winners.entrySet()) {
            w.getValue().pull(prize);
        }
        long temp = bank - prize*winners.size();
        first.getValue().pull(temp);
    }

    public BankState getState() {
        return new BankState(bank, party);
    }

    public void setState(BankState state) {
        bank = state.getBank();
        party = new HashMap<>(state.getParty());
    }

    @Override
    public String toJSONString() {
        JSONWriter writer = new JSONStringer();
        writer.object()
                .key("bank").value(bank)
                .key("party").array();
        party.forEach((id, bet) -> {
            writer.object()
                    .key("id").value(id)
                    .key("bet").value(bet)
                    .endObject();
        });
        return writer.endArray().endObject().toString();
    }

    public class BankState {
        private long bank;
        private Map<UUID, Long> party;

        public BankState(long bank, Map<UUID, Long> party) {
            this.bank = bank;
            this.party = new HashMap<>(party);
        }

        private long getBank() {
            return bank;
        }

        private Map<UUID, Long> getParty() {
            return party;
        }
    }
}
