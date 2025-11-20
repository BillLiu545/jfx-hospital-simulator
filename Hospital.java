import java.util.*;
import javafx.collections.*;
import javafx.scene.control.Alert.*;
import javafx.scene.control.*;

public class Hospital extends HashMap<Integer, Patient>
{
    private String[] conditionList =
    {
        "Cancer",
        "Respiratory Disease",
        "Bone Fracture",
        "Gastric Ulcer",
        "Cardiovascular Disease",
        "Other"
    };
    private int curr_num = 0;
    // Admit a new patient to the hospital
    public Patient admit()
    {
        curr_num += 1;
        Random rand = new Random();
        String condition = conditionList[rand.nextInt(conditionList.length)];
        Patient newPatient = new Patient(condition);
        put(curr_num, newPatient);
        return newPatient;
    }
    // Treat a patient given input of a specific patient number
    public Patient treat(int patient_num)
    {
        if (isEmpty())
        {
            return null;
        }
        Patient p = get(patient_num);
        if (p != null) {
            p.treat();
        }
        return p;
    }
    // Patient number is input by the user for treatment
    public Patient input_treat()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Enter Patient Number");
        dialog.setContentText("Enter number of a patient to treat: ");
        dialog.setTitle("Enter Patient Number");
        Optional<String> optional = dialog.showAndWait();
        try
        {
            int num = Integer.parseInt(optional.get());
            Patient p = treat(num);
            return p;
        }
        catch (NumberFormatException ex)
        {
            return null;
        }
    }
    // Discharge a patient from the hospital if treated given specific patient number
    public Patient discharge(int patient_num)
    {
        if (isEmpty())
        {
            return null;
        }
        Patient p = get(patient_num);
        if (p != null)
        {
            if (p.isTreated())
            {
                remove(patient_num);
                return p;
            }
        }
        return null;
    }
    // Patient number is input by the user for discharge
    public Patient input_discharge()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Enter Patient Number");
        dialog.setContentText("Enter number of a patient to discharge: ");
        dialog.setTitle("Enter Patient Number");
        Optional<String> optional = dialog.showAndWait();
        try
        {
            int num = Integer.parseInt(optional.get());
            Patient p = discharge(num);
            return p;
        }
        catch (NumberFormatException ex)
        {
            return null;
        }
    }
    // Convert to observable list for use in a table view
    public ObservableList<Patient> toObservableList()
    {
        Collection<Patient> collection = values();
        ObservableList<Patient> list = FXCollections.observableArrayList();
        Iterator<Patient> iter = collection.iterator();
        while (iter.hasNext())
        {
            Patient next = iter.next();
            list.add(next);
        }
        return list;
    }
    // Main method to test data structure operations
    public static void main(String[] args)
    {
        Hospital hosp = new Hospital();
        System.out.println(hosp.discharge(1));
        System.out.println(hosp.admit().toString());
        System.out.println(hosp.admit().toString());
        System.out.println(hosp.treat(2));
        System.out.println(hosp.treat(3));
        System.out.println(hosp.admit().toString());
        System.out.println(hosp.admit().toString());
        System.out.println(hosp.discharge(2));
        System.out.println(hosp.discharge(2));
    }
}
