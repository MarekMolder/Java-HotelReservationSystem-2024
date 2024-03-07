package ee.taltech.iti0202.files.morse;

import ee.taltech.iti0202.files.input.InputFilesBufferReader;
import ee.taltech.iti0202.files.input.InputFilesScanner;
import ee.taltech.iti0202.files.output.OutputFilesWriter;

import java.util.List;
import java.util.Map;

public class MorseFilesController {

    public static void main(String[] args) {
        InputFilesScanner scanner = new InputFilesScanner();
        List<String> lines = scanner.readTextFromFile("test-file.txt");
        lines.forEach(System.out::println); //lines in morse.txt which contains Morse codes

        InputFilesBufferReader bufferReader = new InputFilesBufferReader();
        List<String> lines2 = bufferReader.readTextFromFile("morse.txt");
        lines2.forEach(System.out::println); //lines in morse.txt which contains Morse codes

        MorseTranslator translator = new MorseTranslator();
        Map<String, String> codes = translator.addMorseCodes(lines);
        codes.forEach((key, value) -> System.out.println(key + " " + value)); //key and value

        List<String> input = scanner.readTextFromFile("input.txt");
        input.forEach(System.out::println); //your input lines

        List<String> morseLines = translator.translateLinesToMorse(input);
        morseLines.forEach(System.out::println); //your input lines in Morse

        List<String> normalLines = translator.translateLinesFromMorse(morseLines);
        normalLines.forEach(System.out::println); //your input lines in regular text

        OutputFilesWriter writer = new OutputFilesWriter();
        System.out.println(writer.writeLinesToFile(normalLines, "output.txt")); //true
        //This should also create a new file/ write in an existing file
    }
}
