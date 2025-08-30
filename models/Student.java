package OnlineLibrarySystem.models;

import java.util.LinkedList;

public class Student extends User {

    private LinkedList<Loan> loanLinkedList = new LinkedList<>();

    public Student(String name, String email, String password, String gender, String phoneNo, String role) {
        super(name, email, password, gender, phoneNo, "Student");
    }

    public void setLoanLinkedList(LinkedList<Loan> loanLinkedList) {
        this.loanLinkedList = loanLinkedList;
    }

    public LinkedList<Loan> getLoanLinkedList() {
        return loanLinkedList;
    }

    @Override
    public String toString() {
        return super.toString() + "loans: " + getLoanLinkedList();
    }

}
