package com.test.pojo;

public class Player {
    private String id;
    private String name;
    private String department;
    private double mavg;
    private double oavg;
    private double psf;


    public double getPsf() {
        return psf;
    }

    public void setPsf(double psf) {
        this.psf = psf;
    }

    private double total;

    private double sorce;
    public String tmpname;

    public double getSorce() {
        return sorce;
    }
    public void setSorce(double sorce) {
        this.sorce = sorce;
    }

    public String getName_() {
        return name;
    }

    public void setName_(String name_) {
        this.name = name_;
    }

    public String getDepartment_() {
        return department;
    }

    public void setDepartment_(String department_) {
        this.department = department_;
    }

    public double getMavg_() {
        return mavg;
    }

    public void setMavg_(double mavg_) {
        mavg = mavg_;
    }

    public double getOavg_() {
        return oavg;
    }

    public void setOavg_(double oavg_) {
        oavg = oavg_;
    }

    public double getTotal_() {
        return total;
    }

    public void setTotal_(double total_) {
        this.total = total_;
    }

    public String getId_() {
        return id;
    }

    public void setId_(String id_) {
        this.id = id_;
    }
}
