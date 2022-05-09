package com.Iasoftware.nomina.shared.domain;

public class WorkingHours {
    private String idtechnical;
    private Integer normalHours;
    private Integer nightHours;
    private Integer sundayHours ;
    private Integer normalOvertime ;
    private Integer extraNightHours;
    private Integer extraSundayHours ;


    public WorkingHours(){

    }

    public Integer getNormalHours() {
        return normalHours;
    }

    public void setNormalHours(Integer normalHours) {
        this.normalHours = normalHours;
    }

    public Integer getNightHours() {
        return nightHours;
    }

    public void setNightHours(Integer nightHours) {
        this.nightHours = nightHours;
    }

    public Integer getSundayHours() {
        return sundayHours;
    }

    public void setSundayHours(Integer sundayHours) {
        this.sundayHours = sundayHours;
    }

    public Integer getNormalOvertime() {
        return normalOvertime;
    }

    public void setNormalOvertime(Integer normalOvertime) {
        this.normalOvertime = normalOvertime;
    }

    public Integer getExtraNightHours() {
        return extraNightHours;
    }

    public void setExtraNightHours(Integer extraNightHours) {
        this.extraNightHours = extraNightHours;
    }

    public String getIdtechnical() {
        return idtechnical;
    }

    public Integer getExtraSundayHours() {
        return extraSundayHours;
    }


    public void setExtraSundayHours(Integer extraSundayHours) {
        this.extraSundayHours = extraSundayHours;
    }

    public void setIdtechnical(String idtechnical) {
        this.idtechnical = idtechnical;
    }

    @Override
    public String toString() {
        return "WorkingHours{" +
                "idtechnical='" + idtechnical + '\'' +
                ", normalHours=" + normalHours +
                ", nightHours=" + nightHours +
                ", sundayHours=" + sundayHours +
                ", normalOvertime=" + normalOvertime +
                ", extraNightHours=" + extraNightHours +
                ", extraSundayHours=" + extraSundayHours +
                '}';
    }

}
