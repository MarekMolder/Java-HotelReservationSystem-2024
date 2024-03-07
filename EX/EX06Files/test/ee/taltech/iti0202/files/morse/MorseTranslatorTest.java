package ee.taltech.iti0202.files.morse;

import ee.taltech.iti0202.files.input.InputFilesLines;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MorseTranslatorTest {
    InputFilesLines lines = new InputFilesLines();
    MorseTranslator translator = new MorseTranslator();

    @Test
    public void testTranslateLineToMorse() {
        translator.addMorseCodes(lines.readTextFromFile("morse.txt"));
        String expected = "- . .-. .\t... .-\t--- .-.. . -.. --..--\t-.- --- .-.. .";
        String result = translator.translateLineToMorse("tere sa oled, kole");
        assertEquals(expected, result);
    }

    @Test
    public void testTranslateLineFromMorse() {
        translator.addMorseCodes(lines.readTextFromFile("morse.txt"));
        String expected = "tere sa oled, kole";
        String result = translator.translateLineFromMorse("- . .-. .\t... .-\t--- .-.. . -.. --..--\t-.- --- .-.. .");
        assertEquals(expected, result);
    }

    @Test
    public void testTranslateLinesToMorse() {
        translator.addMorseCodes(lines.readTextFromFile("morse.txt"));
        List<String> expected = new ArrayList<>(Arrays.asList
                ("- . .-. .\t... .-\t--- .-.. . -.. --..--\t-.- --- .-.. .", "-- .. ...\t... ..-\t-. .. -- ..\t--- -. ..--.."));
        List<String> result = translator.translateLinesToMorse(new ArrayList<>(Arrays.asList("tere sa oled, kole"
                , "mis su nimi on?")));
        assertEquals(expected, result);
    }

    @Test
    public void testTranslateLinesFromMorse() {
        translator.addMorseCodes(lines.readTextFromFile("morse.txt"));
        List<String> expected = new ArrayList<>(Arrays.asList("tere sa oled, kole"
                , "mis su nimi on?"));
        List<String> result = translator.translateLinesFromMorse(new ArrayList<>(Arrays.asList
                ("- . .-. .\t... .-\t--- .-.. . -.. --..--\t-.- --- .-.. .", "-- .. ...\t... ..-\t-. .. -- ..\t--- -. ..--..")));
        assertEquals(expected, result);
    }

    @Test
    public void testTranslateLineToMorseUpper() {
        translator.addMorseCodes(lines.readTextFromFile("morse.txt"));
        List<String> expected = new ArrayList<>(Arrays.asList
                ("- . .-. .\t... .-\t--- .-.. . -.. --..--\t-.- --- .-.. .", "-- .. ...\t... ..-\t-. .. -- ..\t--- -. ..--.."));
        List<String> result = translator.translateLinesToMorse(new ArrayList<>(Arrays.asList("TERE sa oled, kole", "mis su nimi on?")));
        assertEquals(expected, result);
    }
}
