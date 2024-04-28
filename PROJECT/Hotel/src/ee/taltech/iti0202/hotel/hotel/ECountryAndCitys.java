package ee.taltech.iti0202.hotel.hotel;

public enum ECountryAndCitys {
    FRANCE(new String[]{"paris", "marseille", "lyon"}),
    GERMANY(new String[]{"berlin", "munich", "hamburg"}),
    ITALY(new String[]{"rome", "milan", "naples"}),
    SPAIN(new String[]{"madrid", "barcelona", "valencia"}),
    LITHUANIA(new String[]{"vilnius", "kaunas", "klaipėda"}),
    LATVIA(new String[]{"riga", "daugavpils", "liepāja"}),
    ESTONIA(new String[]{"tallinn", "tartu", "pärnu"});

    private final String[] city;

    ECountryAndCitys(String[] city) {
        this.city = city;
    }

    public String[] getCity() {
        return city;
    }
}
