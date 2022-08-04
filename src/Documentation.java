class Documentation {
    private String name, date, designation, fileName;
    public void setName(String name) {
        this.name = name;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDesignation() {
        return this.designation;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Documentation.\n" +
                "fileName: " + fileName + "\n" +
                "name: " + name + "\n" +
                "date: " + date + "\n" +
                "designation: " + designation + "\n";
    }
}