import java.util.*;

public class ConcurrenciaComp implements Comparator<Concurrencia> {
    public int compare(Concurrencia c1,Concurrencia c2) {
        if (c1.Veces==c2.Veces) {
            return 0;
        } else if (c1.Veces > c2.Veces) {
            return 1;
        } else {
            return -1;
        }
    }
}
