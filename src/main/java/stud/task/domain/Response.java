package stud.task.domain;

import java.util.StringJoiner;

public class Response {

    private long limit;
    private long bet;
    private long bank;
    private long purse;

    public Response() {}

    public Response(long limit, long bet, long bank, long purse) {
        this.limit = limit;
        this.bet = bet;
        this.bank = bank;
        this.purse = purse;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getBet() {
        return bet;
    }

    public void setBet(long bet) {
        this.bet = bet;
    }

    public long getBank() {
        return bank;
    }

    public void setBank(long cBank) {
        this.bank = cBank;
    }

    public long getPurse() {
        return purse;
    }

    public void setPurse(long purse) {
        this.purse = purse;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Response.class.getSimpleName() + "[", "]")
                .add("limit=" + limit)
                .add("bet=" + bet)
                .add("purse=" + purse)
                .toString();
    }
}
