package ee.taltech.iti0202.stream.kittens;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KittenStatistics {

    private List<Kitten> kittens;

    public void setKittens(final List<Kitten> kittens) {
        this.kittens = kittens;
    }

    /**
     * Method to find average age of kittens.
     * @return
     */
    public OptionalDouble findKittensAverageAge() {
        return kittens.stream().map(Kitten::getAge).flatMapToInt(IntStream::of).average();
    }

    /**
     * Method to find oldest kitten.
     * @return
     */
    public Optional<Kitten> findOldestKitten() {
        return kittens.stream().max(Comparator.comparingInt(Kitten::getAge));
    }

    /**
     * Method to find the youngest kitten.
     * @return
     */
    public List<Kitten> findYoungestKittens() {
        int minAge = kittens.stream().min(Comparator.comparingInt(Kitten::getAge)).get().getAge();
        return kittens.stream().filter(kitten -> kitten.getAge() == minAge).collect(Collectors.toList());
    }

    /**
     * Method to find kitten according to gender.
     * @param gender
     * @return
     */
    public List<Kitten> findKittensAccordingToGender(final Kitten.Gender gender) {
        return kittens.stream().filter(kitten -> kitten.getGender().equals(gender)).collect(Collectors.toList());
    }

    /**
     * Method to find kitten between ages.
     * @param minAge
     * @param maxAge
     * @return
     */
    public List<Kitten> findKittensBetweenAges(final int minAge, final int maxAge) {
        return kittens.stream().filter(kitten -> minAge <= kitten.getAge()
                && kitten.getAge() <= maxAge).collect(Collectors.toList());
    }

    /**
     * Method to find kitten with given name.
     * @param givenName
     * @return
     */
    public Optional<Kitten> findFirstKittenWithGivenName(final String givenName) {
        return kittens.stream().filter(kitten -> kitten.getName().equalsIgnoreCase(givenName)).findFirst();
    }

    /**
     * Method to sort kittens by age (younger first).
     * @return
     */
    public List<Kitten> kittensSortedByAgeYoungerFirst() {
        return kittens.stream().sorted(Comparator.comparingInt(Kitten::getAge)).collect(Collectors.toList());
    }

    /**
     * Method to sort kittens by age (older first).
     * @return
     */
    public List<Kitten> kittensSortedByAgeOlderFirst() {
        return kittens.stream().sorted(Comparator.comparingInt(Kitten::getAge).reversed()).collect(Collectors.toList());
    }
}
