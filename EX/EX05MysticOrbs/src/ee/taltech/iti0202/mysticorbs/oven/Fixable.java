package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;

public interface Fixable {
    void fix() throws CannotFixException;

    int getTimesFixed();
}
