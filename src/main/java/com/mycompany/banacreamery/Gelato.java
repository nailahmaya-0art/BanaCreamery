package com.mycompany.banacreamery;

public class Gelato extends ProdukEsKrim {
    private double persentaseLemakSusu;
    private String rasaBase;

    public Gelato(String kodeProduk, String namaVarian, double hargaDasar, int stok, 
                  double persentaseLemakSusu, String rasaBase) {
        super(kodeProduk, namaVarian, hargaDasar, stok);
        this.persentaseLemakSusu = persentaseLemakSusu;
        this.rasaBase = rasaBase;
    }

    @Override
    public double hitungHargaJual() {
        return getHargaDasar() * 1.15;
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("| %-7s | %-20s | Rp%-10.2f | %-4d | %-15s | Base: %-6s (Lemak: %.1f%%) |%n",
                getKodeProduk(), getNamaVarian(), hitungHargaJual(), getStok(), 
                "Gelato Artisan", rasaBase, persentaseLemakSusu);
    }

    @Override
    public void caraPenyajian() {
        System.out.println("-> Penyajian Gelato: Di-scoop padat dan disajikan kaku pada suhu -12 derajat Celcius.");
    }
}