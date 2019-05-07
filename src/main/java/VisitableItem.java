/**
 * Implementuje wzorzec Visitor
 */
public interface VisitableItem {

    void accept(Visitor visitor);

}
