package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;

public interface Fixable {
    /**
     * Method that attempts to fix oven.
     * @throws CannotFixException
     */
    void fix() throws CannotFixException;

    /**
     * Get the number of times the oven has been fixed.
     * @return
     */
    int getTimesFixed();
}
