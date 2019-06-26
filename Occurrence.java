/**
 * @author Daniel Echenique
 */
class Occurrence implements Comparable<Occurrence>
{
    private char letter;
    private int ocurrence;

    public Occurrence(char letter, int ocurrence)
    {
        this.letter = letter;
        this.ocurrence = ocurrence;
    }

    public char getLetter() {
        return letter;
    }

    public int getOcurrence() {
        return ocurrence;
    }

    @Override
    public int compareTo(Occurrence ocurrence) {
        int ocurrenceComp = ((Occurrence)ocurrence).ocurrence;
        return ocurrenceComp - this.ocurrence;
    }
}