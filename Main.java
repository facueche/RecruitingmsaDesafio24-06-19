import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Daniel Echenique
 * Para compilar con UTF8 Encoding:
 *      javac -encoding UTF-8 Main.java
 */
class Main
{
    public static void main(String[] args) {
        // Mensaje en cuestión
        String text = "Σ Φ Ψ Ξ Δ λ Ψ Δ Λ Σ Φ Δ λ Ψ ξ Δ ϗ Ξ Δ Φ Ψ Ξ ϑ λ Ψ Λ Σ Θ ϑ Ξ ϗ Φ ϑ λ Ψ Σ Ξ Ψ λ ϑ Ξ Ψ ζ β Σ φ Δ Ψ Σ Φ Ψ Σ Ξ Ψ ξ Λ ϗ Ξ Ξ ϑ Ψ ϖ Σ Ξ Ψ Π Σ ϖ Λ Σ φ Δ Ξ Ψ Ω Ψ Π Λ Σ Φ ϖ ϗ ϖ ϑ Ψ Δ Ψ Ξ Δ Ψ Θ Δ φ ϗ Δ Ψ ϖ Σ Ψ Ξ ϑ λ Ψ Γ Δ Θ ϗ Φ ϑ λ Ψ Σ Ξ Ψ Δ Λ Λ ϗ Σ Λ ϑ Ψ α Δ Ψ Σ Ξ Ψ Δ Λ Λ ϗ Σ Λ ϑ Ψ α Δ Ψ Σ λ Ψ ξ Δ Φ ϖ Σ Λ Δ Ψ ϖ Σ Ψ Φ ϗ Σ ξ Ξ Δ Ψ λ β Ψ Π ϑ Φ Γ Ρ ϑ Ψ Δ Ξ Ψ α ϗ Σ Φ μ ϑ Ψ Ξ ϑ Ψ λ Δ Ξ β ϖ Δ Φ Ψ Ξ Δ λ Ψ ε Ξ Δ β μ Δ λ Ψ ϖ Σ Ξ Ψ Π Δ ζ ϑ Φ Δ Ξ Ψ Ω Ψ Δ Φ ϗ Θ Δ Φ ϖ ϑ Ψ Ξ Δ Ψ μ Λ ϑ Π Δ Ψ Π Δ Λ Ψ Σ λ ϑ λ Ψ Γ Σ Λ Λ ϑ λ Ψ Σ Ξ Ψ Δ Λ Λ ϗ Σ Λ ϑ Ψ α Δ Ψ Σ Ξ Ψ Δ Λ Λ ϗ Σ Λ ϑ Ψ α Δ Ψ Ξ Δ λ Ψ Π Σ Φ Δ λ Ψ Ω Ψ Ξ Δ λ Ψ α Δ η β ϗ μ Δ λ Ψ λ Σ Ψ α Δ Φ Ψ Π Δ Λ Ψ Ξ Δ Ψ Θ ϗ λ Θ Δ Ψ λ Σ Φ ϖ Δ Ψ Ξ Δ λ Ψ Π Σ Φ Δ λ Ψ λ ϑ Φ Ψ ϖ Σ Ψ Φ ϑ λ ϑ μ Λ ϑ λ Ψ Ξ Δ λ Ψ α Δ η β ϗ μ Δ λ Ψ λ ϑ Φ Ψ Δ ζ Σ Φ Δ λ ";
        
        Message message = new Message(text);
        
        message.printMessage("Mensaje original:\n");
        message.printMessage(message.getOriginalMessage());
        message.printMessage("\n");

        // PASO 1
        message.printMessage("Para dar mas enfoque a los símbolos, y suponinendo que no se podría formar palabras, se suprimen los espacios en blanco que se encuentran en medio de cada uno.\n");
        message.cleanMessage();
        message.printMessage(message.getFinalMessage());
        message.printMessage("\n");

        // PASO 2
        message.printMessage("Como paso siguiente se 'mide' la frecuencia de aparición de cada símbolo en el mensaje:\n");
        message.showOcurrences();
        message.printMessage("\n");

        // PASO 3
        message.printMessage("El separador o espacio es el signo más abundante, casi duplicando a la letra más frecuente.");
        message.printMessage("Por lo cual se procede a reemplazar la letra de mayor frecuencia de aparición por el espacio en blanco.");
        message.printMessage("\n");
        message.printMessage("Sabiendo esto, el texto quedaría de la siguiente forma:\n");
        String[][] spaceReplacement = {
            {"Ψ", " "}
        };
        try {
            message.replaceLetters(spaceReplacement);
            message.printMessage(message.getFinalMessage());
            message.printMessage("\n");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        message.printMessage("El cual ya se lo ve con un poco más de sentido para el ojo humano.\n");

        // PASO 4
        message.printMessage("En el diccionario de la RAE la letra más frecuente es la A, pero en cualquier texto castellano, la frecuencia de las partículas 'que', 'el', 'se', 'me', etc. hace que la 'e' sea más frecuente.");
        message.printMessage("Se prueban otros reemplazos.\n");
        String[][] replacements = {
            {"Δ", "a"},
            {"Σ", "e"},
            {"Ξ", "l"},
            {"λ", "s"},
            {"ϑ", "o"},
            {"Λ", "r"},
            {"Φ", "n"},
            {"ϗ", "i"},
            {"ϖ", "d"},
            {"Π", "p"},
            {"α", "v"},
            {"Θ", "m"},
            {"β", "u"},
            {"μ", "t"},
            {"ξ", "b"},
            {"ζ", "j"},
            {"φ", "g"},
            {"Ω", "y"},
            {"Γ", "c"},
            {"η", "q"},
            {"Ρ", "h"},
            {"ε", "f"}
        };
        for (String[] replacement : replacements) {
            message.printMessage(replacement[0] + " => " + replacement[1]);
        }
        message.printMessage("\n");

        // PASO FINAL
        try {
            message.replaceLetters(replacements);
            message.printMessage("Finalmente, se obtiene el mensaje oculto:\n");
            message.printMessage(message.getFinalMessage());
            message.printMessage("\n\n");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        message.printMessage("Saludos!\n");
        message.printMessage("Autor: Daniel Echenique\n\n");
        message.printMessage("____________________________________________________________________");
        message.printMessage("https://es.wikipedia.org/wiki/Frecuencia_de_aparici%C3%B3n_de_letras");
    }
}