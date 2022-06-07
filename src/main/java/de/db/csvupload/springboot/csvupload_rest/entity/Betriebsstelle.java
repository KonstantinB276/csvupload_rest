package de.db.csvupload.springboot.csvupload_rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByPosition;

@JsonIgnoreProperties(value = { "code" })
public class Betriebsstelle {
    @CsvBindByPosition(position = 0)
    private String code;
    @CsvBindByPosition(position = 1)
    private String name;
    @CsvBindByPosition(position = 2)
    private String kurzname;
    @CsvBindByPosition(position = 3)
    private String typ;

    public Betriebsstelle() {
    }

    public Betriebsstelle(String code, String name, String kurzname, String typ) {
        this.code = code;
        this.name = name;
        this.kurzname = kurzname;
        this.typ = typ;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("Kurzname")
    public String getKurzname() {
        return kurzname;
    }

    public void setKurzname(String kurzname) {
        this.kurzname = kurzname;
    }

    @JsonProperty("Typ")
    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    @Override
    public String toString() {
        return "Betriebsstelle{" +
                "code='" + code + '\'' +
                ", langname='" + name + '\'' +
                ", kurzname='" + kurzname + '\'' +
                ", typ_lang='" + typ + '\'' +
                '}';
    }
}
