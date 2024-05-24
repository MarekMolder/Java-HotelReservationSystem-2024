package ee.taltech.iti0202.bookscraper;

public enum ETopics {
    AJALUGU("ajalugu"),
    ARHITEKTUUR_JA_SISEKUJUNDUS("arhitektuur-ja-sisekujundus"),
    ARVUTI_JA_INTERNET("arvuti-ja-internet"),
    ASTROLOOGIA_JA_MAAGIA("astroloogia-ja-maagia"),
    ILUKIRJANDUS("ilukirjandus"),
    KOKANDUS("kokandus"),
    LASTEKIRJANDUS("lastekirjandus"),
    LOODUSTEADUSED("loodusteadused"),
    TERVIS("tervis"),
    POLIITIKA("poliitika"),
    MEELELAHUTUS("meelelahutus"),
    NOORTEKIRJANDUS("noortekirjandus");


    private final String topicUrl;

    ETopics(String topicUrl) {
        this.topicUrl = topicUrl;
    }

    public String getTopicUrl() {
        return topicUrl;
    }
}
