package ee.taltech.iti0202.texteditor;

public class Main {
        public static void main(String[] args) {
            TextEditor textEditor = new TextEditor();
            textEditor.addText("diary of a programmer", TextType.TITLE);
            textEditor.addText("Day1: Finally started with my new project today.", TextType.PLAIN);
            textEditor.addText("I have \nbeen working on my camelcase.\n", TextType.CAMELCASE);
            System.out.println(textEditor.getCurrentText());
            // Diary of a Programmer
            // Day1: Finally started with my new project today. iHaveBeenWorkingOnMyCamelcase.
            textEditor.addText("Day2: ", TextType.PLAIN);
            textEditor.addText("i'm losing my mind, this bug keeps avoiding meeeeeeeee.\n", TextType.SCREAMING);
            textEditor.addText("Day3: ", TextType.PLAIN);
            textEditor.addText("help me\n", TextType.BINARY);
            System.out.println(textEditor.getCurrentText());
            // Diary of a Programmer
            // Day1: Finally started with my new project today. iHaveBeenWorkingOnMyCamelcase.
            // Day2: I'M LOSING MY MIND, THIS BUG KEEPS AVOIDING MEEEEEEEEE. 01101000011001010110110001110000001000000110110101100101
            // Day3: 01101000011001010110110001110000001000000110110101100101
            System.out.println(textEditor.undo());
            System.out.println();
            // Diary of a Programmer
            // Day1: Finally started with my new project today. iHaveBeenWorkingOnMyCamelcase.
            // Day2: I'M LOSING MY MIND, THIS BUG KEEPS AVOIDING MEEEEEEEEE.
            // Day3:
            textEditor.addText("Fixed the bug, it was a typo.");
            System.out.println(textEditor.getCurrentText());
            System.out.println();
            // Diary of a Programmer
            // Day1: Finally started with my new project today. iHaveBeenWorkingOnMyCamelcase.
            // Day2: I'M LOSING MY MIND, THIS BUG KEEPS AVOIDING MEEEEEEEEE.
            // Day3: 0100011001101001011110000110010101100100001000000111010001101000011001010010000001100010011101010110011100101100001000000110100101110100001000000111011101100001011100110010000001100001001000000111010001111001011100000110111100101110

            textEditor.undo();
            textEditor.setStrategy(TextType.PLAIN);
            textEditor.addText("Fixed the bug, it was a typo.");
            System.out.println(textEditor.getCurrentText());
            System.out.println();
            // Diary of a Programmer
            // Day1: Finally started with my new project today. iHaveBeenWorkingOnMyCamelcase.
            // Day2: I'M LOSING MY MIND, THIS BUG KEEPS AVOIDING MEEEEEEEEE.
            // Day3: Fixed the bug, it was a typo.

            textEditor.clear();
            System.out.println(textEditor.redo().isEmpty()); // true
        }
}
