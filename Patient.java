
public class Patient
{
    private String condition;
    private boolean treated;
    public Patient(String condition)
    {
        this.condition = condition;
        this.treated = false;
    }
    
    public String getCondition()
    {
        return condition;
    }
    
    public void treat()
    {
        treated = true;
    }
    
    public boolean isTreated()
    {
        return treated;
    }
    
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Condition: " + condition + "/Treated: ");
        if (treated == true)
        {
            sb.append("Yes");
        }
        else
        {
            sb.append("No");
        }
        return sb.toString();
    }
}
