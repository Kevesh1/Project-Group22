package budgetapp.model;

public class expenseAndIncome {

    private String sum;

    private String note;


    public expenseAndIncome(String sum, String Note){
        this.sum =sum;
        this.note = note;
    }

    public String getSum() {
        return sum;
    }

    public String getNote() {
        return note;
    }
}
