package com.android.footballclub;

import io.realm.RealmObject;

public class ModelClubRealm extends RealmObject {

    int id;
    String judulClub;
    String namaClub;
    String logoClub;
    String negaraClub;
    String tahunClub;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudulClub() {
        return judulClub;
    }

    public void setJudulClub(String judulClub) {
        this.judulClub = judulClub;
    }

    public String getNamaClub() {
        return namaClub;
    }

    public void setNamaClub(String namaClub) {
        this.namaClub = namaClub;
    }

    public String getLogoClub() {
        return logoClub;
    }

    public void setLogoClub(String logoClub) {
        this.logoClub = logoClub;
    }

    public String getNegaraClub() {
        return negaraClub;
    }

    public void setNegaraClub(String negaraClub) {
        this.negaraClub = negaraClub;
    }

    public String getTahunClub() {
        return tahunClub;
    }

    public void setTahunClub(String tahunClub) {
        this.tahunClub = tahunClub;
    }
}
