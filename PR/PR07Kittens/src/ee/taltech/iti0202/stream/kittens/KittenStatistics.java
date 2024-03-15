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

    public OptionalDouble findKittensAverageAge() {
        return kittens.stream().map(Kitten::getAge).flatMapToInt(IntStream::of).average();
    }

    public Optional<Kitten> findOldestKitten() {
        return kittens.stream().max(Comparator.comparingInt(Kitten::getAge));
    }

    public List<Kitten> findYoungestKittens() {
        return kittens.stream().min(Comparator.comparingInt(Kitten::getAge)).stream().collect(Collectors.toList());
    }

    public List<Kitten> findKittensAccordingToGender(final Kitten.Gender gender) {
        return kittens.stream().filter(kitten -> kitten.getGender().equals(gender)).collect(Collectors.toList());
    }

    public List<Kitten> findKittensBetweenAges(final int minAge, final int maxAge) {
        return kittens.stream().filter(kitten -> minAge <= kitten.getAge() && kitten.getAge() <= maxAge).collect(Collectors.toList());
    }

    public Optional<Kitten> findFirstKittenWithGivenName(final String givenName) {
        return kittens.stream().filter(kitten -> kitten.getName().equals(givenName)).findFirst();
    }

    public List<Kitten> kittensSortedByAgeYoungerFirst() {
        return kittens.stream().sorted().min(Comparator.comparingInt(Kitten::getAge)).stream().collect(Collectors.toList());
    }

    public List<Kitten> kittensSortedByAgeOlderFirst() {
        return kittens.stream().sorted().max(Comparator.comparingInt(Kitten::getAge)).stream().collect(Collectors.toList());
    }
}
