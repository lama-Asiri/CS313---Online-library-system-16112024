package OnlineLibrarySystem.models;

public class Librarian extends User {

    private String LibraryBranch;

    public Librarian(String name, String email, String password, String gender, String phoneNo, String LibraryBranch) {
        super(name, email, password, gender, phoneNo, "Librarian");
        this.LibraryBranch = LibraryBranch;
    }

    public Librarian(String name, String email, String password, String gender, String phoneNo) {
        super(name, email, password, gender, phoneNo, "Librarian");

    }

    public void setLibraryBranch(String LibraryBranch) {
        this.LibraryBranch = LibraryBranch;
    }

    public String getLibraryBranch() {
        return LibraryBranch;
    }

    @Override
    public String toString() {
        return super.toString() + ", Library Branch" + getLibraryBranch();

    }

}
