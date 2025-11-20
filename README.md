# jfx-hospital-simulator
In this repository is a JavaFX project simulating a hospital. Functionality includes admitting, treating, and discharging patients using the HashMap data structure.

# How does it work?
In the TableView, Patients are implicitly ordered by increasing number (1, 2, 3, etc.) (I couldn't find a way to add the number key values into the observable list). The user can select whether to admit a new patient, treat an existing patient, or discharge an existing patient. Upon selecting the admit button, a new Patient object is created to be put in the hash map with a randomly generated condition and initialzes the patient is treated as false. When selecting the treat button, the user enters in a specific number associated with a patient to treat (treat variable becomes true). Upon selecting the discharge button, the user enters a specific patient number for discharge; the patient can only be discharged if its treated variable is true.
