package com.mycompany.banacreamery;

public class Gelato extends ProdukEsKrim {
    private double persentaseLemakSusu;
    private String rasaBase;

    public Gelato(String kodeProduk, String namaVarian, double hargaDasar, int stok, double persentaseLemakSusu, String rasaBase) {
        super(kodeProduk, namaVarian, hargaDasar, stok);
        setPersentaseLemakSusu(persentaseLemakSusu);
        this.rasaBase = rasaBase;
    }

    public double getPersentaseLemakSusu() {
        return persentaseLemakSusu;
    }

    public void setPersentaseLemakSusu(double persentaseLemakSusu) {
        if (persentaseLemakSusu < 0 || persentaseLemakSusu > 100) {
            this.persentaseLemakSusu = 5.0;
        } else {
            this.persentaseLemakSusu = persentaseLemakSusu;
        }
    }

    public String getRasaBase() {
        return rasaBase;
    }

    public void setRasaBase(String rasaBase) {
        this.rasaBase = rasaBase;
    }

    @Override
    public double hitungHargaJual() {
        return super.getHargaDasar() * 1.15;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.printf("| Gelato Artisan  | Base: %-10s (Lemak: %.1f%%)%n", 
                this.rasaBase, this.persentaseLemakSusu);
    }
}