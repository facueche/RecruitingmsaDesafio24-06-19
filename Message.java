import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Daniel Echenique
 */
class Message
{
    private String originalMessage;
    private String finalMessage;

    public Message(String originalMessage)
    {
        this.setOriginalMessage(originalMessage);
        this.setFinalMessage(originalMessage);
    }

    public String getOriginalMessage()
    {
        return this.originalMessage;
    }

    public void setOriginalMessage(String originalMessage)
    {
        this.originalMessage = originalMessage;
    }

    public String getFinalMessage()
    {
        return this.finalMessage;
    }

    public void setFinalMessage(String finalMessage)
    {
        this.finalMessage = finalMessage;
    }

    /**
     * Imprime mensaje con caracteres con codificación UTF8
     * @param message
     */
    public void printMessage(String message)
    {
        PrintStream out;
        try {
            out = new PrintStream(System.out, true, "UTF-8");
            out.println(message);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());;
        }
    }

    /**
     * Borra espacios en blanco y saltos de linea
     */
    public void cleanMessage()
    {
        this.replaceTextOnMessage(" ", "");
        this.replaceTextOnMessage("\n", "");
    }

    /**
     * Obtiene la ocurrencia de cada letra en un texto
     * @param String text
     * @return Lista de ocurrencias
     */
    private ArrayList<Occurrence> countOccurrences(String text)
    {
        ArrayList<Occurrence> occurrences = new ArrayList<Occurrence>();
        for (int i = 0; i < text.length(); i++) {
            if (letterAlreadyCounted(occurrences, text.charAt(i))) {
                continue;
            }
            int count = 1;
            for (int j = i + 1; j < text.length(); j++) {
                if (text.charAt(i) == text.charAt(j)) {
                    count++;
                }
            }
            occurrences.add(new Occurrence(text.charAt(i), count));
        }
        
        // Ordena por ocurrencia de forma descendente
        Collections.sort(occurrences);

        return occurrences;
    }

    /**
     * Revisa si ya se encontró la ocurrencia de una letra
     * @param list
     * @param letter
     * @return boolean
     */
    private boolean letterAlreadyCounted(ArrayList<Occurrence> list, char letter)
    {
        boolean counted = false;

        int i = 0;
        while (i < list.size()) {
            if (list.get(i).getLetter() == letter) {
                counted = true;
                break;
            }
            i++;
        }

        return counted;
    }

    /**
     * Imprime en consola la ocurrencia de cada letra
     */
    public void showOcurrences()
    {
        ArrayList<Occurrence> occurrences = this.countOccurrences(this.finalMessage);
        occurrences.forEach((Occurrence occurrence) -> {
            this.printMessage("Key: " + occurrence.getLetter() + " - Occurrence: " + occurrence.getOcurrence());
        });
    }

    /**
     * Reemplaza una sub-cadena dentro de otra cadena
     * @param target
     * @param replacement
     */
    private void replaceTextOnMessage(String target, String replacement)
    {
        this.finalMessage = this.finalMessage.replace(target, replacement);
    }

    /**
     * Reemplaza letras en el mensaje
     * @param replacements
     */
    public void replaceLetters(String[][] replacements) throws Exception
    {
        for (String[] replacement : replacements) {
            if (replacement.length < 2) {
                throw new Exception("Cada letra tiene que tener un reemplazo");
            }
            this.replaceTextOnMessage(replacement[0], replacement[1]);
        }
    }
}