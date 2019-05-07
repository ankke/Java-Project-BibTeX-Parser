import java.util.List;

public class PrintVisitor implements Visitor {

    /**
     * @param book item do wypisania
     *  metoda wypisuje rekord w odpowiednim formacie
     *  korzysta z metody pomocniczej printName
     */
    public void visitItem(Item book){
        System.out.printf("-------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.printf("%50s \n", book.types.get("category").toUpperCase() + " " + book.types.get("crossrefKey"));
        System.out.printf("-------------------------------------------------------------------------------------------");
        System.out.println();
        for(String type : book.obligatory){
            printName(type, book);
            System.out.printf("%20s %70s  \n", " |", " |");
        }
        for(String type : book.optional) {
            printName(type, book);
            System.out.printf("%20s %70s  \n", " |", " |");
        }
        System.out.printf("-------------------------------------------------------------------------------------------");
        System.out.println();
    }

    /**
     * @param type typ atrybutu
     * @param book rodzaj rekordu
     * metoda pomocnicza do wypisywania jeden pod drugim nazw osób ale editora i authora
     */
    public void printName(String type, Item book){
        if(type.equals("editor") || type.equals("author")){
            List<String> name;
            try {
                name = Names.splitNames(book.types.get(type));
                System.out.printf("%20s %70s \n", type + " |",  " |");
                if(name != null) {
                    for (String s : name) {
                        System.out.printf("%20s %70s \n", " |", s + " |");
                    }
                }
                else{
                    System.out.printf("%20s %70s \n", " |", book.types.get(type) + " |");
                }
            }catch(WrongNameFormat e) {
                System.out.println(e.getMessage());
            }
        }
        else  System.out.printf("%20s %70s \n", type + " |", book.types.get(type) + " |");

    }
}
