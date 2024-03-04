package ee.taltech.iti0202.mysticorbs.exceptions;

import ee.taltech.iti0202.mysticorbs.oven.Oven;

public class CannotFixException extends Throwable {
    private final Oven oven;
    private final Reason reason;

    public enum Reason {
        IS_NOT_BROKEN, FIXED_MAXIMUM_TIMES, NOT_ENOUGH_RESOURCES
    }

    /**
     * Exception thrown to indicate that a specific oven issue cannot be fixed.
     *
     * @param oven   The {@link Oven} representing the oven for which the issue cannot be resolved.
     * @param reason The {@link Reason} specifying the cause or reason for not being able to fix
     *               the oven issue.
     */
    public CannotFixException(Oven oven, Reason reason) {
        this.oven = oven;
        this.reason = reason;
    }
    public Oven getOven() {
        return oven;
    }

    public Reason getReason() {
        return reason;
    }
}
