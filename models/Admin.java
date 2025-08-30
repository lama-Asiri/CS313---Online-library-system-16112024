package OnlineLibrarySystem.models;

public class Admin extends User {

    private String assignedDepartment;

    public Admin(String name, String email, String password, String gender, String phoneNo, String role, String assignedDepartment) {
        super(name, email, password, gender, phoneNo, "Admin");
        this.assignedDepartment = assignedDepartment;
    }

    public Admin(String name, String email, String password, String gender, String phone, String role) {
        super(name, email, password, gender, phone, "Admin");
    }

    public void setAssignedDepartment(String assignedDepartment) {
        this.assignedDepartment = assignedDepartment;
    }

    public String getAssignedDepartment() {
        return assignedDepartment;
    }

    @Override
    public String toString() {
        return super.toString() + ", Assigned Department" + getAssignedDepartment();
    }

}
